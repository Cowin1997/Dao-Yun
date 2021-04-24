package cn.edu.fzu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class selectCourse {
    private Integer id;
    @NotNull
    @ApiModelProperty(value = "学生学号")
    private String st_id;
    @NotNull
    @ApiModelProperty(value = "所选班课ID")
    private String cs_id;

    @ApiModelProperty(value = "该课程签到分")
    private Integer score;

}
