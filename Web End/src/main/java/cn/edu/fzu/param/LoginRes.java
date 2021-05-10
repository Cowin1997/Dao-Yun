package cn.edu.fzu.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel
@Data
@AllArgsConstructor
public class LoginRes {
    @ApiModelProperty(value = "登录成功返回token", example = "a.b.c")
    public String token;
    @ApiModelProperty(value = "登录成功返回,身份类型{1:超级管理员,2:普通管理员,3:教师,4:学生")
    public Integer type;
    @ApiModelProperty(value = "登录成功返回工号")
    public String uid;
}
