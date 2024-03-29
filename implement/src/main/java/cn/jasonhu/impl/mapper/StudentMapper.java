package cn.jasonhu.impl.mapper;

import cn.jasonhu.commons.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    List<Student> getStudentList();
}
