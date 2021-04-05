package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private Integer id;
    private String us_username;
    private String us_phone;
    private String us_password;
    private Boolean us_type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date us_registtime;
    private String us_email;

    public String getUs_username() {
        return us_username;
    }

    public void setUs_username(String us_username) {
        this.us_username = us_username;
    }

    public String getUs_phone() {
        return us_phone;
    }

    public void setUs_phone(String us_phone) {
        this.us_phone = us_phone;
    }

    public String getUs_password() {
        return us_password;
    }

    public void setUs_password(String us_password) {
        this.us_password = us_password;
    }

    public Boolean getUs_type() {
        return us_type;
    }

    public void setUs_type(Boolean us_type) {
        this.us_type = us_type;
    }

    public Date getUs_registtime() {
        return us_registtime;
    }

    public void setUs_registtime(Date us_registtime) {
        this.us_registtime = us_registtime;
    }

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", us_username='" + us_username + '\'' +
                ", us_phone='" + us_phone + '\'' +
                ", us_password='" + us_password + '\'' +
                ", us_type=" + us_type +
                ", us_registtime=" + us_registtime +
                ", us_email='" + us_email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Integer id, String us_username, String us_phone, String us_password, Boolean us_type, Date us_registtime, String us_email) {
        this.id = id;
        this.us_username = us_username;
        this.us_phone = us_phone;
        this.us_password = us_password;
        this.us_type = us_type;
        this.us_registtime = us_registtime;
        this.us_email = us_email;
    }
}
