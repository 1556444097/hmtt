package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.mapper.AdUserMapper;
import com.itheima.admin.pojo.AdUser;
import com.itheima.admin.service.AdUserService;
import com.itheima.common.dto.LoginUserDto;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.AppJwtUtil;
import com.itheima.common.vo.LoginUserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @description <p>管理员用户信息 业务实现</p>
 *
 * @version 1.0
 * @package com.itheima.admin.service.impl
 */
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {

    /**
     * 登录校验
     *
     * @param dto
     * @return
     */
    @Override
    public LoginUserVo loginCheck(LoginUserDto dto) {
        // 获取请求参数
        String name = dto.getName();
        String password = dto.getPassword();
        // 根据用户名查询用户信息
        AdUser adUser = query().eq("name", name).one();
        // 判断用户是否存在
        if (adUser == null) {
            throw new LeadNewsException("用户名或密码错误");
        }
        // 判断状态
        if (adUser.getStatus() != 9) {
            throw new LeadNewsException("用户已被禁用");
        }

        // 判断密码是否正确
        password = password + adUser.getSalt();
        String encrypt = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!encrypt.equals(adUser.getPassword())) {
            throw new LeadNewsException("用户名或密码错误");
        }

        String token = AppJwtUtil.createToken(adUser.getId());
        adUser.setSalt(null);
        adUser.setPassword(null);

        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setToken(token);
        loginUserVo.setUser(adUser);
        return loginUserVo;
    }
}
