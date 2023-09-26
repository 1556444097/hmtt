package com.itheima.wemedia.service;

import com.itheima.wemedia.pojo.WmUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description <p>自媒体用户信息 业务接口</p>
 *
 * @version 1.0
 * @package com.itheima.wemedia.service
 */
public interface WmUserService extends IService<WmUser> {

    /**
     * 通过ap_user_id查询自媒体账号信息
     * @param apUserId
     * @return
     */
    WmUser findByApUserId(Long apUserId);
}
