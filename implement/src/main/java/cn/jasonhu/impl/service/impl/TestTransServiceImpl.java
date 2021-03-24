package cn.jasonhu.impl.service.impl;

import cn.jasonhu.impl.service.StudentService;
import cn.jasonhu.impl.service.TeacherService;
import cn.jasonhu.impl.service.TestTransService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author jason hu
 * @since 2021/3/24 9:18
 */
@Service
public class TestTransServiceImpl implements TestTransService {

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    @Override
    public Object getUserList(String type) {
        Object obj = null;
        switch (type) {
            case "teacher":
                obj = teacherService.getTeacherList();
                break;
            case "student":
                obj = studentService.getStudentList();
                break;
        }
        return obj;
    }
}
