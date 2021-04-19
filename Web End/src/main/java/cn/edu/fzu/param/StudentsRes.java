package cn.edu.fzu.param;

import cn.edu.fzu.entity.Student;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class StudentsRes {
    private Integer total;
    private List<Student> studentList;
    private Integer page;
    private Integer size;
}
