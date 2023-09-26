package com.itheima.article.service;

import com.itheima.article.pojo.ApAuthor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description <p>APP文章作者信息 业务接口</p>
 *
 * @version 1.0
 * @package com.itheima.article.service
 */
public interface ApAuthorService extends IService<ApAuthor> {

    /**
     * 通过ap_user_id与wm_user_id查询作者信息
     * @param apUserId
     * @param wmUserId
     * @return
     */
    ApAuthor getByApUserIdWmUserId(Long apUserId, Long wmUserId);
}