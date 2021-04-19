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
    private String ch_sid;
    @ApiModelProperty(value = "任务id")
    private Integer ch_taskid;
    @ApiModelProperty(value = "签到时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date ch_checktime;
    @ApiModelProperty(value = "本次签到经验值",hidden = true)
    private Integer ch_checkscore;
    @ApiModelProperty(value = "签到位置")
    private String ch_loc;
    @ApiModelProperty(value = "签到说明")
    private String ch_info;


}
