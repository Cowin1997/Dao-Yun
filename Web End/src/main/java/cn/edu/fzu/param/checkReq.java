package cn.edu.fzu.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class checkReq {
    @ApiModelProperty(value = "学生学号")
    private String checksid;
    @ApiModelProperty(value = "任务id")
    private Integer taskid;
    @ApiModelProperty(value = "班级id")
    private Integer checkcid;
    @ApiModelProperty(value = "签到时间", hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date checktime;
    @ApiModelProperty(value = "本次签到经验值", hidden = true)
    private Integer checkscore;
    @ApiModelProperty(value = "签到位置")
    private String loc;
    @ApiModelProperty(value = "签到说明")
    private String info;

    @ApiModelProperty(value = "发起签到任务的经度")
    private Double longitude;
    @ApiModelProperty(value = "发起签到任务的维度")
    private Double latitude;
}
