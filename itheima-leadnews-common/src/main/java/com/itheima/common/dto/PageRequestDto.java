package com.itheima.common.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * data transfer object
 * 请求数据
 */
@Data
@Slf4j
public class PageRequestDto {

    private Long size;
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
