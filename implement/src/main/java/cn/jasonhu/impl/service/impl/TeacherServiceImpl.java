package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.Teacher;
import cn.jasonhu.impl.mapper.TeacherMapper;
import cn.jasonhu.impl.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements
        TeacherService {

    @Override
    public List<Teacher> getTeacherList() {
        return baseMapper.getTeacherList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRequired(Teacher teacher) {
        baseMapper.insert(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void addRequiresNew(Teacher teacher) {
        baseMapper.insert(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addNested(Teacher teacher) {
        baseMapper.insert(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addSupports(Teacher teacher) {
        baseMapper.insert(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void addNotSupported(Teacher teacher) {
        baseMapper.insert(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void addMandatory(Teacher teacher) {
        baseMapper.insert(teacher);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void addNever(Teacher teacher) {
        baseMapper.insert(teacher);
    }

}
