package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Major {
    private Integer id;
    private Integer ma_code;
    private String ma_name;
    private Integer ma_college;
    private Integer ma_school;
    private String ma_phone;
    private String ma_info;
}
