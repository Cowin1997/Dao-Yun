package cn.edu.fzu.entity;

public class Major {
    private Integer id;
    private Integer ma_majorid;
    private String ma_majorname;
    private Integer ma_collegeid;
    private Integer ma_schoolcode;
    private String ma_phone;
    private String ma_info;

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", ma_majorid=" + ma_majorid +
                ", ma_majorname='" + ma_majorname + '\'' +
                ", ma_collegeid=" + ma_collegeid +
                ", ma_schoolcode=" + ma_schoolcode +
                ", ma_phone='" + ma_phone + '\'' +
                ", ma_info='" + ma_info + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMa_majorid() {
        return ma_majorid;
    }

    public void setMa_majorid(Integer ma_majorid) {
        this.ma_majorid = ma_majorid;
    }

    public String getMa_majorname() {
        return ma_majorname;
    }

    public void setMa_majorname(String ma_majorname) {
        this.ma_majorname = ma_majorname;
    }

    public Integer getMa_collegeid() {
        return ma_collegeid;
    }

    public void setMa_collegeid(Integer ma_collegeid) {
        this.ma_collegeid = ma_collegeid;
    }

    public Integer getMa_schoolcode() {
        return ma_schoolcode;
    }

    public void setMa_schoolcode(Integer ma_schoolcode) {
        this.ma_schoolcode = ma_schoolcode;
    }

    public String getMa_phone() {
        return ma_phone;
    }

    public void setMa_phone(String ma_phone) {
        this.ma_phone = ma_phone;
    }

    public String getMa_info() {
        return ma_info;
    }

    public void setMa_info(String ma_info) {
        this.ma_info = ma_info;
    }

    public Major() {
    }

    public Major(Integer id, Integer ma_majorid, String ma_majorname, Integer ma_collegeid, Integer ma_schoolcode, String ma_phone, String ma_info) {
        this.id = id;
        this.ma_majorid = ma_majorid;
        this.ma_majorname = ma_majorname;
        this.ma_collegeid = ma_collegeid;
        this.ma_schoolcode = ma_schoolcode;
        this.ma_phone = ma_phone;
        this.ma_info = ma_info;
    }
}
