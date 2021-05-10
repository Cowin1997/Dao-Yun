package cn.edu.fzu.service;

import cn.edu.fzu.dao.OrganizationMapper;
import cn.edu.fzu.entity.College;
import cn.edu.fzu.entity.Major;
import cn.edu.fzu.utils.PageResult;
import cn.edu.fzu.entity.School;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrganizationService {
    @Resource
    private OrganizationMapper organizationMapper;


    public PageResult<School> getSchoolList(Integer page, Integer size) { //获取学校分页列表
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<School> schoolList = this.organizationMapper.getSchoolList(from, to);
        Integer totalSize = this.organizationMapper.getSchoolTotal(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        PageResult pageResult = new PageResult(schoolList, totalSize, totalPage);
        return pageResult;
    }

    public School getSchool(Integer schCode) { //根据学校代码获取学校明细
        School school = this.organizationMapper.getSchool(schCode);
        return school;
    }


    public PageResult<College> getCollegeList(Integer schCode, Integer page, Integer size) { //根据学校代码,获取学校相关专业
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<College> collegeList = this.organizationMapper.getCollegeList(schCode, from, to);
        Integer totalSize = this.organizationMapper.getCollegeTotal(schCode); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        PageResult pageResult = new PageResult(collegeList, totalSize, totalPage);
        return pageResult;
    }

    //根据学校代码和学院代码,获取学院详细信息
    public College getCollege(Integer schCode, Integer colCode) {
        College college = this.organizationMapper.getCollege(schCode, colCode);
        return college;
    }

    //根据学校代码和学院代码,获取专业列表信息
    public PageResult<Major> getMajorList(Integer colCode, Integer page, Integer size) {
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<Major> majorList = this.organizationMapper.getMajorList(colCode, from, to);
        Integer totalSize = this.organizationMapper.getMajorTotal(colCode); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        PageResult pageResult = new PageResult(majorList, totalSize, totalPage);
        return pageResult;
    }

    //根据学院代码和专业代码,获取专业信息
    public Major getMajor(Integer colCode, Integer majCode) {
        Major major = this.organizationMapper.getMajor(colCode, majCode);
        return major;
    }

}
