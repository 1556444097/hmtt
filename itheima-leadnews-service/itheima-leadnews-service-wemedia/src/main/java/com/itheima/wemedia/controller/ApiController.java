package com.itheima.wemedia.controller;

import com.itheima.common.vo.ResultVo;
import com.itheima.wemedia.pojo.WmUser;
import com.itheima.wemedia.service.WmUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @description 对外提供远程调用
 * @package com.itheima.wemedia.controller
 */
@RestController
public class ApiController {

    @Resource
    private WmUserService wmUserService;

    /**
     * 通过ap_user_id查询自媒体账号信息
     * @param apUserId
     * @return
     */
    @GetMapping("/api/wmUser/{apUserId}")
    public ResultVo<WmUser> getByApUserId(@PathVariable(value = "apUserId") Long apUserId){
        WmUser wmUser = wmUserService.findByApUserId(apUserId);
        return ResultVo.ok(wmUser);
    }

    /**
     * 添加自媒体账号
     * @param wmUser
     * @return
     */
    @PostMapping("/api/wmUser/add")
    public ResultVo<WmUser> addWmUser(@RequestBody WmUser wmUser){
        wmUserService.save(wmUser);
        return ResultVo.ok(wmUser);
    }
}
