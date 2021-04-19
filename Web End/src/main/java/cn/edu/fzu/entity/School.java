package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class School {
    private Integer id;
    private Integer sch_code;
    private String sch_name;
    private String sch_addr;
    private String sch_info;
    private String sch_phone;
}
