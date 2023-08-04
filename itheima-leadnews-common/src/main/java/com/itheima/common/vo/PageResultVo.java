package com.itheima.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * VO Value Object | View Object
 * 响应给客户端
 * @version 1.0
 * @description 封装分页结果
 * @package com.itheima.common.vo
 */
@Data
@ApiModel(value = "PageResultVo", description = "通用分页响应结果")
public class PageResultVo<T> extends ResultVo<T> implements Serializable {
    @ApiModelProperty(value = "当前页码", required = true, dataType = "Long")
    private Long currentPage;
    @ApiModelProperty(value = "每页显示条数", required = true, dataType = "Long")
    private Long size;
    @ApiModelProperty(value = "总条数", required = true, dataType = "Long")
    private Long total;


    /**
     * 快速构建分页结果
     * @param page
     * @param size
     * @param total
     * @param data
     * @return
     */
    public static <T>PageResultVo<T> pageResult(Long page, Long size, Long total, List<T> data){
        PageResultVo vo = new PageResultVo();
        vo.setCurrentPage(page);
        vo.setSize(size);
        vo.setTotal(total);
        vo.setData(data);
        return vo;
    }
}
