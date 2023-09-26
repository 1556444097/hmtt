package com.itheima.user.controller;


import com.itheima.common.vo.PageResultVo;
import com.itheima.common.vo.ResultVo;
import com.itheima.user.dto.ApUserRealnameAuthDto;
import com.itheima.user.dto.ApUserRealnamePageRequestDto;
import com.itheima.user.pojo.ApUserRealname;
import com.itheima.user.service.ApUserRealnameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
 * @description <p>APP实名认证信息</p>
 *
 * @version 1.0
 * @package com.itheima.user.controller
 */
@Api(value="ApUserRealnameController",tags = "APP实名认证信息")
@RestController
@RequestMapping("/auth")
public class ApUserRealnameController extends AbstractCoreController<ApUserRealname> {

    private ApUserRealnameService apUserRealnameService;

    @Autowired
    public ApUserRealnameController(ApUserRealnameService apUserRealnameService) {
        super(apUserRealnameService);
        this.apUserRealnameService=apUserRealnameService;
    }

    /**
     * 实名认证列表 分页查询
     */
    @PostMapping("list")
    public PageResultVo list(@RequestBody ApUserRealnamePageRequestDto dto) {
        return apUserRealnameService.findPage(dto);
    }

    /**
     * 实名认证 通过
     * @return
     */
    @PostMapping("authPass")
    public ResultVo authPass(@RequestBody ApUserRealnameAuthDto dto){
        apUserRealnameService.authPass(dto);
        return ResultVo.ok("操作成功!");
    }

    /**
     * 实名认证 驳回
     * @return
     */
    @PostMapping("authFail")
    public ResultVo authFail(@RequestBody ApUserRealnameAuthDto dto){
        apUserRealnameService.authFail(dto);
        return ResultVo.ok("操作成功!");
    }
}

