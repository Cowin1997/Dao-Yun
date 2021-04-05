package cn.edu.fzu.entity;

public class School {
    private Integer id;
    private Integer sch_code;
    private String sch_name;
    private String sch_addr;
    private String sch_info;
    private String sch_phone;
    private Integer st_grade;

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", sch_code=" + sch_code +
                ", sch_name='" + sch_name + '\'' +
                ", sch_addr='" + sch_addr + '\'' +
                ", sch_info='" + sch_info + '\'' +
                ", sch_phone='" + sch_phone + '\'' +
                ", st_grade=" + st_grade +
                '}';
    }

    public Integer getSt_grade() {
        return st_grade;
    }

    public void setSt_grade(Integer st_grade) {
        this.st_grade = st_grade;
    }

    public School(Integer id, Integer sch_code, String sch_name, String sch_addr, String sch_info, String sch_phone, Integer st_grade) {
        this.id = id;
        this.sch_code = sch_code;
        this.sch_name = sch_name;
        this.sch_addr = sch_addr;
        this.sch_info = sch_info;
        this.sch_phone = sch_phone;
        this.st_grade = st_grade;
    }

    public School(Integer id, Integer sch_code, String sch_name, String sch_addr, String sch_info, String sch_phone) {
        this.id = id;
        this.sch_code = sch_code;
        this.sch_name = sch_name;
        this.sch_addr = sch_addr;
        this.sch_info = sch_info;
        this.sch_phone = sch_phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSch_code() {
        return sch_code;
    }

    public void setSch_code(Integer sch_code) {
        this.sch_code = sch_code;
    }

    public String getSch_name() {
        return sch_name;
    }

    public void setSch_name(String sch_name) {
        this.sch_name = sch_name;
    }

    public String getSch_addr() {
        return sch_addr;
    }

    public void setSch_addr(String sch_addr) {
        this.sch_addr = sch_addr;
    }

    public String getSch_info() {
        return sch_info;
    }

    public void setSch_info(String sch_info) {
        this.sch_info = sch_info;
    }

    public String getSch_phone() {
        return sch_phone;
    }

    public void setSch_phone(String sch_phone) {
        this.sch_phone = sch_phone;
    }
}
