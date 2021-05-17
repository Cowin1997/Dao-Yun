package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.dto.SysDictDTO;
import cn.edu.fzu.daoyun.entity.SysDictDO;
import cn.edu.fzu.daoyun.mapper.SysDictMapper;
import cn.edu.fzu.daoyun.service.SysDictService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDictDO> getDictParentList(Integer from, Integer to) {
        return sysDictMapper.getDictParentList(from,to);
    }
    public Page<SysDictDTO> getDictPageBySearch(Integer page, Integer size,String search){
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<SysDictDO> dictParentListBySearch = sysDictMapper.getDictParentListBySearch(from, to, search);
        Integer totalSize = dictParentListBySearch.size();
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        List<SysDictDTO> list = new ArrayList<>();
        for (SysDictDO dict:dictParentListBySearch) {
            List<SysDictDO> subDictList = this.getSubDictList(dict.getId());
            list.add(new SysDictDTO(dict,subDictList));
        }
        return new Page<>(list, totalSize, totalPage);
    }

    @Override
    public List<SysDictDO> getSubDictList(Integer parentId) {
        return sysDictMapper.getSubDictList(parentId);
   }

    @Override
    public Page<SysDictDTO> getDictPage(Integer page, Integer size) {
        Integer from = (page - 1) * size;
        Integer to = page * size;
        Integer totalSize = this.sysDictMapper.getSysDictTotal(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        List<SysDictDTO> list = new ArrayList<>();
        List<SysDictDO> dictParentList = this.getDictParentList(from, to);
        for (SysDictDO dict:dictParentList) {
            List<SysDictDO> subDictList = this.getSubDictList(dict.getId());
            list.add(new SysDictDTO(dict,subDictList));
        }
        return new Page<>(list, totalSize, totalPage);
    }


    public Integer addParentDict(SysDictDO dict){
        sysDictMapper.addParentDict(dict);
        return dict.getId();
    }

    public Integer addSubDict(SysDictDO dict){
        sysDictMapper.addSubDict(dict);
        return dict.getId();
    }
    public Boolean deleteParentDict(Integer id){
        Boolean aBoolean = sysDictMapper.deleteSubtDictByPid(id);
        Boolean aBoolean1 = sysDictMapper.deleteParentDict(id);
        return aBoolean && aBoolean1;
    }

    public Boolean deleteSubDict(Integer id){
        return  this.sysDictMapper.deleteParentDict(id);
    }

    public Boolean updateParentDict(SysDictDO dict){
        return sysDictMapper.updateParentDict(dict);
    }
    public Boolean updateSubDict(SysDictDO dict){
        return sysDictMapper.updateSubDict(dict);
    }
}
