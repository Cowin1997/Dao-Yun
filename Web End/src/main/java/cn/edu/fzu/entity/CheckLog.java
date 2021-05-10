package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel
public class CheckLog {
    private Integer id;
    @ApiModelProperty(value = "签到任务的ID")
    private Integer taskid;

    @ApiModelProperty(value = "签到学生ID")
    private String checksid;

    @ApiModelProperty(value = "签到的经度")
    private Double longitude;
    @ApiModelProperty(value = "签到的维度")
    private Double latitude;

    @ApiModelProperty(value = "签到时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date checktime;

    @ApiModelProperty(value = "本次签到分数")
    private Integer checkscore;
    @ApiModelProperty(value = "本次签到地点")
    private String checkloc;
     @ApiModelProperty(value = "签到说明")
    private String info;


}
