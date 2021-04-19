package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@ApiModel
public class Teacher {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String te_id;
    private String te_name;
    private String te_phone;
    @Email
    private String te_email;
    private Integer te_school;
    private Integer te_college;
    private Integer te_major;
    private String te_sex;
    private String te_info;
    private String te_rank;
    private String te_edu;

}
