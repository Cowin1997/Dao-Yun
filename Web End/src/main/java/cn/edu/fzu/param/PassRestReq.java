package cn.edu.fzu.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class PassRestReq {
    @ApiModelProperty(value = "账户id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "旧密码", example = "oldPass")
    private String oldPass;
    @ApiModelProperty(value = "新密码", example = "newPass")
    private String newPass;
}
