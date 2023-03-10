package cn.jasonhu.impl.service.impl;

import cn.jasonhu.commons.entity.Student;
import cn.jasonhu.commons.entity.Teacher;
import cn.jasonhu.impl.service.StudentService;
import cn.jasonhu.impl.service.TeacherService;
import cn.jasonhu.impl.service.TestTransService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author jason hu
 * @since 2021/3/24 9:18
 * <p>
 * 参考地址：https://segmentfault.com/a/1190000013341344 https://blog.csdn.net/yanxin1213/article/details/100582643
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

    /**
     * 1、Propagation.REQUIRED 1.1 外围方法未开启事务，内部方法在自己的事务中独立运行，外围方法异常不影响内部方法独立的事务。
     */
    @Override
    public void noTransExceptionRequired() {
        Teacher teacher = new Teacher().setUsername("教师11").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生11").setAge(15);
        studentService.addRequired(student);

        throw new RuntimeException("Propagation.REQUIRED：外围方法不存在事务并抛出异常，数据仍可插入");
    }

    /**
     * 1.2 外围方法未开启事务，插入“教师12”、“学生12”方法在自己的事务中独立运行， 插入“教师12”方法不受影响，插入“学生12”方法抛出异常回滚
     */
    @Override
    public void noTransRequiredException() {
        Teacher teacher = new Teacher().setUsername("教师12").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生12").setAge(15);
        studentService.addRequiredException(student);
    }

    /**
     * 1.3 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transExcteptionRequired() {
        Teacher teacher = new Teacher().setUsername("教师13").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生13").setAge(15);
        studentService.addRequired(student);

        throw new RuntimeException("Propagation.REQUIRED：外围方法存在事务并抛出异常，数据均不能插入");
    }

    /**
     * 1.4 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transRequiredExcteption() {
        Teacher teacher = new Teacher().setUsername("教师14").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生14").setAge(15);
        studentService.addRequiredException(student);
    }

    /**
     * 1.5 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，外围方法感知异常致使整体事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transRequiredCatchExcteption() {
        Teacher teacher = new Teacher().setUsername("教师15").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生15").setAge(15);
        try {
            studentService.addRequiredException(student);
        } catch (Exception e) {
            // 不加会出现Transaction rolled back because it has been marked as rollback-only异常
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
        }
    }


    /**
     * 2、Propagation.REQUIRES_NEW 2.1 外围方法未开启事务，内部方法都在自己的事务中独立运行,外围方法抛出异常回滚不会影响内部方法。
     */
    @Override
    public void noTransExceptionRequiresNew() {
        Teacher teacher = new Teacher().setUsername("教师21").setAge(30);
        teacherService.addRequiresNew(teacher);

        Student student = new Student().setUsername("学生21").setAge(15);
        studentService.addRequiresNew(student);

        throw new RuntimeException("Propagation.REQUIRES_NEW：外围方法不存在事务并抛出异常，数据仍可插入");
    }


    /**
     * 2.2 外围方法未开启事务，内部方法分别开启自己的事务，插入“学生22”方法抛出异常回滚，其他事务不受影响。
     */
    @Override
    public void noTransRequiresNewException() {
        Teacher teacher = new Teacher().setUsername("教师22").setAge(30);
        teacherService.addRequiresNew(teacher);

        Student student = new Student().setUsername("学生22").setAge(15);
        studentService.addRequiresNewException(student);
    }

    /**
     * 2.3 插入“学生23”方法和外围方法一个事务，插入“学生24”、“学生25”方法分别在独立的新建事务中， 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“学生23”的方法回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transExceptionRequiredRequiresNew() {
        Student student = new Student().setUsername("学生23").setAge(15);
        studentService.addRequired(student);

        student = new Student().setUsername("学生24").setAge(15);
        studentService.addRequiresNew(student);

        student = new Student().setUsername("学生25").setAge(15);
        studentService.addRequiresNew(student);

        throw new RuntimeException("学生23回滚，学生24、25插入");
    }

    /**
     * 2.4 插入“学生23”方法和外围方法一个事务，插入“学生24”、“学生25”方法分别在独立的新建事务中， 插入“学生25”方法抛出异常，首先插入
     * “学生25”方法的事务被回滚，异常继续抛出被外围方法感知， 外围方法事务亦被回滚，故插入“学生23”方法也被回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transRequiredRequiresNewException() {
        Student student = new Student().setUsername("学生23").setAge(15);
        studentService.addRequired(student);

        student = new Student().setUsername("学生24").setAge(15);
        studentService.addRequiresNew(student);

        student = new Student().setUsername("学生25").setAge(15);
        studentService.addRequiresNewException(student);
    }

    /**
     * 2.5 插入“学生23”方法和外围方法一个事务，插入“学生24”、“学生25”方法分别在独立的新建事务中， 插入“学生25”方法抛出异常，首先插入
     * “学生25”方法的事务被回滚，异常被catch不会被外围方法感知，外围方法事务不回滚，故插入“学生23”。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transRequiredRequiresNewCatchException() {
        Student student = new Student().setUsername("学生23").setAge(15);
        studentService.addRequired(student);

        student = new Student().setUsername("学生24").setAge(15);
        studentService.addRequiresNew(student);

        try {
            student = new Student().setUsername("学生25").setAge(15);
            studentService.addRequiresNewException(student);
        } catch (Exception e) {
            System.out.println("学生25回滚");
        }
    }

    /**
     * 3、Propagation.NESTED 3.1 外围方法未开启事务，内部方法在自己的事务中独立运行，外围方法异常不影响内部方法独立的事务。
     */
    @Override
    public void noTransExceptionNested() {
        Teacher teacher = new Teacher().setUsername("教师31").setAge(30);
        teacherService.addNested(teacher);

        Student student = new Student().setUsername("学生31").setAge(15);
        studentService.addNested(student);

        throw new RuntimeException("Propagation.NESTED：外围方法不存在事务并抛出异常，数据仍可插入");
    }

    /**
     * 3.2 外围方法未开启事务，插入“教师32”、“学生32”方法在自己的事务中独立运行， 插入“教师32”方法不受影响，插入“学生32”方法抛出异常回滚
     */
    @Override
    public void noTransNestedException() {
        Teacher teacher = new Teacher().setUsername("教师32").setAge(30);
        teacherService.addNested(teacher);

        Student student = new Student().setUsername("学生32").setAge(15);
        studentService.addNestedException(student);
    }

    /**
     * 3.3 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transExcteptionNested() {
        Teacher teacher = new Teacher().setUsername("教师33").setAge(30);
        teacherService.addNested(teacher);

        Student student = new Student().setUsername("学生33").setAge(15);
        studentService.addNested(student);

        throw new RuntimeException("Propagation.REQUIRED：外围方法存在事务并抛出异常，数据均不能插入");
    }

    /**
     * 3.4 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transNestedExcteption() {
        Teacher teacher = new Teacher().setUsername("教师34").setAge(30);
        teacherService.addNested(teacher);

        Student student = new Student().setUsername("学生34").setAge(15);
        studentService.addNestedException(student);
    }

    /**
     * 3.5 外围方法开启事务，内部事务为外围事务的子事务，插入“学生35”内部方法抛出异常，可以单独对子事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transNestedCatchExcteption() {
        Teacher teacher = new Teacher().setUsername("教师35").setAge(30);
        teacherService.addNested(teacher);

        Student student = new Student().setUsername("学生35").setAge(15);
        try {
            studentService.addNestedException(student);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

    /**
     * 4、Propagation.SUPPORTS 4.1 外围方法未开启事务，插入“教师41”、“学生41”方法也均未开启事务，因为不存在事务所以无论外围方法或者内部方法抛出异常都不会回滚。
     * 均插入成功
     */
    @Override
    public void noTransExceptionSupports() {
        Teacher teacher = new Teacher().setUsername("教师41").setAge(30);
        teacherService.addSupports(teacher);

        Student student = new Student().setUsername("学生41").setAge(15);
        studentService.addSupports(student);

        throw new RuntimeException("Propagation.SUPPORTS：外围方法不存在事务并抛出异常，数据仍可插入");
    }

    /**
     * 4.2 外围方法未开启事务，插入“教师42”、“学生42”方法也均未开启事务，因为不存在事务所以无论外围方法或者内部方法抛出异常都不会回滚。 均插入成功
     */
    @Override
    public void noTransSupportsException() {
        Teacher teacher = new Teacher().setUsername("教师42").setAge(30);
        teacherService.addSupports(teacher);

        Student student = new Student().setUsername("学生42").setAge(15);
        studentService.addSupportsException(student);
    }

    /**
     * 4.3 外围方法开启事务，插入“教师43”、“学生43”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。外 围方法或内部方法抛出异常，整个事务全部回滚。
     * 均未插入成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transExceptionSupports() {
        Teacher teacher = new Teacher().setUsername("教师43").setAge(30);
        teacherService.addSupports(teacher);

        Student student = new Student().setUsername("学生43").setAge(15);
        studentService.addSupports(student);

        throw new RuntimeException("Propagation.SUPPORTS：外围方法存在事务并抛出异常，数据仍可插入");
    }

    /**
     * 4.4 外围方法开启事务，插入“张三”、“李四”方法都在外围方法的事务中运行，加入外围方法事务，所以三个方法同一个事务。 外围方法或内部方法抛出异常，整个事务全部回滚。 均未插入成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transSupportsException() {
        Teacher teacher = new Teacher().setUsername("教师44").setAge(30);
        teacherService.addSupports(teacher);

        Student student = new Student().setUsername("学生44").setAge(15);
        studentService.addSupportsException(student);
    }


    /**
     * 5、Propagation.NOT_SUPPORTED 5.1 外围方法未开启事务，插入“教师51”方法在自己的事务中运行，插入“学生51”方法不在任何事务中运行。外围方法抛出异常，
     * 但是外围方法没有事务，所以其他内部事务方法不会被回滚，非事务方法更不会被回滚。 均插入成功
     */
    @Override
    public void noTransExceptionRequiredNotSupported() {
        Teacher teacher = new Teacher().setUsername("教师51").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生51").setAge(15);
        studentService.addNotSupported(student);

        throw new RuntimeException("Propagation.NOT_SUPPORTED：外围方法不存在事务并抛出异常，数据仍可插入");
    }

    /**
     * 4.2 外围方法未开启事务，插入“教师52”方法在自己的事务中运行，插入“学生52”方法不在任何事务中运行。 插入“学生52”方法抛出异常，首先因为插入“学生52”方法没有开启事务，所以“学生52”方法不会回滚，外围方法感知异常，
     * 但是因为外围方法没有事务，所以外围方法也不会回滚。 均插入成功
     */
    @Override
    public void noTransRequiredNotSupportedException() {
        Teacher teacher = new Teacher().setUsername("教师52").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生52").setAge(15);
        studentService.addNotSupportedException(student);
    }

    /**
     * 4.3 外围方法开启事务，因为插入“教师53”方法传播为required，所以和外围方法同一个事务。插入“学生53”方法不在任何事务中运行。
     * 外围方法抛出异常，外围方法所在的事务将会回滚，因为插入“教师53”方法和外围方法同一个事务，所以将会回滚。 “教师53”插入失败，“学生53”插入成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transExceptionRequiredNotSupported() {
        Teacher teacher = new Teacher().setUsername("教师53").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生53").setAge(15);
        studentService.addNotSupported(student);

        throw new RuntimeException("Propagation.NOT_SUPPORTED：外围方法存在事务并抛出异常，“教师53”插入失败，“学生53”插入成功");
    }

    /**
     * 4.4  外围方法开启事务，因为插入“教师54”方法传播为required，所以和外围方法同一个事务。插入“学生54”方法不在任何事务中运行。
     * 插入“学生54”方法抛出异常，因为此方法不开启事务，所以此方法不会被回滚，外围方法接收到了异常，所以外围事务需要回滚，因插入“教师54” 方法和外围方法同一事务，故被回滚。
     * “教师54”未插入，“学生54”插入
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transRequiredNotSupportedException() {
        Teacher teacher = new Teacher().setUsername("教师54").setAge(30);
        teacherService.addRequired(teacher);

        Student student = new Student().setUsername("学生54").setAge(15);
        studentService.addNotSupportedException(student);
    }

    /**
     * 6、Propagation.MANDATORY 6.1 外围方法未开启事务。内部插入“学生61”方法执行的时候因外围没有事务而直接抛出异常，具体插入方法都没有机会执行。
     * “教师61”未插入
     */
    @Override
    public void noTransMandatory() {
        Student student = new Student().setUsername("学生61").setAge(30);
        studentService.addMandatory(student);
    }


    /**
     * 6.2 外围方法开启事务，插入“教师62”方法和插入“学生62”方法都加入外围方法事务，外围方法抛出异常，事务回滚。 均未插入
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transExceptionMandatory() {
        Teacher teacher = new Teacher().setUsername("教师62").setAge(30);
        teacherService.addMandatory(teacher);

        Student student = new Student().setUsername("学生62").setAge(30);
        studentService.addMandatory(student);

        throw new RuntimeException("Propagation.MANDATORY：使用当前的事务，外围抛出异常， 事务回滚");
    }


    /**
     * 6.3 外围方法开启事务，插入“教师63”方法和插入“学生63”方法都加入外围方法事务，内部方法抛出异常，整个事务回滚。 均未插入
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transMandatoryException() {
        Teacher teacher = new Teacher().setUsername("教师63").setAge(30);
        teacherService.addMandatory(teacher);

        Student student = new Student().setUsername("学生63").setAge(30);
        studentService.addMandatoryException(student);
    }

    /**
     * 7 Propagation.NEVER 7.1 外围方法开启事务。内部插入“教师71”方法执行的时候因外围有事务而直接抛出异常，具体插入方法都没有机会执行。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void transNever() {
        Teacher teacher = new Teacher().setUsername("教师71").setAge(30);
        teacherService.addNever(teacher);
    }

    /**
     * 7.2 外围方法未开启事务，插入“教师72”方法和插入“学生72”方法也均无事务，任何异常都不会回滚。 均插入
     */
    @Override
    public void noTransExceptionNever() {
        Teacher teacher = new Teacher().setUsername("教师72").setAge(30);
        teacherService.addNever(teacher);

        Student student = new Student().setUsername("学生72").setAge(30);
        studentService.addNever(student);

        throw new RuntimeException("Propagation.NEVER：");
    }

    /**
     * 7.3 外围方法未开启事务，插入“教师73”方法和插入“学生73”方法也均无事务，任何异常都不会回滚。 均插入
     */
    @Override
    public void noTransNeverException() {
        Teacher teacher = new Teacher().setUsername("教师73").setAge(30);
        teacherService.addNever(teacher);

        Student student = new Student().setUsername("学生73").setAge(30);
        studentService.addNeverException(student);
    }

