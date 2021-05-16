package cn.edu.fzu.daoyun.dto;

import cn.edu.fzu.daoyun.entity.CheckLog;
import cn.edu.fzu.daoyun.entity.TaskDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@ApiModel
public class CheckLogDTO implements Serializable {
    private TaskDO task;
    private CheckLog log;
    private Boolean check;
}
