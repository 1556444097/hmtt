package com.itheima.wemedia.service.impl;

import com.itheima.wemedia.pojo.WmUser;
import com.itheima.wemedia.mapper.WmUserMapper;
import com.itheima.wemedia.service.WmUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description <p>自媒体用户信息 业务实现</p>
 *
 * @version 1.0
 * @package com.itheima.wemedia.service.impl
 */
@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {

    /**
     * 通过ap_user_id查询自媒体账号信息
     *
     * @param apUserId
     * @return
     */
    @Override
    public WmUser findByApUserId(Long apUserId) {
        return query().eq("ap_user_id", apUserId).one();
    }
}
