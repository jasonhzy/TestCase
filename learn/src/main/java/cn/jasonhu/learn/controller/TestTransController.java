package cn.jasonhu.learn.controller;

import cn.jasonhu.commons.response.ResponseResult;
import cn.jasonhu.impl.service.TestTransService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseResult getTeacherList() {
        return ResponseResult.success(testTransService.getUserList("teacher"));
    }

    @GetMapping("/student/list")
    @ApiOperation(value = "获取学生列表")
    public ResponseResult getStudentList() {
        return ResponseResult.success(testTransService.getUserList("student"));
    }

    @GetMapping("/student/{id}")
    @ApiOperation(value = "获取学生信息")
    public ResponseResult getStudentList(@PathVariable Integer id) {
        return ResponseResult.success(testTransService.getUserById(id));
    }

    @GetMapping("/propagation/required")
    @ApiOperation(value = "测试REQUIRED（默认隔离级别）")
    public ResponseResult testPropagationRequired() {
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
        return ResponseResult.success();
    }
}
