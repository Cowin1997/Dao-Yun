package cn.edu.fzu.controller;

import cn.edu.fzu.dao.MajorMapper;
import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.Major;
import cn.edu.fzu.entity.PageResult;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import cn.edu.fzu.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = {"/major"})
@Api(tags={"专业列表接口"})
public class MajorController {
    @Resource
    private MajorMapper majorMapper;
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultModel<PageResult<Major>> getMajorBySchoolAndCollege(@RequestParam("sch") Integer sch, @RequestParam("col")Integer col_id
    ,@RequestParam("page") Integer page , @RequestParam("size") Integer size
    ) {
        if(page > 0 && size >0) {
            Integer from = (page - 1) * size;
            Integer to = page * size;
            List<Major> majorList = this.majorMapper.getMajorBySchooCodeAndCollegeId(sch, col_id, from, to);
            Integer totalSize = this.majorMapper.getTotalBySchoolAndCollegeId(sch,col_id);
            Integer totalPage = (int) Math.ceil((double) totalSize / size);
            PageResult pageResult = new PageResult(majorList, totalSize, totalPage);
            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, pageResult);
        } else{
            return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR, null);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public ResultModel<Major> getMajorByMajorCode(@PathVariable("code")String code){
        Major major = this.majorMapper.getMajorByMajorCode(code);
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.GET_SUCCESS, major);

    }



}
