package cn.edu.fzu.entity;

import lombok.Data;

@Data
public class Permission {
    private Integer id;
    private String roleid;
    private String menuuri;
    private String name;
    private String describe;
    private Integer code;
}
