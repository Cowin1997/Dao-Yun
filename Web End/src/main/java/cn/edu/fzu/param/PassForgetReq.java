package cn.edu.fzu.param;

import io.swagger.annotations.Api;
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
public class PassForgetReq {
    @ApiModelProperty(value = "电话号码",required = true)
    @NotNull
    private String phone;
    @ApiModelProperty(value = "短信验证码",required = true)
    @NotNull
    private String code;
    @ApiModelProperty(value = "新密码",required = true)
    @NotNull
    private String newPass;
    @ApiModelProperty(value = "重复新密码",required = true)
    @NotNull
    private String newPassAgain;
}
