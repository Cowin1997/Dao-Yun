package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(value = {"handler"})
public class CheckTask {
    private Integer id;
    private Integer class_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date create_time;
    private String password;
    private Integer time_limit;
    private Integer teacher_id;
    private Integer type;
    private String info;
    private Course course;
    private Teacher teacher;
    private Boolean status;
    private Boolean isCheck;
    private CheckLog log;

    @Override
    public String toString() {
        return "CheckTask{" +
                "id=" + id +
                ", class_id=" + class_id +
                ", create_time=" + create_time +
                ", password='" + password + '\'' +
                ", time_limit=" + time_limit +
                ", teacher_id=" + teacher_id +
                ", type=" + type +
                ", info='" + info + '\'' +
                ", course=" + course +
                ", teacher=" + teacher +
                ", status=" + status +
                ", isCheck=" + isCheck +
                ", log=" + log +
                '}';
    }

    public CheckLog getLog() {
        return log;
    }

    public void setLog(CheckLog log) {
        this.log = log;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public Boolean getStatus() {
        return status;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Integer time_limit) {
        this.time_limit = time_limit;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
