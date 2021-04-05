package cn.edu.fzu.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Admin {

    private Integer id;
    private String ad_username;
    private String ad_password;
    private String ad_lastpassword;
    private String ad_phone;
    private String ad_email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date ad_lastlogintime;
    private String ad_lastloginip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date ad_createtime;
    private Integer ad_createid;
    private Boolean ad_issuperadmin;
    private Boolean ad_accountstatus;

    public Admin(Integer id, String ad_username, String ad_password, String ad_lastpassword, String ad_phone, String ad_email, Date ad_lastlogintime, String ad_lastloginip, Date ad_createtime, Integer ad_createid, Boolean ad_issuperadmin, Boolean ad_accountstatus) {
        this.id = id;
        this.ad_username = ad_username;
        this.ad_password = ad_password;
        this.ad_lastpassword = ad_lastpassword;
        this.ad_phone = ad_phone;
        this.ad_email = ad_email;
        this.ad_lastlogintime = ad_lastlogintime;
        this.ad_lastloginip = ad_lastloginip;
        this.ad_createtime = ad_createtime;
        this.ad_createid = ad_createid;
        this.ad_issuperadmin = ad_issuperadmin;
        this.ad_accountstatus = ad_accountstatus;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", ad_username='" + ad_username + '\'' +
                ", ad_password='" + ad_password + '\'' +
                ", ad_lastpassword='" + ad_lastpassword + '\'' +
                ", ad_phone='" + ad_phone + '\'' +
                ", ad_email='" + ad_email + '\'' +
                ", ad_lastlogintime=" + ad_lastlogintime +
                ", ad_lastloginip='" + ad_lastloginip + '\'' +
                ", ad_createtime=" + ad_createtime +
                ", ad_createid=" + ad_createid +
                ", ad_issuperadmin=" + ad_issuperadmin +
                ", ad_accountstatus=" + ad_accountstatus +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd_username() {
        return ad_username;
    }

    public void setAd_username(String ad_username) {
        this.ad_username = ad_username;
    }

    public String getAd_password() {
        return ad_password;
    }

    public void setAd_password(String ad_password) {
        this.ad_password = ad_password;
    }

    public String getAd_lastpassword() {
        return ad_lastpassword;
    }

    public void setAd_lastpassword(String ad_lastpassword) {
        this.ad_lastpassword = ad_lastpassword;
    }

    public String getAd_phone() {
        return ad_phone;
    }

    public void setAd_phone(String ad_phone) {
        this.ad_phone = ad_phone;
    }

    public String getAd_email() {
        return ad_email;
    }

    public void setAd_email(String ad_email) {
        this.ad_email = ad_email;
    }

    public Date getAd_lastlogintime() {
        return ad_lastlogintime;
    }

    public void setAd_lastlogintime(Date ad_lastlogintime) {
        this.ad_lastlogintime = ad_lastlogintime;
    }

    public String getAd_lastloginip() {
        return ad_lastloginip;
    }

    public void setAd_lastloginip(String ad_lastloginip) {
        this.ad_lastloginip = ad_lastloginip;
    }

    public Date getAd_createtime() {
        return ad_createtime;
    }

    public void setAd_createtime(Date ad_createtime) {
        this.ad_createtime = ad_createtime;
    }

    public Integer getAd_createid() {
        return ad_createid;
    }

    public void setAd_createid(Integer ad_createid) {
        this.ad_createid = ad_createid;
    }

    public Boolean getAd_issuperadmin() {
        return ad_issuperadmin;
    }

    public void setAd_issuperadmin(Boolean ad_issuperadmin) {
        this.ad_issuperadmin = ad_issuperadmin;
    }

    public Boolean getAd_accountstatus() {
        return ad_accountstatus;
    }

    public void setAd_accountstatus(Boolean ad_accountstatus) {
        this.ad_accountstatus = ad_accountstatus;
    }
}
