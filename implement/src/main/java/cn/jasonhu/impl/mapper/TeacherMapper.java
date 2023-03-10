package cn.jasonhu.impl.mapper;

import cn.jasonhu.commons.entity.Teacher;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> getTeacherList();
}
