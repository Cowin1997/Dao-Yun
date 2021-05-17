package cn.edu.fzu.daoyun.query;

import lombok.Data;

@Data
public class updateUserQuery {
    private Integer id;
    private String name;
    private String password;
    private String phone;
}
