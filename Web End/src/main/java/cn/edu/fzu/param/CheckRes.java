package cn.edu.fzu.param;

import cn.edu.fzu.entity.CheckLog;
import cn.edu.fzu.entity.CheckTask;
import cn.edu.fzu.entity.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class CheckRes {
    private Student student;
    @ApiModelProperty("如果为空,说明未签到。非空返回签到信息")
    private CheckLog log;
}
