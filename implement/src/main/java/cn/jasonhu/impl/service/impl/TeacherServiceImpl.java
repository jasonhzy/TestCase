package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.Teacher;
import cn.jasonhu.impl.Mapper.TeacherMapper;
import cn.jasonhu.impl.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Override
    public List<Teacher> getTeacherList() {
        return baseMapper.getTeacherList();
    }
}
