package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdVistorStatistics;
import com.itheima.admin.service.AdVistorStatisticsService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description <p>访问数据统计</p>
 *
 * @version 1.0
 * @package com.itheima.admin.controller
 */
@Api(value="AdVistorStatisticsController",tags = "访问数据统计")
@RestController
@RequestMapping("/adVistorStatistics")
public class AdVistorStatisticsController extends AbstractCoreController<AdVistorStatistics> {

    private AdVistorStatisticsService adVistorStatisticsService;

    @Autowired
    public AdVistorStatisticsController(AdVistorStatisticsService adVistorStatisticsService) {
        super(adVistorStatisticsService);
        this.adVistorStatisticsService=adVistorStatisticsService;
    }

}

