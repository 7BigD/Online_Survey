package com.scau.controller;


import com.scau.entity.Result;
import com.scau.entity.User;
import com.scau.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BigD
 * @since 2021-06-05
 */
@Api(value = "UserController", tags = { "用户接口controller" })
@Controller
@RequestMapping("//user")
public class UserController {

    @Autowired
    private UserService userService;

    //注册 跟create_time绑定
    //登录功能
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String", name = "username", dataType = "String", required = true, value = "用户名"),
            @ApiImplicitParam(paramType = "String", name = "password", dataType = "String", required = true, value = "密码")
    })
//    @ApiResponses(value = { @ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败")
    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password) {

        List<User> users = userService.list();
        users.forEach(System.out::println);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return Result.ok().data("user",user);
            }
        }
        return Result.error();
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        userService.save(user);
//        return user;
    }

    @ApiOperation(value = "用户测试（暂时不管）", httpMethod = "GET")
    @GetMapping("/{id}")
    @ResponseBody
    public User display(@PathVariable(name = "id") Integer id) {

        List<User> users = userService.list();
        users.forEach(System.out::println);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;

    }
}

