package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysParam {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "系统参数键")
    private String arg_key;
    @ApiModelProperty(value = "系统参数值")
    private String arg_value;
    @ApiModelProperty(value = "系统参数说明")
    private String arg_desc;
}
