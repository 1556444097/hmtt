package com.itheima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.article.feign.ApAuthorFeign;
import com.itheima.article.pojo.ApAuthor;
import com.itheima.common.constants.BusinessConstants;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.vo.PageResultVo;
import com.itheima.common.vo.ResultVo;
import com.itheima.user.dto.ApUserRealnameAuthDto;
import com.itheima.user.dto.ApUserRealnamePageRequestDto;
import com.itheima.user.mapper.ApUserMapper;
import com.itheima.user.pojo.ApUser;
import com.itheima.user.pojo.ApUserRealname;
import com.itheima.user.mapper.ApUserRealnameMapper;
import com.itheima.user.service.ApUserRealnameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.wemedia.feign.WmUserFeign;
import com.itheima.wemedia.pojo.WmUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @description <p>APP实名认证信息 业务实现</p>
 * @package com.itheima.user.service.impl
 */
@Slf4j
@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    @Autowired
    private WmUserFeign wmUserFeign;
    @Autowired
    private ApAuthorFeign apAuthorFeign;
    @Autowired
    private ApUserMapper apUserMapper;
    /**
     * 实名认证列表 分页查询
     *
     * @param dto
     * @return
     */
    @Override
    public PageResultVo findPage(ApUserRealnamePageRequestDto dto) {
        IPage<ApUserRealname> pageInfo = new Page<>(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<ApUserRealname> lqw = new LambdaQueryWrapper<>();
        lqw.eq(dto.getStatus() != null, ApUserRealname::getStatus, dto.getStatus());
        page(pageInfo, lqw);

        return PageResultVo.pageResult(dto.getPage(), dto.getSize(), pageInfo.getTotal(), pageInfo.getRecords());
    }

    /**
     * 实名认证 通过
     *
     * @param dto
     */
    @Override
    @Transactional
    public void authPass(ApUserRealnameAuthDto dto) {
        //1. 更新实名认证表记录的状态
        updateApUserRealName(dto);
        //2. 更新用户表的flag与是否认证过
        // 查询user_id, 通过实名认证表记录中就有这个字段user_id
        ApUserRealname apUserRealname = getById(dto.getId());
        Long apUserId = apUserRealname.getUserId();
        updateApUser(apUserId);
        //3. 添加自媒体账号
        WmUser wmUser = createWmUser(apUserId);
        //4. 添加作者
        createApAuthor(wmUser);
        //5. 事务控制
    }

    /**
     * 添加作者信息
     * @param wmUser
     */
    private void createApAuthor(WmUser wmUser) {
        //1. 查询是否存在
        //1.1 远程调用文章微服通过
        ResultVo<ApAuthor> getResult = apAuthorFeign.getByApUserIdWmUserId(wmUser.getApUserId(), wmUser.getId());
        if (!getResult.isSuccess()) {
            // 不成功，要抛异常，让上面事务回滚
            log.error("远程调用文章微服查询作者信息失败:{}", getResult.getErrorMessage());
            throw new LeadNewsException("操作失败, 请求稍后重试!");
        }
        ApAuthor author = getResult.getData();
        //2. 不存在才需要添加
        if(null == author){
            author = new ApAuthor();
            //设置属性
            author.setName(wmUser.getName());
            author.setUserId(wmUser.getApUserId());
            author.setWmUserId(wmUser.getId());
            author.setCreatedTime(LocalDateTime.now());
            author.setType(BusinessConstants.ApAuthorConstants.A_MEDIA_USER);
            // 远程调用添加
            ResultVo addResult = apAuthorFeign.addAuthor(author);
            if (!addResult.isSuccess()) {
                // 不成功，要抛异常，让上面事务回滚
                log.error("远程调用文章微服添加作者信息失败:{}", getResult.getErrorMessage());
                throw new LeadNewsException("操作失败, 请求稍后重试!");
            }
        }
    }

    /**
     * 添加自媒体账号
     * @param apUserId
     */
    private WmUser createWmUser(Long apUserId) {
        //1. 判断是否存在
        // 1.1 远程调用自媒体微 通过ap_user_id查询leandnews_wemedia.wm_user表
        ResultVo<WmUser> getResult = wmUserFeign.getByApUserId(apUserId);
        if (!getResult.isSuccess()) {
            // 不成功，要抛异常，让上面事务回滚
            log.error("远程调用自媒体查询自媒体用户信息失败:{}", getResult.getErrorMessage());
            throw new LeadNewsException("操作失败, 请求稍后重试!");
        }
        // data就是返回的自媒体用户信息
        WmUser wmUser = getResult.getData();
        //2. 不存在才添加
        if(null == wmUser){
            // 创建一对象
            wmUser = buildWmUser(apUserId);
            // 远程调用添加
            ResultVo<WmUser> addResult = wmUserFeign.addWmUser(wmUser);
            if (!addResult.isSuccess()) {
                // 不成功，要抛异常，让上面事务回滚
                log.error("远程调用自媒体添加自媒体用户信息失败:{}", getResult.getErrorMessage());
                throw new LeadNewsException("操作失败, 请求稍后重试!");
            }
            //新增自媒体用户信息, 才能得新增的id
            wmUser = addResult.getData();
        }
        //返回自媒体账号id
        return wmUser;
    }

    /**
     * 构建WmUser对象
     * @param apUserId
     * @return
     */
    private WmUser buildWmUser(Long apUserId) {
        WmUser wmUser = new WmUser();
        // 给它属性赋值
        ApUser apUser = apUserMapper.selectById(apUserId);
        BeanUtils.copyProperties(apUser, wmUser);// 属性名且类型必须相同才会复制
        // 能否复制id, 不能
        wmUser.setId(null);
        // 特殊属性处理
        wmUser.setStatus(BusinessConstants.WmUserConstants.STATUS_OK);
        wmUser.setApUserId(apUserId);
        wmUser.setNickname(apUser.getName());
        wmUser.setType(BusinessConstants.WmUserConstants.TYPE_PERSONAL);
        wmUser.setScore(0);// 运营分为0
        wmUser.setCreatedTime(LocalDateTime.now());
        return wmUser;
    }

    /**
     * 更新用户表的flag与是否认证过
     * @param apUserId
     */
    private void updateApUser(Long apUserId) {
        // 创建更新的对象
        ApUser updatePojo = new ApUser();
        // 设置要更新的字段与值
        updatePojo.setIsIdentityAuthentication(BusinessConstants.ApUserConstants.AUTHENTICATED);
        updatePojo.setFlag(BusinessConstants.ApUserConstants.FLAG_WEMEDIA);

        // 设置更新的id, ap_user.id
        updatePojo.setId(apUserId);
        // 执行更新
        apUserMapper.updateById(updatePojo);
    }

    /**
     * 更新实名认证表记录的状态
     * @param dto
     */
    private void updateApUserRealName(ApUserRealnameAuthDto dto) {
        // 更新的对象
        ApUserRealname updatePojo = new ApUserRealname();
        // 设置要更新的字段与值
        updatePojo.setStatus(BusinessConstants.ApUserRealnameConstants.AUTH_PASS);
        // 设置条件的id
        updatePojo.setId(dto.getId());
        // 更新
        updateById(updatePojo);
    }

    /**
     * 实名认证 驳回
     *
     * @param dto
     */
    @Override
    public void authFail(ApUserRealnameAuthDto dto) {
        ApUserRealname updatePajo = new ApUserRealname();
        updatePajo.setStatus(BusinessConstants.ApUserRealnameConstants.AUTH_REJECT);
        updatePajo.setReason(dto.getMsg());

        updatePajo.setId(dto.getId());
        updateById(updatePajo);
    }
}
