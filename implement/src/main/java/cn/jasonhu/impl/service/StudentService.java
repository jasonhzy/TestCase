package cn.jasonhu.impl.service;

import cn.jasonhu.commons.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface StudentService extends IService<Student> {

    List<Student> getStudentList();

    Student getUserById(Integer id);

    void addRequired(Student student);

    void addRequiredException(Student student);

    void addRequiresNew(Student student);

    void addRequiresNewException(Student student);

    void addNested(Student student);

    void addNestedException(Student student);

    void addSupports(Student student);

    void addSupportsException(Student student);

    void addNotSupported(Student student);

    void addNotSupportedException(Student student);

    void addMandatory(Student student);

    void addMandatoryException(Student student);

    void addNever(Student student);

    void addNeverException(Student student);
}
