package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class College {
    private Integer id;
    private Integer co_code;
    private String co_name;
    private String co_phone;
    private String co_info;
}
