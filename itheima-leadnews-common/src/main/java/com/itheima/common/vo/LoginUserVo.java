package com.itheima.common.vo;

import lombok.Data;

/**
 * @version 1.0
 * @description 说明
 * @package com.itheima.common.vo
 */
@Data
public class LoginUserVo {
    // 返回的token
    private String token;
    // 返回的登录用户的信息
    // 为什么不指定类型？原因:app登录（ApUser）、admin也要登录(AdUser)、
    // 自媒体也有登录 WmUser。为了能接收这3个类型的对象，所以使用了Object
    private Object user;
}
