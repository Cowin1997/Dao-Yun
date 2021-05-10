package cn.edu.fzu.entity;

import lombok.Data;

@Data
public class Role {
    private Integer id;
    private String role_name;
    private Boolean status;
    private String role_describe;
}
