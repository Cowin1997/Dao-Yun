package cn.edu.fzu.entity;

import lombok.Data;

@Data
public class Menu {
    private Integer id;
    private String icon;
    private String uri;
    private String title;
    private Integer parentid;
    private Integer ord;
    private String roleid;
}
