package com.itheima.common.vo;

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
public class PageResultVo<T> extends ResultVo<T> implements Serializable {
    private Long currentPage;
    private Long size;
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
