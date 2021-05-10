package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Data
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
@ToString
public class CheckTask {
    @ApiModelProperty(hidden = true)

    private Integer id;
    @ApiModelProperty(value = "需要签到的班课id")
    private String class_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "发起时间", hidden = true)
    private Date create_time;
    @ApiModelProperty(value = "签到密码,若type为0则为限时签到,不需要密码。为1则为手势签到,需要密码。")
    private String password;
    @ApiModelProperty(value = "限时签到的时间,单位:秒")
    private Integer time_limit;
    @ApiModelProperty(value = "发起签到的教师工号")
    private Integer teacher_id;
    @ApiModelProperty(value = "签到类型,0为限时签到,1为手势签到")
    private Integer type;
    @ApiModelProperty(value = "签到说明")
    private String info;
    @ApiModelProperty(value = "签到是否结束,0未结束,1结束", hidden = true)
    private Boolean status;

    @ApiModelProperty(value = "发起签到任务的经度")
    private Double longitude;
    @ApiModelProperty(value = "发起签到任务的维度")
    private Double latitude;

}
