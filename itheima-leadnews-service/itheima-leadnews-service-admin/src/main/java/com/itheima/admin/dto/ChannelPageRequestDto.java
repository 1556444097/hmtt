package com.itheima.admin.dto;

import com.itheima.common.dto.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @create: 2023-03-04 23:49
 * 频道分页查询条件
 **/
@Data
@ApiModel(description = "频道分页查询条件")
public class ChannelPageRequestDto extends PageRequestDto {
    @ApiModelProperty(notes = "频道名称，实现模糊查询",dataType = "String")
    private String name;
    @ApiModelProperty(notes = "频道状态，实现精确查询",dataType = "Integer")
    private Integer status;
}
