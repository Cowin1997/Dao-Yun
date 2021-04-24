package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class College {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "学院名称",example = "数学与计算机学院", dataType="String")
    private String co_name;
    @ApiModelProperty(value = "学院代码",example = "1038601", dataType="Integer")
    private Integer co_code;
    @ApiModelProperty(value = "学院说明",example = "福州市闽侯县学园路2号福州大学数学与计算机科学学院", dataType="String")
    private String co_info;
}
