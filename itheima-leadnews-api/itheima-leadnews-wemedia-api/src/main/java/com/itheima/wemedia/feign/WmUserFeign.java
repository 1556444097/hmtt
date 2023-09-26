package com.itheima.wemedia.feign;

import com.itheima.common.vo.ResultVo;
import com.itheima.wemedia.pojo.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 1.0
 * @description contextId: 默认使用的是微服名称，为了防止冲突，必须指定独立的id。在spring容器中的bean对象 id|name
 * fallbackFactory：熔断降级处理
 * @package com.itheima.wemedia.feign
 */
@FeignClient(name = "leadnews-wemedia", contextId = "wmUserFeign")
public interface WmUserFeign {

    /**
     * 通过ap_user_id查询自媒体账号信息
     * @param apUserId
     * @return
     */
    @GetMapping("/api/wmUser/{apUserId}")
    ResultVo<WmUser> getByApUserId(@PathVariable(value = "apUserId") Long apUserId);

    /**
     * 添加自媒体账号
     * @param wmUser
     * @return
     */
    @PostMapping("/api/wmUser/add")
    ResultVo<WmUser> addWmUser(@RequestBody WmUser wmUser);
}
