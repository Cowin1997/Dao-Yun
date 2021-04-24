package cn.edu.fzu.service;

import cn.edu.fzu.dao.ConfigDataMapper;
import cn.edu.fzu.entity.DictParam;
import cn.edu.fzu.entity.School;
import cn.edu.fzu.entity.SysParam;
import cn.edu.fzu.entity.User;
import cn.edu.fzu.utils.PageResult;
import cn.edu.fzu.utils.ResultModel;
import cn.edu.fzu.utils.ResultUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ParamService {
    @Resource
    private ConfigDataMapper configDataMapper;


    public PageResult<SysParam> getSysParamList(Integer page, Integer size){
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<SysParam> sysParamList = this.configDataMapper.getSysParamList(from, to);
        Integer totalSize = this.configDataMapper.getSysParamTotal(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        PageResult pageResult = new PageResult(sysParamList, totalSize, totalPage);
        return pageResult;
    }

    public SysParam getSysParamByKey(String key){
        SysParam param = this.configDataMapper.getSysParamByKey(key);
        return param;
    }

    public Boolean delSysParam(Integer id){
       Boolean aBoolean = this.configDataMapper.delSysParam(id);
       return aBoolean;
    }

    public ResultModel<Boolean> addSysParam(SysParam param){
        SysParam sysParam = this.configDataMapper.getSysParamByKey(param.getArg_key());
        if (sysParam!=null) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.SYS_PARAM_KEY_EXIST,false);
        param.setLast_revise_time(new Date());
        Boolean aBoolean = this.configDataMapper.addSysParam(param);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.ADD_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ADD_ERROR,false);
    }

    public ResultModel<Boolean> updateSysParam(SysParam param){
        param.setLast_revise_time(new Date());
        Boolean aBoolean = this.configDataMapper.updateSysParam(param);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,false);
    }


    public PageResult<DictParam> getDictParamList(Integer page,Integer size){
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<DictParam> dictParamList = this.configDataMapper.getDictParentList(from, to);
        Integer totalSize = this.configDataMapper.getDictParamTotal(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        PageResult pageResult = new PageResult(dictParamList, totalSize, totalPage);
        return pageResult;
    }


    public ResultModel<Boolean> deleteDictById(Integer id){
        Boolean aBoolean = this.configDataMapper.delDictParam(id);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.DEL_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.DEL_ERROR,false);
    }



    public ResultModel<Boolean> updateDict(DictParam param){
        param.setLast_revise_time(new Date());
        Boolean aBoolean = this.configDataMapper.updateDictParam(param);
        if(aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.UPDATE_SUCCESS,true);
        return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.UPDATE_ERROR,false);
    }

    @Transactional
    public ResultModel<Integer> addDict(DictParam param){
        if(param.getSubs()==null){ //添加的是父级字典
            Boolean aBoolean = this.configDataMapper.addDictParam(param);
            if (aBoolean) return new ResultModel<>(ResultUtils.STATUS.SUCCESS,ResultUtils.MESSAGE.ADD_SUCCESS,param.getId());
            return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.ADD_ERROR,null);
        }else{//添加的是子级字典
            List<DictParam> subDictParamList = param.getSubs();
            Integer res = -1;
            for (DictParam p: subDictParamList) {
                Boolean aBoolean = this.configDataMapper.addDictParam(p);
                if(!aBoolean) return new ResultModel<>(ResultUtils.STATUS.ERROR,ResultUtils.MESSAGE.REQUEST_PARAM_ERROR,null);
                res=p.getId();
            }

            return new ResultModel<>(ResultUtils.STATUS.SUCCESS, ResultUtils.MESSAGE.ADD_SUCCESS, res);

        }
}

}
