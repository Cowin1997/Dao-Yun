package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.type.Alias;

@ApiModel
@Data
public class School {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "学校名称", example = "福州大学", dataType = "String")
    private String sch_name;
    @ApiModelProperty(value = "学校代码", example = "10386", dataType = "Integer")
    private Integer sch_code;
    @ApiModelProperty(value = "学校说明", example = "福州市闽侯县乌龙江大道2号", dataType = "String")
    private String sch_info;
}
