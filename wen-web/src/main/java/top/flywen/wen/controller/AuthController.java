package top.flywen.wen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.flywen.wen.base.BaseResult;
import top.flywen.wen.business.AuthService;
import top.flywen.wen.constant.ResponseConstant;
import top.flywen.wen.entity.User;

@RestController
@RequestMapping("/auth")
@Api(value = "认证接口", tags = {"认证接口"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST")
    public BaseResult login(@RequestBody User user) {
        User login = authService.login(user);
        BaseResult result = new BaseResult(ResponseConstant.ERROR_CODE,"login failure",user);
        if(login != null) {
            result = new BaseResult(ResponseConstant.SUCCESS_CODE,"login success", login);
        }
        return result;
    }


    @GetMapping("/logout")
    @ApiOperation(value = "登出", notes = "登出", httpMethod = "GET")
    public BaseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new BaseResult(ResponseConstant.SUCCESS_CODE,"success",null);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "注册", httpMethod = "POST")
    public BaseResult register(@RequestBody User user) {
        User register = authService.register(user);
        BaseResult result = new BaseResult(ResponseConstant.ERROR_CODE,"register failure", register);
        if(register != null) {
            result = new BaseResult(ResponseConstant.SUCCESS_CODE, "register success", register);
        }
        return result;
    }
}
