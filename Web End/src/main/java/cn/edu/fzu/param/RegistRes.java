package cn.edu.fzu.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class RegistRes {
    @ApiModelProperty(value = "教工号/学号", example = "123456")
    private String indentity;

}
