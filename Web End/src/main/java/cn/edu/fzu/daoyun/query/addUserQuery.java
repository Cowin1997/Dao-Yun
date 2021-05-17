package cn.edu.fzu.daoyun.query;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class addUserQuery {
    private Boolean enabled;
    private String name;
    private String phone;
    private Integer role;
    private Integer id;
    private Date gmt_create;
}
