package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.Student;
import cn.jasonhu.impl.Mapper.StudentMapper;
import cn.jasonhu.impl.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements
        StudentService {


    @Override
    public List<Student> getStudentList() {
        return baseMapper.getStudentList();
    }
}
