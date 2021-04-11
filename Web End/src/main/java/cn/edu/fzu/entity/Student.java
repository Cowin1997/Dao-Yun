package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Student {
    private Integer id;
    private String st_id;
    private String st_name;
    private String st_sex;
    private String st_phone;
    private String st_email;
    private Integer st_schoolcode;
    private Integer st_collegecode;
    private Integer st_majorcode;
    private Integer st_classid;
    private Integer st_checkscore;
    private Integer st_checkcount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date st_lastchecktime;
    private String st_lastcheckloc;
    private String st_checklevel;
    private String st_info;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", st_id='" + st_id + '\'' +
                ", st_name='" + st_name + '\'' +
                ", st_sex='" + st_sex + '\'' +
                ", st_phone='" + st_phone + '\'' +
                ", st_email='" + st_email + '\'' +
                ", st_schoolcode=" + st_schoolcode +
                ", st_collegecode=" + st_collegecode +
                ", st_majorcode=" + st_majorcode +
                ", st_classid=" + st_classid +
                ", st_checkscore=" + st_checkscore +
                ", st_checkcount=" + st_checkcount +
                ", st_lastchecktime=" + st_lastchecktime +
                ", st_lastcheckloc='" + st_lastcheckloc + '\'' +
                ", st_checklevel='" + st_checklevel + '\'' +
                ", st_info='" + st_info + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_sex() {
        return st_sex;
    }

    public void setSt_sex(String st_sex) {
        this.st_sex = st_sex;
    }

    public String getSt_phone() {
        return st_phone;
    }

    public void setSt_phone(String st_phone) {
        this.st_phone = st_phone;
    }

    public String getSt_email() {
        return st_email;
    }

    public void setSt_email(String st_email) {
        this.st_email = st_email;
    }

    public Integer getSt_schoolcode() {
        return st_schoolcode;
    }

    public void setSt_schoolcode(Integer st_schoolcode) {
        this.st_schoolcode = st_schoolcode;
    }

    public Integer getSt_collegecode() {
        return st_collegecode;
    }

    public void setSt_collegecode(Integer st_collegecode) {
        this.st_collegecode = st_collegecode;
    }

    public Integer getSt_majorcode() {
        return st_majorcode;
    }

    public void setSt_majorcode(Integer st_majorcode) {
        this.st_majorcode = st_majorcode;
    }

    public Integer getSt_classid() {
        return st_classid;
    }

    public void setSt_classid(Integer st_classid) {
        this.st_classid = st_classid;
    }

    public Integer getSt_checkscore() {
        return st_checkscore;
    }

    public void setSt_checkscore(Integer st_checkscore) {
        this.st_checkscore = st_checkscore;
    }

    public Integer getSt_checkcount() {
        return st_checkcount;
    }

    public void setSt_checkcount(Integer st_checkcount) {
        this.st_checkcount = st_checkcount;
    }

    public Date getSt_lastchecktime() {
        return st_lastchecktime;
    }

    public void setSt_lastchecktime(Date st_lastchecktime) {
        this.st_lastchecktime = st_lastchecktime;
    }

    public String getSt_lastcheckloc() {
        return st_lastcheckloc;
    }

    public void setSt_lastcheckloc(String st_lastcheckloc) {
        this.st_lastcheckloc = st_lastcheckloc;
    }

    public String getSt_checklevel() {
        return st_checklevel;
    }

    public void setSt_checklevel(String st_checklevel) {
        this.st_checklevel = st_checklevel;
    }

    public String getSt_info() {
        return st_info;
    }

    public void setSt_info(String st_info) {
        this.st_info = st_info;
    }

    public Student(Integer id, String st_id, String st_name, String st_sex, String st_phone, String st_email, Integer st_schoolcode, Integer st_collegecode, Integer st_majorcode, Integer st_classid, Integer st_checkscore, Integer st_checkcount, Date st_lastchecktime, String st_lastcheckloc, String st_checklevel, String st_info) {
        this.id = id;
        this.st_id = st_id;
        this.st_name = st_name;
        this.st_sex = st_sex;
        this.st_phone = st_phone;
        this.st_email = st_email;
        this.st_schoolcode = st_schoolcode;
        this.st_collegecode = st_collegecode;
        this.st_majorcode = st_majorcode;
        this.st_classid = st_classid;
        this.st_checkscore = st_checkscore;
        this.st_checkcount = st_checkcount;
        this.st_lastchecktime = st_lastchecktime;
        this.st_lastcheckloc = st_lastcheckloc;
        this.st_checklevel = st_checklevel;
        this.st_info = st_info;
    }

    public Student() {
    }
}
