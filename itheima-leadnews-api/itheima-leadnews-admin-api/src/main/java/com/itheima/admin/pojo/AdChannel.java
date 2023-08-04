package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @ApiModelProperty(value = "主键", dataType = "Integer")
    private Integer id;

    @TableField("name")
    // 添加校验规则, 非空校验
    @NotNull(message = "频道名称不能为空！")
    // 长度校验
    @Size(min = 4, max = 10,message = "频道名称必须是4到10个字之间！")
    @ApiModelProperty(value = "频道名称", dataType = "String")
    private String name;

    @TableField("description")
    @ApiModelProperty(value = "频道描述", dataType = "String")
    private String description;

    @TableField("is_default")
    @ApiModelProperty(value = "是否默认", dataType = "Boolean")
    private Boolean isDefault;

    @TableField("status")
    @ApiModelProperty(value = "状态", dataType = "Boolean")
    private Boolean status;

    @TableField("ord")
    @ApiModelProperty(value = "排序", dataType = "Integer")
    private Integer ord;

    @TableField("created_time")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date createdTime;
}
