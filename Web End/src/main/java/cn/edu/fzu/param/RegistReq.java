package cn.edu.fzu.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
@AllArgsConstructor
public class RegistReq {
    @ApiModelProperty(required = true, value="身份类型{3:老师,4:学生}",example="1")
    private Integer type;
    @ApiModelProperty(required = true, value="手机号码",example="130xxx456")
    private String phone;

    @ApiModelProperty(required = true, value="验证码",example="4856")
    private String msgcode;

    @ApiModelProperty(required = true,value="教工号/学号",example="123456")
    private String identity;

    @ApiModelProperty(required = true,value="密码",example="123456")
    private String password;

    @ApiModelProperty(required = false,value="用户名",example="123456")
    private String username;

    @ApiModelProperty(required = false,value="邮箱",example="123456@qq.com")
    private String email;

    @ApiModelProperty(hidden=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date registTime;

    @ApiModelProperty(hidden=true)
    private Boolean status;

}
