package cn.edu.fzu.daoyun.entity;

import cn.edu.fzu.daoyun.base.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ApiModel
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CheckLog extends BaseDO implements Serializable {
    private Integer task_id;
    private Integer student_sid;
    private Double longitude;
    private Double latitude;

}
