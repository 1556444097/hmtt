package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @version 1.0
 * @description 频道实体类
 * @package com.itheima.admin.pojo
 */
@Data
@TableName("ad_channel")
public class AdChannel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    // 添加校验规则, 非空校验
    @NotNull(message = "频道名称不能为空！")
    // 长度校验
    @Size(min = 4, max = 10,message = "频道名称必须是4到10个字之间！")
    private String name;

    @TableField("description")
    private String description;

    @TableField("is_default")
    private Boolean isDefault;

    @TableField("status")
    private Boolean status;

    @TableField("ord")
    private Integer ord;

    @TableField("created_time")
    private Date createdTime;
}
