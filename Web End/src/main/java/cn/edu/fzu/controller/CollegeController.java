package cn.edu.fzu.controller;

import cn.edu.fzu.dao.CollegeMapper;
import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.PageResult;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = {"/college"})
@Api(tags={"学院列表接口"})
public class CollegeController {
    @Resource
    private CollegeMapper collegeMapper;

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ApiOperation(value = "getCollegeBySchoolCode",notes="通过学校编码,获取学院列表")


    public ResultModel<PageResult<College>> getCollegeBySchoolCode(@ApiParam(value="学校编码",required = true) @RequestParam("sch_code") Integer sch_code,
    @RequestParam("page") Integer page , @RequestParam("size") Integer size
    ){
        if(page > 0 && size >0) {
            Integer from = (page - 1) * size;
            Integer end = page * size;
            List<College> collegeList = this.collegeMapper.getCollegeBySchoolCode(sch_code, from, end);
            Integer totalSize = this.collegeMapper.getTotalBySchool(sch_code);
            Integer totalPage = (int) Math.ceil((double) totalSize / size);
            PageResult pageResult = new PageResult(collegeList, totalSize, totalPage);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, pageResult);
        }else{
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR, null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    @ApiOperation(value = "getCollegeByCollegeCode",notes="通过学院编码获取学院信息")
    public ResultModel<College> getCollegeByCollegeCode(@PathVariable(value = "code",required = true) Integer code){
        College college = this.collegeMapper.getCollegeByCollegeCode(code);
        return new ResultModel<College>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,college);
    }

}
