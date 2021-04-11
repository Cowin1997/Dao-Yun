package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private Integer id;
    private String us_username;
    private String us_phone;
    private String us_password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date us_registtime;
    private String us_email;
    private Integer us_roleid;
    private Boolean us_status;
    private Integer us_relate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", us_username='" + us_username + '\'' +
                ", us_phone='" + us_phone + '\'' +
                ", us_password='" + us_password + '\'' +
                ", us_registtime=" + us_registtime +
                ", us_email='" + us_email + '\'' +
                ", us_roleid=" + us_roleid +
                ", us_status=" + us_status +
                ", us_relate=" + us_relate +
                '}';
    }

    public Integer getUs_relate() {
        return us_relate;
    }

    public void setUs_relate(Integer us_relate) {
        this.us_relate = us_relate;
    }

    public Boolean getUs_status() {
        return us_status;
    }

    public void setUs_status(Boolean us_status) {
        this.us_status = us_status;
    }

    public Integer getUs_roleid() {
        return us_roleid;
    }

    public void setUs_roleid(Integer us_roleid) {
        this.us_roleid = us_roleid;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
