package cn.edu.fzu.daoyun.dto;

import cn.edu.fzu.daoyun.entity.CourseDO;
import cn.edu.fzu.daoyun.entity.TeacherDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class CourseDTO2 extends CourseDO implements Serializable {
    private TeacherDO teacher;
}
