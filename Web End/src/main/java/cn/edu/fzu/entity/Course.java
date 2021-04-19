package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
public class Course {
    @ApiModelProperty(hidden = true)
    private Integer id;
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
    @ApiModelProperty(value = "任课教师详细信息")
    private Teacher teacher;
    @ApiModelProperty(value = "所属学校编码")
    private Integer cs_school;
    @ApiModelProperty(value = "所属专业编码")
    private Integer cs_major;
    @ApiModelProperty(value = "所属学院编码")
    private Integer cs_college;
}
