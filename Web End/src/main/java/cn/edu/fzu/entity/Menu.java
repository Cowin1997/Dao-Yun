package fzu.edu.cn.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Menu {
    private Integer id;
    private String icon;
    private String uri;
    private String title;
    private Boolean admin;
    private Boolean superadmin;

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", uri='" + uri + '\'' +
                ", title='" + title + '\'' +
                ", admin=" + admin +
                ", superadmin=" + superadmin +
                '}';
    }

    public Menu(Integer id, String icon, String uri, String title, Boolean admin, Boolean superadmin) {
        this.id = id;
        this.icon = icon;
        this.uri = uri;
        this.title = title;
        this.admin = admin;
        this.superadmin = superadmin;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(Boolean superadmin) {
        this.superadmin = superadmin;
    }
}
