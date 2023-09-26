package com.itheima.article.feign;

import com.itheima.article.pojo.ApAuthor;
import com.itheima.common.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 1.0
 * @description 说明
 * @package com.itheima.article.feign
 */
@FeignClient(name = "leadnews-article", contextId = "apAuthorFeign")
public interface ApAuthorFeign {

    /**
     * 通过ap_user_id与wm_user_id查询作者信息
     * @param apUserId
     * @param wmUserId
     * @return
     */
    @GetMapping("/api/apAuthor/{apUserId}/{wmUserId}")
    ResultVo<ApAuthor> getByApUserIdWmUserId(@PathVariable(value = "apUserId") Long apUserId,
                                             @PathVariable(value = "wmUserId") Long wmUserId);

    /**
     * 添加作者
     * @param apAuthor
     * 如果没有指定返回数据的泛型类型，消费者端在反序列化时，转成LinkedHashMap<String,Object>
     * @return
     */
    @PostMapping("/api/apAuthor/add")
    ResultVo addAuthor(@RequestBody ApAuthor apAuthor);
}
