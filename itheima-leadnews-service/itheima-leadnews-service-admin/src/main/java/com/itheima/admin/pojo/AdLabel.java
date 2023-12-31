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
 * @description <p>标签信息 </p>
 *
 * @version 1.0
 * @package com.itheima.admin.pojo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_label")
@ApiModel(value="AdLabel", description="标签信息")
public class AdLabel implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "频道名称", dataType="String")
    @TableField("name")
    private String name;

    @ApiModelProperty(notes = "0:系统增加 1:人工增加", dataType="Integer")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(notes = "创建时间", dataType="LocalDateTime")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
