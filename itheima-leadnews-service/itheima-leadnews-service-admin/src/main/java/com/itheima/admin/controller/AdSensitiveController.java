package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdSensitive;
import com.itheima.admin.service.AdSensitiveService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description <p>敏感词信息</p>
 *
 * @version 1.0
 * @package com.itheima.admin.controller
 */
@Api(value="AdSensitiveController",tags = "敏感词信息")
@RestController
@RequestMapping("/adSensitive")
public class AdSensitiveController extends AbstractCoreController<AdSensitive> {

    private AdSensitiveService adSensitiveService;

    @Autowired
    public AdSensitiveController(AdSensitiveService adSensitiveService) {
        super(adSensitiveService);
        this.adSensitiveService=adSensitiveService;
    }

}

