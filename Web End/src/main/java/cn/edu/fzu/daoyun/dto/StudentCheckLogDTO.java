package cn.edu.fzu.daoyun.dto;

import cn.edu.fzu.daoyun.entity.StudentDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@ApiModel
public class StudentCheckLogDTO extends StudentDO implements Serializable {
    @ApiModelProperty("签到总分")
    private Integer score;
    @ApiModelProperty("签到总次数")
    private Integer totalcheck;
    @ApiModelProperty("已签到次数")
    private Integer hascheck;
}
