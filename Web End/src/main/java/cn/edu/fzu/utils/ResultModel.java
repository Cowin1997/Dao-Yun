package cn.edu.fzu.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "基础返回类", description = "基础返回类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel<T> implements Serializable {
    // 状态码
    @ApiModelProperty(required = true, position = 1, value = "响应状态码{0:正常,-1:异常}", example = "0")
    private Integer code;
    // 业务提示语
    @ApiModelProperty(required = false, position = 3, value = "响应消息", example = "msg")
    private String msg;
    // 数据对象
    @ApiModelProperty(required = false, position = 2, value = "响应数据", example = "data")
    private T data;


}
