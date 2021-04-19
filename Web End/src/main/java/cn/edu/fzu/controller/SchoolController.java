package cn.edu.fzu.controller;

import cn.edu.fzu.dao.SchoolMapper;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.entity.PageResult;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Controller
@RequestMapping(value = {"/school"})
@Api(tags={"学校列表接口"})
public class SchoolController {
    @Resource
    private SchoolMapper schoolMapper;
    @RequestMapping(value = {""},method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getSchoolList",notes="获取学校列表")
    public ResultModel<PageResult<School>> getSchoolList(@RequestParam("page")Integer page , @RequestParam("size") Integer size ){
        if(page > 0 && size >0) {
            Integer from = (page - 1) * size;
            Integer end = page * size;
            List<School> schoolList = this.schoolMapper.getSchools(from, end);
            Integer totalSize = this.schoolMapper.getSchoolTotal();
            Integer totalPage = (int) Math.ceil((double) totalSize / size);
            PageResult pageResult = new PageResult(schoolList, totalSize, totalPage);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, pageResult);
        }else{
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR, null);
        }
    }

    @ResponseBody
    @ApiOperation(value = "getSchoolByCode",notes="根据学校编码,获取学校信息")
    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public ResultModel<School> getSchoolByCode(@PathVariable(value = "code",required = true)Integer code){
        School school = this.schoolMapper.getSchoolBySchoolCode(code);
        if(school!=null){
            return new ResultModel<School>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,school);
        }else{
            return new ResultModel<School>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.GET_ERROR,null);
        }
    }



}
