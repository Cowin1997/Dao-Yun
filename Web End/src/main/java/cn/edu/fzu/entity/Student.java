package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Student", description = "Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String st_id;
    private String st_name;
    private String st_sex;
    private String st_phone;
    private String st_email;
    private Integer st_school;
    private Integer st_college;
    private Integer st_major;
    private Integer st_grade;
}
