package com.itheima.admin.controller;

import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.AdChannelService;
import com.itheima.common.vo.PageResultVo;
import com.itheima.common.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @create: 2023-03-04 23:52
 **/
@RestController
@RequestMapping("/channel")
@Api(tags = "频道管理")
public class AdChannelController {


    @Resource
    private AdChannelService adChannelService;

    /**
     * @Description: 频道分页查询
     * @param dto
     * @return: com.itheima.common.vo.PageResultVo
     * @Date: 2023/3/4 23:54
     */
    @PostMapping("/list")
    @ApiOperation("频道分页查询")
    public PageResultVo list(@RequestBody ChannelPageRequestDto dto) {

        PageResultVo vo = adChannelService.findPage(dto);

        return vo;

    }

    /**
     * @Description: 新增频道
     * @param adChannel
     * @return: com.itheima.common.vo.ResultVo
     * @Date: 2023/3/6 22:53
     */
    @PostMapping("/save")
    @ApiOperation("新增频道")
    public ResultVo add(@RequestBody @Valid AdChannel adChannel) {
        adChannelService.add(adChannel);
        return ResultVo.ok("操作成功！");
    }

    /**
     * @Description: 通过id查询
     * @param id
     * @return: com.itheima.common.vo.ResultVo
     * @Date: 2023/3/6 23:06
     */
    @GetMapping("/one/{id}")
    @ApiOperation("通过id查询")
    public ResultVo findById(@PathVariable(value = "id") Integer id) {
        AdChannel adChannel = adChannelService.getById(id);
        return ResultVo.ok(adChannel);
    }

    /**
     * @Description: 修改频道
     * @param adChannel
     * @return: com.itheima.common.vo.ResultVo
     * @Date: 2023/3/6 23:09
     */
    @PostMapping("/update")
    @ApiOperation("修改频道")
    public ResultVo update(@RequestBody AdChannel adChannel) {
        adChannelService.updateById(adChannel);
        return ResultVo.ok("操作成功！");
    }

    /**
     * @Description: 通过id删除
     * @param id
     * @return: com.itheima.common.vo.ResultVo
     * @Date: 2023/3/6 23:16
     */
    @DeleteMapping("/del/{id}")
    @ApiOperation("通过id删除")
    public ResultVo deleteById(@PathVariable(value = "id") Integer id) {
        adChannelService.removeById(id);
        return ResultVo.ok("操作成功！");
    }


}
