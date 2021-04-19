package cn.edu.fzu.service;

import cn.edu.fzu.dao.StudentMapper;
import cn.edu.fzu.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;



}
