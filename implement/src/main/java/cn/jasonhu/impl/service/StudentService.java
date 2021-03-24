package cn.jasonhu.impl.service;

import cn.jasonhu.commons.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface StudentService extends IService<Student> {

    List<Student> getStudentList();
}
