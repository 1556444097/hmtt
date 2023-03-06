package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.common.vo.PageResultVo;

public interface AdChannelService extends IService<AdChannel> {

    /**
     * @Description: 频道分页查询
     * @param dto
     * @return: com.itheima.common.vo.PageResultVo
     * @Date: 2023/3/5 0:00
     */
    PageResultVo findPage(ChannelPageRequestDto dto);

    /**
     * @Description: 新增频道
     * @param adChannel
     * @Date: 2023/3/6 22:55
     */
    void add(AdChannel adChannel);
}
