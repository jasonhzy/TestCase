package cn.jasonhu.impl.service;

import cn.jasonhu.commons.entity.Teacher;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface TeacherService extends IService<Teacher> {

    List<Teacher> getTeacherList();

    void addRequired(Teacher teacher);

    void addRequiresNew(Teacher teacher);

    void addNested(Teacher teacher);

    void addSupports(Teacher teacher);

    void addNotSupported(Teacher teacher);

    void addMandatory(Teacher teacher);

    void addNever(Teacher teacher);


}
