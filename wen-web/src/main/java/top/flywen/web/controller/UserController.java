package top.flywen.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.flywen.asset.base.BaseResult;
import top.flywen.business.UserService;
import top.flywen.config.GlobalConstant;
import top.flywen.asset.constant.ResponseConstant;
import top.flywen.asset.domain.MyPage;
import top.flywen.asset.entity.User;
import top.flywen.log.annotation.Log;

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
