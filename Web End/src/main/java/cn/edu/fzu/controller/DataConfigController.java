package cn.edu.fzu.controller;

import cn.edu.fzu.dao.ConfigDataMapper;
import cn.edu.fzu.entity.DictParam;
import cn.edu.fzu.entity.SysParam;
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
@RequestMapping(value = "/data-config")
@Api(tags={"参数配置接口"})
public class DataConfigController {
    @Resource
    private ConfigDataMapper configDataMapper;

    @RequestMapping(value = "/sys-param-list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getSysParamList",notes = "获取所有系统参数")
    public ResultModel<List<SysParam>> getSysParamList(){
       List<SysParam> sysParamList = this.configDataMapper.getSysParamList();
       return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,sysParamList);
    }
    @RequestMapping(value = "/sys-param",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getSysParamByKey",notes = "根据系统参数键获取系统参数")
    public ResultModel<SysParam> getSysParamByKey(@RequestParam("key") String key){
        SysParam sysParam = this.configDataMapper.getSysParamByKey(key);
        if(sysParam==null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SYS_PARAM_KEY_NO_EXIST,null);
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,sysParam);
    }

    @RequestMapping(value = "/sys-param/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "delSysParamById",notes = "根据参数id删除参数配置")
    public ResultModel<Boolean> delSysParamById(@PathVariable("id") Integer id){
        Boolean aBoolean = this.configDataMapper.delSysParam(id);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.DEL_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.DEL_ERROR,false);
    }

    @RequestMapping(value = "/sys-param",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "addSysParam",notes = "添加系统参数")
    public ResultModel<Boolean> addSysParam(@RequestBody SysParam param){
        SysParam sysParam = this.configDataMapper.getSysParamByKey(param.getArg_key());
        if (sysParam!=null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SYS_PARAM_KEY_EXIST,false);
        Boolean aBoolean = this.configDataMapper.addSysParam(param);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.ADD_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ADD_ERROR,false);
    }
    @RequestMapping(value = "/sys-param",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "updateSysParam",notes = "更新系统参数")
    public ResultModel<Boolean> updateSysParam(@RequestBody SysParam param){
        Boolean aBoolean = this.configDataMapper.updateSysParam(param);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,false);
    }


    @RequestMapping(value = "/dict-param-list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getDictParamList",notes = "获取所有字典")
    public ResultModel<List<DictParam>> getDictParamList(){
        List<DictParam> dictParamList = this.configDataMapper.getDictParentList();
        log.info(dictParamList.toString());
        return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.GET_SUCCESS,dictParamList);
    }


    @RequestMapping(value = "/dict-param/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "deleteDictById",notes = "根据ID删除字典")
    public ResultModel<Boolean> deleteDictById(@PathVariable(value = "id") Integer id){
           Boolean aBoolean = this.configDataMapper.delDictParam(id);
           if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.DEL_SUCCESS,true);
           return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.DEL_ERROR,false);
    }


    @RequestMapping(value = "/dict-param",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "addDict",notes = "添加字典")
    public ResultModel<Integer> addDict(@RequestBody DictParam param){
        if(param.getSubs()==null){ //添加的是父级字典
            Boolean aBoolean = this.configDataMapper.addDictParam(param);
            if (aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.ADD_SUCCESS,param.getId());
            return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ADD_ERROR,null);
        }else{//添加的是子级字典
            List<DictParam> dictParamList = param.getSubs();
            if(dictParamList.size()!=1) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.REQUEST_PARAM_ERROR,null);
            else {
                DictParam p = dictParamList.get(0);
                Boolean aBoolean = this.configDataMapper.addDictParam(p);
                if (aBoolean)
                    return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.ADD_SUCCESS, p.getId());
                return new ResultModel<>(ResultUtils.STATUS.ERROR, ResultUtils.MESSAGE.ADD_ERROR, null);

            }
        }
    }


    @RequestMapping(value = "/dict-param",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "updateDict",notes = "更新字典")
    public ResultModel<Boolean> updateDict(@RequestBody DictParam param){
        Boolean aBoolean = this.configDataMapper.updateDictParam(param);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,false);
    }






    }

