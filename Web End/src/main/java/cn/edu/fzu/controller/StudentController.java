package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CourseMapper;
import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.utils.PageResult;
import cn.edu.fzu.entity.Student;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(tags = {"学生信息接口"})
@RequestMapping(value = {"/student"})
public class StudentController {
    @Resource
    private StudentMapper studentMapper;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "getStudent", notes = "获取学生列表")
    public ResultModel<PageResult<Student>> getStudent(@RequestParam(value = "sch", required = false) Integer sch,
                                                       @RequestParam(value = "col", required = false) Integer col,
                                                       @RequestParam(value = "maj", required = false) Integer maj,
                                                       @RequestParam(value = "cid", required = false) String cid,
                                                       @RequestParam(value = "page", required = true) Integer page,
                                                       @RequestParam(value = "size", required = true) Integer size) {
        if (page > 0 && size > 0) {
            List<String> studentSidList;
            Integer totalSize = 0;
            List<Student> studentList = new ArrayList<>();
            Integer from = (page - 1) * size;
            Integer to = page * size;
            System.out.println(sch);
            if ((sch != null && !StringUtils.equals(sch, "")) && (col == null || StringUtils.equals(col, "")) && (maj == null || StringUtils.equals(maj, ""))) { //获取学校所有学生
                studentList = this.studentMapper.getStudentBySch(sch, from, to);
                totalSize = this.studentMapper.getTotalBySch(sch);
            } else if ((sch != null || !StringUtils.equals("", sch)) && (col != null || !StringUtils.equals("", col)) && (maj == null || StringUtils.equals(maj, ""))) {
                studentList = this.studentMapper.getStudentBySch_Col(sch, col, from, to);
                totalSize = this.studentMapper.getTotalBySch_Col(sch, col);
            } else if ((sch != null || !StringUtils.equals("", sch)) && (col != null || !StringUtils.equals("", col)) && (maj != null || !StringUtils.equals(maj, ""))) {
                studentList = this.studentMapper.getStudentBySch_Col_Maj(sch, col, maj, from, to);
                totalSize = this.studentMapper.getTotalBySch_Col_Maj(sch, col, maj);
            } else if (cid != null && !StringUtils.equals("", cid)) {
                studentSidList = this.studentMapper.getStudentSidByCid(cid, from, to);
                totalSize = this.studentMapper.getTotalByCid(cid);
                for (String sid : studentSidList) {
                    Student s = this.studentMapper.getStudentBySid(sid);
                    studentList.add(s);
                }
            }
            Integer totalPage = (int) Math.ceil((double) totalSize / size);
            PageResult pageResult = new PageResult(studentList, totalSize, totalPage);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, pageResult);
        } else {
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR, null);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/{sid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delStudentBySid", notes = "通过学号,删除学生")
    public ResultModel<Boolean> delStudentBySid(@PathVariable(value = "sid", required = true) String sid) {
        Boolean aBoolean = this.studentMapper.delStudentBySid(sid);
        if (aBoolean == true) {
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.DEL_SUCCESS, true);
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.DEL_ERROR, false);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "addStudent", notes = "添加学生")
    public ResultModel<Boolean> addStudent(@RequestBody Student student) {
        Student s = this.studentMapper.getStudent(student.getSt_id());
        if (s != null) return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.STUDENT_EXIST, false);
        Boolean aBoolean = this.studentMapper.addStudent(student);

        if (aBoolean == true) {
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.ADD_SUCCESS, true);
        }
        return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.ADD_ERROR, false);
    }


    @ResponseBody
    @RequestMapping(value = "/{st_id}", method = RequestMethod.GET)
    @ApiOperation(value = "getStudent", notes = "通过学生学号查找学生")
    public ResultModel<Student> getStudent(@PathVariable("st_id") String st_id) {
        Student student = this.studentMapper.getStudent(st_id);
        return ResultUtils.success(student, ResultUtils.MESSAGE.GET_SUCCESS);

    }


    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "updateStudent", notes = "更新学生信息")
    public ResultModel<Boolean> updateStudent(@RequestBody Student student) {
        ;
        Boolean aBoolean = this.studentMapper.updateStudent(student);
        if (aBoolean == true) {
            return new ResultModel<Boolean>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.UPDATE_SUCCESS, true);
        } else {
            return new ResultModel<Boolean>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.UPDATE_ERROR, false);
        }

    }


}
