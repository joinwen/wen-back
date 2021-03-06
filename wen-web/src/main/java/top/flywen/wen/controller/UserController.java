package top.flywen.wen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.flywen.wen.base.BaseResult;
import top.flywen.wen.business.UserService;
import top.flywen.wen.config.GlobalConstant;
import top.flywen.wen.constant.ResponseConstant;
import top.flywen.wen.domain.MyPage;
import top.flywen.wen.entity.User;
import top.flywen.wen.log.annotation.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Api(value = "用户接口", tags = {"用户接口"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @Log("查询所有用户")
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户", httpMethod = "GET")
    public BaseResult getAllUsers() {
        System.out.println(GlobalConstant.USER_INFO);
        List<User> allUsers = userService.getAllUsers();
        return new BaseResult(ResponseConstant.SUCCESS_CODE, allUsers);
    }
    @GetMapping("/page")
    @ApiOperation(value = "用户分页", notes = "用户分页", httpMethod = "GET")
    public BaseResult getUsersByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        MyPage<User> pg = new MyPage<>(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("username","wen");
        IPage<User> usersByPage = userService.getUsersByPage(pg, map);
        return new BaseResult(ResponseConstant.SUCCESS_CODE, usersByPage);
    }
}
