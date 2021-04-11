package cn.edu.fzu.entity;

public class Permission {
    private Integer id;
    private String roleid;
    private String menuuri;
    private String name;
    private String describe;
    private Integer code;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", roleid='" + roleid + '\'' +
                ", menuuri='" + menuuri + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", code=" + code +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getMenuuri() {
        return menuuri;
    }

    public void setMenuuri(String menuuri) {
        this.menuuri = menuuri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
