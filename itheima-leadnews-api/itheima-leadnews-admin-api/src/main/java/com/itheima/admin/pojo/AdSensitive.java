package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("ad_sensitive")
@ApiModel(description = "敏感词")
public class AdSensitive implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(notes = "主键id",dataType="int")
    private Integer id;

    /**
     * 敏感词
     */
    @TableField("sensitives")
    @ApiModelProperty(notes = "敏感词",dataType="String")
    private String sensitives;

    /**
     * 创建时间
     */
    @TableField("created_time")
    @ApiModelProperty(notes = "创建时间",dataType="local date time")
    private LocalDateTime createdTime;

}