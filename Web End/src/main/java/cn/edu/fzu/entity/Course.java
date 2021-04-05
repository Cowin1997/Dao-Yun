package cn.edu.fzu.entity;

import java.util.Date;

public class Course {
    private Integer id;
    private Integer cl_classid;
    private String cl_classname;
    private Integer cl_teachid;
    private String cl_teachname;
    private String cl_classloc;
    private Date cl_begin;
    private Date cl_end;
    private String cl_info;
    private Date cl_createtime;
    private Integer cl_creatorid;
    private String cl_creatorname;


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cl_classid=" + cl_classid +
                ", cl_classname='" + cl_classname + '\'' +
                ", cl_teachid=" + cl_teachid +
                ", cl_teachname='" + cl_teachname + '\'' +
                ", cl_classloc='" + cl_classloc + '\'' +
                ", cl_begin=" + cl_begin +
                ", cl_end=" + cl_end +
                ", cl_info='" + cl_info + '\'' +
                ", cl_createtime=" + cl_createtime +
                ", cl_creatorid=" + cl_creatorid +
                ", cl_creatorname='" + cl_creatorname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCl_classid() {
        return cl_classid;
    }

    public void setCl_classid(Integer cl_classid) {
        this.cl_classid = cl_classid;
    }

    public String getCl_classname() {
        return cl_classname;
    }

    public void setCl_classname(String cl_classname) {
        this.cl_classname = cl_classname;
    }

    public Integer getCl_teachid() {
        return cl_teachid;
    }

    public void setCl_teachid(Integer cl_teachid) {
        this.cl_teachid = cl_teachid;
    }

    public String getCl_teachname() {
        return cl_teachname;
    }

    public void setCl_teachname(String cl_teachname) {
        this.cl_teachname = cl_teachname;
    }

    public String getCl_classloc() {
        return cl_classloc;
    }

    public void setCl_classloc(String cl_classloc) {
        this.cl_classloc = cl_classloc;
    }

    public Date getCl_begin() {
        return cl_begin;
    }

    public void setCl_begin(Date cl_begin) {
        this.cl_begin = cl_begin;
    }

    public Date getCl_end() {
        return cl_end;
    }

    public void setCl_end(Date cl_end) {
        this.cl_end = cl_end;
    }

    public String getCl_info() {
        return cl_info;
    }

    public void setCl_info(String cl_info) {
        this.cl_info = cl_info;
    }

    public Date getCl_createtime() {
        return cl_createtime;
    }

    public void setCl_createtime(Date cl_createtime) {
        this.cl_createtime = cl_createtime;
    }

    public Integer getCl_creatorid() {
        return cl_creatorid;
    }

    public void setCl_creatorid(Integer cl_creatorid) {
        this.cl_creatorid = cl_creatorid;
    }

    public String getCl_creatorname() {
        return cl_creatorname;
    }

    public void setCl_creatorname(String cl_creatorname) {
        this.cl_creatorname = cl_creatorname;
    }

    public Course(Integer id, Integer cl_classid, String cl_classname, Integer cl_teachid, String cl_teachname, String cl_classloc, Date cl_begin, Date cl_end, String cl_info, Date cl_createtime, Integer cl_creatorid, String cl_creatorname) {
        this.id = id;
        this.cl_classid = cl_classid;
        this.cl_classname = cl_classname;
        this.cl_teachid = cl_teachid;
        this.cl_teachname = cl_teachname;
        this.cl_classloc = cl_classloc;
        this.cl_begin = cl_begin;
        this.cl_end = cl_end;
        this.cl_info = cl_info;
        this.cl_createtime = cl_createtime;
        this.cl_creatorid = cl_creatorid;
        this.cl_creatorname = cl_creatorname;
    }
}
