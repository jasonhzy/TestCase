package cn.jasonhu.learn.controller;

import cn.jasonhu.commons.response.ResultRes;
import cn.jasonhu.commons.response.ReturnResult;
import cn.jasonhu.impl.service.UserService;
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
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "获取列表")
    public ReturnResult getList() {
        return ResultRes.success(userService.getUserList());
    }
}
