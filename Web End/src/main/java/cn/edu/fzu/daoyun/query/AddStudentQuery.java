package cn.edu.fzu.daoyun.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class AddStudentQuery {
    private Integer role;
    private Integer sid;
    private String name;
    private String gender;
    private String phone;
    private Integer school_code;
    private Integer college_code;
    private Integer major_code;
    private Boolean enabled;
    private Date gmt_create;
    private Integer id;
    private String accountName;
}
