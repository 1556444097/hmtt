package com.itheima.admin.controller;

import com.itheima.admin.service.AdUserService;
import com.itheima.common.dto.LoginUserDto;
import com.itheima.common.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private AdUserService adUserService;

    /**
     * 登录校验
     */
    @PostMapping("/in")
    public ResultVo loginCheck(@RequestBody @Valid LoginUserDto dto) {
        return ResultVo.ok(adUserService.loginCheck(dto));
    }
}
