package cn.jasonhu.impl.service;

import cn.jasonhu.commons.entity.Teacher;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface TeacherService extends IService<Teacher> {

    List<Teacher> getTeacherList();
}
