package cn.edu.fzu.daoyun.service.impl;

import cn.edu.fzu.daoyun.base.Page;
import cn.edu.fzu.daoyun.entity.StudentDO;
import cn.edu.fzu.daoyun.entity.UserAuthDO;
import cn.edu.fzu.daoyun.entity.UserDO;
import cn.edu.fzu.daoyun.exception.BadRequestException;
import cn.edu.fzu.daoyun.mapper.StudentMapper;
import cn.edu.fzu.daoyun.mapper.UserAuthMapper;
import cn.edu.fzu.daoyun.mapper.UserMapper;
import cn.edu.fzu.daoyun.query.AddStudentQuery;
import cn.edu.fzu.daoyun.query.addUserQuery;
import cn.edu.fzu.daoyun.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAuthMapper userAuthMapper;


    /**
     * 根据学号获取学生信息
     *
     * @param sid
     * @return
     */
    @Override
    public StudentDO getStudentBySid(Integer sid) {
        return this.studentMapper.selectBySid(sid);
    }

    /**
     * 根据学号删除学生(*解绑了账号绑定的学生)
     *
     * @param sid
     */
    @Override
    public Boolean deleteStudentBySid(Integer sid) {
        return this.studentMapper.delStudentBySid(sid);
    }

    /**
     * 更新学生信息
     *
     * @param student
     */
    @Override
    public Boolean updateStudentBySid(StudentDO student) {
        student.setGmt_modified(new Date());
        return this.studentMapper.updateStudent(student);
    }

    /**
     * 添加学生(+添加账号)
     *
     * @param
     */
    @Override
    @Transactional
    public Boolean addStudent(AddStudentQuery query) {
        StudentDO s = this.getStudentBySid(query.getSid());
        if(s!=null) throw new BadRequestException("添加学生的学号已存在");
        // 注册检查
        UserDO u = this.userMapper.getUserByPhone(query.getPhone());
        UserAuthDO u2 = this.userAuthMapper.selectByIdentifier(query.getPhone(), null);
        if(u!=null || u2!=null) throw new BadRequestException("电话号码已被注册");
        // 注册
        query.setGmt_create(new Date());
        Boolean aBoolean = this.userMapper.addUser(query);
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        // 添加凭证
        aBoolean = this.userAuthMapper.addUserAuthLocal2(query.getId(), query.getAccountName(), query.getGmt_create());
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        aBoolean = this.userAuthMapper.addUserAuthPhone2(query.getId(), query.getPhone(), query.getGmt_create());
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        // 添加学生
        StudentDO student = new StudentDO();
        BeanUtils.copyProperties(query,student);
        student.setUser_id(query.getId());
        aBoolean = this.studentMapper.insertStudent(student);
        if(aBoolean == false) throw new BadRequestException("注册异常,请联系管理员");
        return aBoolean;
    }

    /**
     * 根据组织信息查询所有学生
     */
    @Override
    public Page<StudentDO> getStudentPageByOrg(Integer sch, Integer col, Integer maj, Integer page, Integer size) {
        Integer from = (page-1)*size;
        Integer to = page * size;
        Integer totalSize = this.studentMapper.getTotalBySch_Col_Maj(sch,col,maj);
        Integer totalPage = (int) Math.ceil((double) totalSize / size);
        List<StudentDO> pageData = this.studentMapper.getStudentBySch_Col_Maj(sch, col, maj, from, to);
        return new Page<>(pageData,totalSize,totalPage);
    }

    public Page<StudentDO> searchStudentByLike(Integer page, Integer size,String search){
        Integer from = (page - 1) * size;
        Integer to = page * size;
        List<StudentDO> studentByLike = this.studentMapper.getStudentByLike(search);
        Integer totalSize = studentByLike.size(); //总条数
        Integer totalPage = (int) Math.ceil((double) totalSize / size); //总页数
        Page pageResult = new Page(studentByLike, totalSize, totalPage);
        return pageResult;
    }
}
