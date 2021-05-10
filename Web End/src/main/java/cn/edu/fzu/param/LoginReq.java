package cn.edu.fzu.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "LoginParam", description = "登录参数类")
public class LoginReq {

    @ApiModelProperty(required = false, position = 1, value = "用户名", example = "username")
    private String username;

    @ApiModelProperty(required = false, position = 2, value = "电话号码", example = "phone")
    private String phone;

    @ApiModelProperty(required = false, position = 3, value = "工号", example = "phone")
    private String uid;

    @NotNull
    @ApiModelProperty(required = true, position = 4, value = "密码", example = "password")
    private String password;
    @ApiModelProperty(required = false, position = 5, value = "验证码", example = "code")
    private String code;
    @NotNull
    @ApiModelProperty(required = false, position = 6, value = "用户类型:{ 1:超级管理员,2:普通管理员,3:教师,4:学生}", example = "1")
    private Integer type;

}
