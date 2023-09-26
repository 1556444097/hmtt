package com.itheima.wemedia.controller;


import com.itheima.wemedia.pojo.WmMaterial;
import com.itheima.wemedia.service.WmMaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
 * @description <p>自媒体图文素材信息</p>
 *
 * @version 1.0
 * @package com.itheima.wemedia.controller
 */
@Api(value="WmMaterialController",tags = "自媒体图文素材信息")
@RestController
@RequestMapping("/wmMaterial")
public class WmMaterialController extends AbstractCoreController<WmMaterial> {

    private WmMaterialService wmMaterialService;

    @Autowired
    public WmMaterialController(WmMaterialService wmMaterialService) {
        super(wmMaterialService);
        this.wmMaterialService=wmMaterialService;
    }

}

