package com.itheima.user.service;

import com.itheima.common.vo.PageResultVo;
import com.itheima.user.dto.ApUserRealnameAuthDto;
import com.itheima.user.dto.ApUserRealnamePageRequestDto;
import com.itheima.user.pojo.ApUserRealname;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description <p>APP实名认证信息 业务接口</p>
 *
 * @version 1.0
 * @package com.itheima.user.service
 */
public interface ApUserRealnameService extends IService<ApUserRealname> {

    /**
     * 实名认证列表 分页查询
     * @param dto
     * @return
     */
    PageResultVo findPage(ApUserRealnamePageRequestDto dto);

    /**
     * 实名认证 通过
     * @param dto
     */
    void authPass(ApUserRealnameAuthDto dto);

    /**
     * 实名认证 驳回
     * @param dto
     */
    void authFail(ApUserRealnameAuthDto dto);
}
