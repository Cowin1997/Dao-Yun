package cn.edu.fzu.entity;

public class College {
    private Integer id;
    private Integer co_collegeid;
    private String co_collegename;
    private String co_phone;
    private String co_info;


    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", co_collegeid=" + co_collegeid +
                ", co_collegename='" + co_collegename + '\'' +
                ", co_phone='" + co_phone + '\'' +
                ", co_info='" + co_info + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCo_collegeid() {
        return co_collegeid;
    }

    public void setCo_collegeid(Integer co_collegeid) {
        this.co_collegeid = co_collegeid;
    }

    public String getCo_collegename() {
        return co_collegename;
    }

    public void setCo_collegename(String co_collegename) {
        this.co_collegename = co_collegename;
    }

    public String getCo_phone() {
        return co_phone;
    }

    public void setCo_phone(String co_phone) {
        this.co_phone = co_phone;
    }

    public String getCo_info() {
        return co_info;
    }

    public void setCo_info(String co_info) {
        this.co_info = co_info;
    }

    public College() {
    }

    public College(Integer id, Integer co_collegeid, String co_collegename, String co_phone, String co_info) {
        this.id = id;
        this.co_collegeid = co_collegeid;
        this.co_collegename = co_collegename;
        this.co_phone = co_phone;
        this.co_info = co_info;
    }
}
