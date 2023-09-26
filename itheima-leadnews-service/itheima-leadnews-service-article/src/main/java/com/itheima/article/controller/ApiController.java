package com.itheima.article.controller;

import com.itheima.article.pojo.ApAuthor;
import com.itheima.article.service.ApAuthorService;
import com.itheima.common.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @description 说明
 * @package com.itheima.article.controller
 */
@RestController
public class ApiController {

    @Resource
    private ApAuthorService apAuthorService;

    /**
     * 通过ap_user_id与wm_user_id查询作者信息
     *
     * @param apUserId
     * @param wmUserId
     * @return
     */
    @GetMapping("/api/apAuthor/{apUserId}/{wmUserId}")
    public ResultVo getByApUserIdWmUserId(@PathVariable(value = "apUserId") Long apUserId,
                                          @PathVariable(value = "wmUserId") Long wmUserId){
        ApAuthor author = apAuthorService.getByApUserIdWmUserId(apUserId, wmUserId);
        return ResultVo.ok(author);
    }

    /**
     * 添加作者
     * @param apAuthor
     * @return
     */
    @PostMapping("/api/apAuthor/add")
    public ResultVo addAuthor(@RequestBody ApAuthor apAuthor){
        apAuthorService.save(apAuthor);
        return ResultVo.ok("操作成功!");
    }
}