/**
 *  总结：
 *  Propagation.REQUIRED
 *  1、在外围方法未开启事务的情况下修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰
 *  2、外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，所有Propagation.REQUIRED
 *      修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
 *  Propagation.REQUIRES_NEW
 *  1、在外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰
 *  2、在外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会单独开启独立事务，且与外部方法事务也独立，
 *     内部方法之间、内部方法和外部方法事务均相互独立，互不干扰
 *  Propagation.NESTED
 *  1、在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，
 *      且开启的事务相互独立，互不干扰。
 *  2、在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，
 *      而内部子事务可以单独回滚而不影响外围主事务和其他子事务
 *
 *  REQUIRED,REQUIRES_NEW,NESTED异同
 *  由“1.3，1.4，1.5”和“3.3，3.4，3.5”对比，我们可知：
 *  NESTED和REQUIRED修饰的内部方法都属于外围方法事务，如果外围方法抛出异常，这两种方法的事务都会被回滚。
 *  但是REQUIRED是加入外围方法事务，所以和外围事务同属于一个事务，一旦REQUIRED事务抛出异常被回滚，外围方法事务也将被回滚。
 *  而NESTED是外围方法的子事务，有单独的保存点，所以NESTED方法抛出异常被回滚，不会影响到外围方法的事务。
 *
 *  由“2.3，2.4，2.5”和“3.3，3.4，3.5”对比，我们可知：
 *  NESTED和REQUIRES_NEW都可以做到内部方法事务回滚而不影响外围方法事务。但是因为NESTED是嵌套事务，所以外围方法回滚之后，
 *  作为外围方法事务的子事务也会被回滚。而REQUIRES_NEW是通过开启新的事务实现的，内部事务和外围事务是两个事务，
 *  外围事务回滚不会影响内部事务。
 */


}
