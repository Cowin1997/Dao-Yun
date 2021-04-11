package fzu.edu.cn.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Menu {
    private Integer id;
    private String icon;
    private String uri;
    private String title;
    private Integer parentid;
    private Integer ord;
    private String roleid;

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", uri='" + uri + '\'' +
                ", title='" + title + '\'' +
                ", parentid=" + parentid +
                ", ord=" + ord +
                ", roleid='" + roleid + '\'' +
                '}';
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
