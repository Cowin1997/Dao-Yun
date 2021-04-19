package cn.edu.fzu.param;

import cn.edu.fzu.entity.CheckLog;
import cn.edu.fzu.entity.CheckTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class logRes {
    @ApiModelProperty(value = "签到的任务")
    private CheckTask task;
    @ApiModelProperty(value = "该任务的签到情况")
    private CheckLog log;
}
