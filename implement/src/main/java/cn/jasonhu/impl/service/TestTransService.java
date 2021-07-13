package cn.jasonhu.impl.service;

/**
 * @author jason hu
 * @since 2021/3/24 9:18
 */
public interface TestTransService {

    Object getUserList(String type);

    void noTransExceptionRequired();

    void noTransRequiredException();

    void transExcteptionRequired();

    void transRequiredExcteption();

    void transRequiredCatchExcteption();

    void noTransExceptionRequiresNew();

    void noTransRequiresNewException();

    void transExceptionRequiredRequiresNew();

    void transRequiredRequiresNewException();

    void transRequiredRequiresNewCatchException();

    void noTransExceptionNested();

    void noTransNestedException();

    void transExcteptionNested();

    void transNestedExcteption();

    void transNestedCatchExcteption();

    void noTransExceptionSupports();

    void noTransSupportsException();

    void transExceptionSupports();

    void transSupportsException();

    void noTransExceptionRequiredNotSupported();

    void noTransRequiredNotSupportedException();

    void transExceptionRequiredNotSupported();

    void transRequiredNotSupportedException();

    void noTransMandatory();

    void transExceptionMandatory();

    void transMandatoryException();

    void transNever();

    void noTransExceptionNever();

    void noTransNeverException();
}
