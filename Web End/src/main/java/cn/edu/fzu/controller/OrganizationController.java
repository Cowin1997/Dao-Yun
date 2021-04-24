package cn.edu.fzu.controller;

import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.Major;
import cn.edu.fzu.utils.PageResult;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.service.OrganizationService;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;


@Slf4j
@Controller
@RequestMapping("/org")
@Api(tags={"学校,学院,专业组织接口"})
public class OrganizationController {
    @Resource
    private OrganizationService organizationService;

    @ApiOperation(value = "getSchoolList", notes = "获取学校分页列表")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    @RequestMapping(value = "/school-list", method = RequestMethod.GET)
    public ResponseEntity<ResultModel<PageResult<School>>> getSchoolList(
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "size", required = true) Integer size
    ) {
        if (page <= 0 || size <= 0) //页数或条数不能小于0
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        PageResult<School> pageResult = this.organizationService.getSchoolList(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(pageResult, ResultUtils.MESSAGE.GET_SUCCESS));
    }

    @ApiOperation(value = "getSchool", notes = "根据学校代码获取学校信息")
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, response = ResultModel.class, message = "bad request")
    })
    public ResponseEntity<ResultModel<School>> getSchool(
            @RequestParam(value = "schCode", required = true) Integer schCode
    ) {
        if (schCode <= 0) // 学校代码不能为负数
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        School school  = this.organizationService.getSchool(schCode);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(school, ResultUtils.MESSAGE.GET_SUCCESS));
    }

    @RequestMapping(value = "/college-list", method = RequestMethod.GET)
    @ApiOperation(value = "getCollegeList", notes = "根据学校代码,获取学院分页信息")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<PageResult<College>>> getCollegeList(
            @RequestParam(value = "schCode",required = true) Integer schCode,
            @RequestParam(value = "page",required = true) Integer page ,
            @RequestParam(value = "size",required = true) Integer size
    ){
        if (schCode <= 0 || page <=0 || size<=0 ) // 页数或条数或学校代码不能为负数
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        PageResult<College> pageResult = this.organizationService.getCollegeList(schCode,page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(pageResult, ResultUtils.MESSAGE.GET_SUCCESS));
    }


    @RequestMapping(value = "/college", method = RequestMethod.GET)
    @ApiOperation(value = "getCollege", notes = "根据学校代码和学院代码,获取学院详细信息")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<College>> getCollege(
            @RequestParam(value = "schCode",required = true) Integer schCode,
            @RequestParam(value = "colCode",required = true) Integer colCode
            ){
        if (schCode <= 0 || colCode <=0 ) // 学校或学院代码不能为负数
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        College college = this.organizationService.getCollege(schCode,colCode);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(college, ResultUtils.MESSAGE.GET_SUCCESS));
    }





    @RequestMapping(value = "/major-list", method = RequestMethod.GET)
    @ApiOperation(value = "getMajorList", notes = "根据学校代码和学院代码,获取专业列表信息")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<PageResult<Major>>> getMajorList(
            @RequestParam(value = "colCode",required = true) Integer colCode,
            @RequestParam(value = "page",required = true) Integer page,
            @RequestParam(value = "size",required = true) Integer size
    ){
        if (colCode<=0 || page <=0 || size<=0 ) // 页数或条数或学校代码不能为负数
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        PageResult<Major> pageResult = this.organizationService.getMajorList(colCode,page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(pageResult, ResultUtils.MESSAGE.GET_SUCCESS));
    }


    @RequestMapping(value = "/major", method = RequestMethod.GET)
    @ApiOperation(value = "getMajor", notes = "根据学院代码和专业代码,获取专业信息")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<Major>> getMajor(
            @RequestParam(value = "colCode",required = true) Integer colCode,
            @RequestParam(value = "majCode",required = true) Integer majCode
    ){
        if (colCode<=0 || majCode<=0 ) //学院代码或者专业代码不能为负数
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        Major major = this.organizationService.getMajor(colCode,majCode);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(major, ResultUtils.MESSAGE.GET_SUCCESS));
    }






}



