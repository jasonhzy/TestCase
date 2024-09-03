package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.Student;
import cn.jasonhu.commons.entity.Teacher;
import cn.jasonhu.impl.mapper.StudentMapper;
import cn.jasonhu.impl.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements
        StudentService {

    @Override
    public List<Student> getStudentList() {
        return baseMapper.selectList(null);
    }

    public Student getUserById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRequired(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRequiredException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.REQUIRED：内部方法抛出异常");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNew(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNewException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.REQUIRES_NEW：内部方法抛出异常");
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNested(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNestedException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.NESTED：内部方法抛出异常");
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSupports(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSupportsException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.SUPPORTS：内部方法抛出异常");
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void addNotSupported(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void addNotSupportedException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.NOT_SUPPORTED：内部方法抛出异常");
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addMandatory(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addMandatoryException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.MANDATORY：内部方法抛出异常");
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void addNever(Student student) {
        baseMapper.insert(student);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void addNeverException(Student student) {
        baseMapper.insert(student);
        throw new RuntimeException("Propagation.NEVER：内部方法抛出异常");
    }
}
