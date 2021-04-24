package cn.edu.fzu.controller;

import cn.edu.fzu.dao.ConfigDataMapper;
import cn.edu.fzu.entity.DictParam;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.entity.SysParam;
import cn.edu.fzu.service.ParamService;
import cn.edu.fzu.utils.PageResult;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/data-config")
@Api(tags={"参数配置接口"})
public class DataConfigController {
    @Resource
    private ParamService paramService;

    @RequestMapping(value = "/sys-param-list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getSysParamList",notes = "获取系统参数分页")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<PageResult<SysParam>>> getSysParamList(
            @RequestParam(value = "page",required = true) Integer page,
            @RequestParam(value = "size",required = true) Integer size
    ){
        if (page <= 0 || size <= 0) //页数或条数不能小于0
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        PageResult<SysParam> pageResult = this.paramService.getSysParamList(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(pageResult, ResultUtils.MESSAGE.GET_SUCCESS));
    }


    @RequestMapping(value = "/sys-param",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getSysParamByKey",notes = "根据系统参数键获取系统参数")
    public ResponseEntity<ResultModel<SysParam>> getSysParamByKey(
            @RequestParam(value = "key",required = true) String key)
    {
        SysParam sysParam = this.paramService.getSysParamByKey(key);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(sysParam, ResultUtils.MESSAGE.GET_SUCCESS));
    }


    @RequestMapping(value = "/sys-param/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "delSysParamById",notes = "根据参数id删除参数配置")
    @Transactional

    public ResponseEntity<ResultModel<Boolean>> delSysParamById(
            @PathVariable(value = "id",required = true) Integer id){
        Boolean aBoolean = this.paramService.delSysParam(id);
        if(aBoolean)
            return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(true, ResultUtils.MESSAGE.DEL_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.error(false, ResultUtils.MESSAGE.DEL_ERROR));
    }




    @RequestMapping(value = "/sys-param",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "addSysParam",notes = "添加系统参数")
    public ResponseEntity<ResultModel<Boolean>> addSysParam(@RequestBody SysParam param){
        ResultModel addResult = this.paramService.addSysParam(param);
        return ResponseEntity.status(HttpStatus.OK).body(addResult);
    }




    @RequestMapping(value = "/sys-param",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "updateSysParam",notes = "更新系统参数")
    public ResponseEntity<ResultModel<Boolean>> updateSysParam(@RequestBody SysParam param){
        ResultModel<Boolean> updateResult = this.paramService.updateSysParam(param);
        return ResponseEntity.status(HttpStatus.OK).body(updateResult);
    }


    @RequestMapping(value = "/dict-param-list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getDictParamList",notes = "获取所有字典")
    @ApiResponses(value = {
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "bad request", response = ResultModel.class)
    })
    public ResponseEntity<ResultModel<PageResult<DictParam>>> getDictParamList(
            @RequestParam(value = "page",required = true) Integer page,
            @RequestParam(value = "size",required = true) Integer size
    ){
        if (page <= 0 || size <= 0) //页数或条数不能小于0
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.error(null, ResultUtils.MESSAGE.REQUEST_PARAM_ERROR));
        PageResult<DictParam> pageResult = this.paramService.getDictParamList(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(ResultUtils.success(pageResult, ResultUtils.MESSAGE.GET_SUCCESS));
    }


    @RequestMapping(value = "/dict-param/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "deleteDictById",notes = "根据ID删除字典")
    public ResponseEntity<ResultModel<Boolean>> deleteDictById(@PathVariable(value = "id") Integer id){
         ResultModel resultModel = this.paramService.deleteDictById(id);
         return ResponseEntity.status(HttpStatus.OK).body(resultModel);
    }


    @RequestMapping(value = "/dict-param",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "addDict",notes = "添加字典")
    public ResponseEntity<ResultModel<Integer>> addDict(@RequestBody DictParam param){
        ResultModel resultModel = this.paramService.addDict(param);
        return ResponseEntity.status(HttpStatus.OK).body(resultModel);

    }


    @RequestMapping(value = "/dict-param",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "updateDict",notes = "更新字典")
    public ResponseEntity<ResultModel<Boolean>> updateDict(@RequestBody DictParam param) {
        ResultModel resultModel = this.paramService.updateDict(param);
        return ResponseEntity.status(HttpStatus.OK).body(resultModel);
    }



}

