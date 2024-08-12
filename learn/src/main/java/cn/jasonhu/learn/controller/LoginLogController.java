package cn.jasonhu.learn.controller;

import cn.jasonhu.commons.response.ResponseResult;
import cn.jasonhu.impl.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jason hu
 * @since 2021/3/23 16:19
 */
@Api(tags = "测试Spring事件")
@RestController
@RequestMapping("/test/event")
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService;

    @GetMapping("/login")
    @ApiOperation(value = "登录日志")
    public ResponseResult getTeacherList() {
        loginLogService.saveLog();
        return ResponseResult.success();
    }
}
