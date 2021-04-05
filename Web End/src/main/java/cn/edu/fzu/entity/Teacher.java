package cn.edu.fzu.entity;

public class Teacher {
    private Integer id;
    private Integer te_teachid;
    private String te_teachname;
    private String te_phone;
    private String te_email;
    private Integer te_schoolcode;
    private Integer te_collegeid;
    private Integer te_majorid;
    private String te_sex;
    private String te_info;
    private String te_rank;
    private String te_edu;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", te_teachid=" + te_teachid +
                ", te_teachname='" + te_teachname + '\'' +
                ", te_phone='" + te_phone + '\'' +
                ", te_email='" + te_email + '\'' +
                ", te_schoolcode=" + te_schoolcode +
                ", te_collegeid=" + te_collegeid +
                ", te_majorid=" + te_majorid +
                ", te_sex='" + te_sex + '\'' +
                ", te_info='" + te_info + '\'' +
                ", te_rank='" + te_rank + '\'' +
                ", te_edu='" + te_edu + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTe_teachid() {
        return te_teachid;
    }

    public void setTe_teachid(Integer te_teachid) {
        this.te_teachid = te_teachid;
    }

    public String getTe_teachname() {
        return te_teachname;
    }

    public void setTe_teachname(String te_teachname) {
        this.te_teachname = te_teachname;
    }

    public String getTe_phone() {
        return te_phone;
    }

    public void setTe_phone(String te_phone) {
        this.te_phone = te_phone;
    }

    public String getTe_email() {
        return te_email;
    }

    public void setTe_email(String te_email) {
        this.te_email = te_email;
    }

    public Integer getTe_schoolcode() {
        return te_schoolcode;
    }

    public void setTe_schoolcode(Integer te_schoolcode) {
        this.te_schoolcode = te_schoolcode;
    }

    public Integer getTe_collegeid() {
        return te_collegeid;
    }

    public void setTe_collegeid(Integer te_collegeid) {
        this.te_collegeid = te_collegeid;
    }

    public Integer getTe_majorid() {
        return te_majorid;
    }

    public void setTe_majorid(Integer te_majorid) {
        this.te_majorid = te_majorid;
    }

    public String getTe_sex() {
        return te_sex;
    }

    public void setTe_sex(String te_sex) {
        this.te_sex = te_sex;
    }

    public String getTe_info() {
        return te_info;
    }

    public void setTe_info(String te_info) {
        this.te_info = te_info;
    }

    public String getTe_rank() {
        return te_rank;
    }

    public void setTe_rank(String te_rank) {
        this.te_rank = te_rank;
    }

    public String getTe_edu() {
        return te_edu;
    }

    public void setTe_edu(String te_edu) {
        this.te_edu = te_edu;
    }

    public Teacher(Integer id, Integer te_teachid, String te_teachname, String te_phone, String te_email, Integer te_schoolcode, Integer te_collegeid, Integer te_majorid, String te_sex, String te_info, String te_rank, String te_edu) {
        this.id = id;
        this.te_teachid = te_teachid;
        this.te_teachname = te_teachname;
        this.te_phone = te_phone;
        this.te_email = te_email;
        this.te_schoolcode = te_schoolcode;
        this.te_collegeid = te_collegeid;
        this.te_majorid = te_majorid;
        this.te_sex = te_sex;
        this.te_info = te_info;
        this.te_rank = te_rank;
        this.te_edu = te_edu;
    }
}
