package com.itheima.common.vo;

import com.itheima.common.enums.HttpCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;

/**
 * @version 1.0
 * @description 说明
 * @package com.itheima.common.vo
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ResultVo", description = "通用响应结果")
public class ResultVo<T> implements Serializable {
    @ApiModelProperty(value = "请求响应域名", required = true, dataType = "String")
    private String host;
    @ApiModelProperty(value = "请求响应码", required = true, dataType = "Integer")
    private Integer code;
    @ApiModelProperty(value = "请求响应消息", required = true, dataType = "String")
    private String errorMessage;
    @ApiModelProperty(value = "请求响应数据", required = true, dataType = "JSON")
    private T data;

    public ResultVo() {
        this.code = HttpCodeEnum.SUCCESS.getCode();
    }

    public static ResultVo ok() {
        return new ResultVo();
    }

    public static ResultVo ok(String msg) {
        return new ResultVo(null,HttpCodeEnum.SUCCESS.getCode(),msg,null);
    }

    public static ResultVo ok(Object data) {
        return new ResultVo(null,HttpCodeEnum.SUCCESS.getCode(),HttpCodeEnum.SUCCESS.getMessage(),data);
    }
    public static ResultVo error() {
        return ResultVo.error(HttpCodeEnum.SERVER_ERROR);
    }
    public static ResultVo error(int code, String msg) {
        return new ResultVo(null,code,msg,null);
    }
    public static ResultVo error(HttpCodeEnum enums){
        return ResultVo.error(enums.getCode(),enums.getMessage());
    }
    public static ResultVo bizError(String msg) {
        return ResultVo.error(HttpCodeEnum.SERVER_ERROR.getCode(), msg);
    }

    public boolean isSuccess(){
        return this.code!=null&&this.code.intValue()==HttpCodeEnum.SUCCESS.getCode();
    }
}
