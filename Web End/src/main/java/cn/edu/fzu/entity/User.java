package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String us_username;
    private String us_phone;
    private String us_password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date us_registtime;
    private String us_email;
    private Integer us_roleid;
    private Boolean us_status;
    private String us_id;

}
