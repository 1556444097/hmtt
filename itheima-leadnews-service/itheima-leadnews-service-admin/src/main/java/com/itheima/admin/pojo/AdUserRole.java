package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description <p>管理员角色信息 </p>
 *
 * @version 1.0
 * @package com.itheima.admin.pojo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_user_role")
@ApiModel(value="AdUserRole", description="管理员角色信息")
public class AdUserRole implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "角色ID", dataType="Integer")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(notes = "用户ID", dataType="Long")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(notes = "登录时间", dataType="LocalDateTime")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
