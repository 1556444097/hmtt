package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.mapper.AdChannelMapper;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.AdChannelService;
import com.itheima.common.vo.PageResultVo;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @create: 2023-03-05 00:01
 **/
@Service
@Transactional
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {
    /**
     * @Description: 频道分页查询
     * @param dto
     * @return: com.itheima.common.vo.PageResultVo
     * @Date: 2023/3/5 0:00
     */
    @Override
    public PageResultVo findPage(ChannelPageRequestDto dto) {

        IPage<AdChannel> pageInfo = new Page<>(dto.getPage(), dto.getSize());

        LambdaQueryWrapper<AdChannel> lqw = new LambdaQueryWrapper<>();

        lqw.like(!StringUtils.isEmpty(dto.getName()), AdChannel::getName, dto.getName());

        lqw.eq(dto.getStatus() != null, AdChannel::getStatus, dto.getStatus());

        IPage<AdChannel> pageResult = page(pageInfo, lqw);

        System.err.println("pageResult == pageInfo? => " + (pageResult == pageInfo));

        PageResultVo<AdChannel> vo = PageResultVo.pageResult(dto.getPage(), dto.getSize(), pageInfo.getTotal(), pageInfo.getRecords());

        return vo;
    }

    /**
     * @Description: 新增频道
     * @param adChannel
     * @Date: 2023/3/6 22:55
     */
    @Override
    public void add(AdChannel adChannel) {
        adChannel.setCreatedTime(new Date());
        adChannel.setIsDefault(false);

        save(adChannel);
    }
}
