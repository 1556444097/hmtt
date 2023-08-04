package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.pojo.AdUser;
import com.itheima.common.dto.LoginUserDto;
import com.itheima.common.vo.LoginUserVo;

/**
 * @description <p>管理员用户信息 业务接口</p>
 *
 * @version 1.0
 * @package com.itheima.admin.service
 */
public interface AdUserService extends IService<AdUser> {

    /**
     * 登录校验
     * @param dto
     * @return
     */
    LoginUserVo loginCheck(LoginUserDto dto);
}
