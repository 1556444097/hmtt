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
 * @description <p>角色信息 </p>
 *
 * @version 1.0
 * @package com.itheima.admin.pojo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_role")
@ApiModel(value="AdRole", description="角色信息")
public class AdRole implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "角色名称", dataType="String")
    @TableField("name")
    private String name;

    @ApiModelProperty(notes = "角色描述", dataType="String")
    @TableField("description")
    private String description;

    @ApiModelProperty(notes = "是否有效", dataType="Integer")
    @TableField("is_enable")
    private Integer isEnable;

    @ApiModelProperty(notes = "登录时间", dataType="LocalDateTime")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
