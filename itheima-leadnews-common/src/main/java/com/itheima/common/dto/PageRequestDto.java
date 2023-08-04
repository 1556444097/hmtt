package com.itheima.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * data transfer object
 * 请求数据
 */
@Data
@Slf4j
@ApiModel(value = "PageRequestDto", description = "通用分页查询参数")
public class PageRequestDto {

    @NotNull(message = "size error")
    @ApiModelProperty(value = "每页显示条数", required = true, dataType = "Long")
    private Long size;
    @NotNull(message = "page error")
    @ApiModelProperty(value = "当前页码", required = true, dataType = "Long")
    private Long page;

    public void checkParam() {
        if (this.page == null || this.page < 0) {
            setPage(1l);
        }
        if (this.size == null || this.size < 0 || this.size > 100) {
            setSize(10l);
        }
    }
}
