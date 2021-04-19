package cn.edu.fzu.param;

import cn.edu.fzu.entity.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CourseReq {
    @ApiModelProperty(value = "班课号")
    private String cs_id;
    @ApiModelProperty(value = "班课名字")
    private String cs_name;
    @ApiModelProperty(hidden = true)
    private String cs_tid;
    @ApiModelProperty(value = "上课地点")
    private String cs_loc;
    @ApiModelProperty(value = "上课时间",example = "5-8节")
    private String cs_time;
    @ApiModelProperty(value = "是否还可以加入班课",example = "true")
    private Boolean cs_status;
    @ApiModelProperty(value = "班课说明")
    private String cs_info;
}
