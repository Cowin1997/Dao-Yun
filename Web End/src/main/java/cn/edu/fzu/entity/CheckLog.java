package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CheckLog {
    private Integer id;
    private Integer ch_checksid;
    private String  ch_checksname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date ch_checktime;
    private String ch_checkloc;
    private Integer ch_checkscore;
    private Integer ch_checkcount;
    private Integer ch_currentscore;
    private Integer ch_checkcouseid;

    @Override
    public String toString() {
        return "CheckLog{" +
                "id=" + id +
                ", ch_checksid=" + ch_checksid +
                ", ch_checksname='" + ch_checksname + '\'' +
                ", ch_checktime=" + ch_checktime +
                ", ch_checkloc='" + ch_checkloc + '\'' +
                ", ch_checkscore=" + ch_checkscore +
                ", ch_checkcount=" + ch_checkcount +
                ", ch_currentscore=" + ch_currentscore +
                ", ch_checkcouseid=" + ch_checkcouseid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCh_checksid() {
        return ch_checksid;
    }

    public void setCh_checksid(Integer ch_checksid) {
        this.ch_checksid = ch_checksid;
    }

    public String getCh_checksname() {
        return ch_checksname;
    }

    public void setCh_checksname(String ch_checksname) {
        this.ch_checksname = ch_checksname;
    }

    public Date getCh_checktime() {
        return ch_checktime;
    }

    public void setCh_checktime(Date ch_checktime) {
        this.ch_checktime = ch_checktime;
    }

    public String getCh_checkloc() {
        return ch_checkloc;
    }

    public void setCh_checkloc(String ch_checkloc) {
        this.ch_checkloc = ch_checkloc;
    }

    public Integer getCh_checkscore() {
        return ch_checkscore;
    }

    public void setCh_checkscore(Integer ch_checkscore) {
        this.ch_checkscore = ch_checkscore;
    }

    public Integer getCh_checkcount() {
        return ch_checkcount;
    }

    public void setCh_checkcount(Integer ch_checkcount) {
        this.ch_checkcount = ch_checkcount;
    }

    public Integer getCh_currentscore() {
        return ch_currentscore;
    }

    public void setCh_currentscore(Integer ch_currentscore) {
        this.ch_currentscore = ch_currentscore;
    }

    public Integer getCh_checkcouseid() {
        return ch_checkcouseid;
    }

    public void setCh_checkcouseid(Integer ch_checkcouseid) {
        this.ch_checkcouseid = ch_checkcouseid;
    }

    public CheckLog(Integer id, Integer ch_checksid, String ch_checksname, Date ch_checktime, String ch_checkloc, Integer ch_checkscore, Integer ch_checkcount, Integer ch_currentscore, Integer ch_checkcouseid) {
        this.id = id;
        this.ch_checksid = ch_checksid;
        this.ch_checksname = ch_checksname;
        this.ch_checktime = ch_checktime;
        this.ch_checkloc = ch_checkloc;
        this.ch_checkscore = ch_checkscore;
        this.ch_checkcount = ch_checkcount;
        this.ch_currentscore = ch_currentscore;
        this.ch_checkcouseid = ch_checkcouseid;
    }
}
