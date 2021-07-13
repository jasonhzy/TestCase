package cn.jasonhu.learn.controller;

import cn.jasonhu.commons.response.ResultRes;
import cn.jasonhu.commons.response.ReturnResult;
import cn.jasonhu.impl.service.TeacherService;
import cn.jasonhu.impl.service.TestTransService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason hu
 * @since 2021/3/23 16:19
 */
@Api(tags = "测试事务传播性")
@RestController
@RequestMapping("/test/trans")
public class TestTransController {

    @Resource
    private TestTransService testTransService;

    @GetMapping("/teacher/list")
    @ApiOperation(value = "获取教师列表")
    public ReturnResult getTeacherList() {
        return ResultRes.success(testTransService.getUserList("teacher"));
    }

    @GetMapping("/student/list")
    @ApiOperation(value = "获取学生列表")
    public ReturnResult getStudentList() {
        return ResultRes.success(testTransService.getUserList("student"));
    }

    @GetMapping("/propagation/required")
    @ApiOperation(value = "测试REQUIRED（默认隔离级别）")
    public ReturnResult testPropagationRequired() {
        //testTransService.noTransExceptionRequired();
        //testTransService.noTransRequiredException();
        //testTransService.transExcteptionRequired();
        //testTransService.transRequiredExcteption();
        //testTransService.transRequiredCatchExcteption();

        //testTransService.noTransExceptionRequiresNew();
        //testTransService.transRequiredRequiresNewException();
        //testTransService.transRequiredRequiresNewCatchException();
        //testTransService.noTransExceptionNested();
        //testTransService.noTransNestedException();
        //testTransService.transExcteptionNested();
        //testTransService.transNestedExcteption();
        //testTransService.transNestedCatchExcteption();
        //testTransService.noTransExceptionSupports();
        //testTransService.noTransSupportsException();
        //testTransService.transExceptionSupports();
        //testTransService.transSupportsException();

        //testTransService.noTransExceptionRequiredNotSupported();
        //testTransService.noTransRequiredNotSupportedException();
        //testTransService.transExceptionRequiredNotSupported();
        //testTransService.transRequiredNotSupportedException();
        testTransService.noTransMandatory();
        return ResultRes.success();
    }
}
