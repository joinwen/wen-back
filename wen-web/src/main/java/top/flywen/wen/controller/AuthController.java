package top.flywen.wen.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.flywen.wen.base.BaseResult;
import top.flywen.wen.business.AuthService;
import top.flywen.wen.constant.ResponseConstant;
import top.flywen.wen.entity.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public BaseResult login(@RequestBody User user) {
        User login = authService.login(user);
        BaseResult result = new BaseResult(ResponseConstant.ERROR_CODE,"login failure",user);
        if(login != null) {
            result = new BaseResult(ResponseConstant.SUCCESS_CODE,"login success", login);
        }
        return result;
    }


    @GetMapping("/logout")
    public BaseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new BaseResult(ResponseConstant.SUCCESS_CODE,"success",null);
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody User user) {
        User register = authService.register(user);
        BaseResult result = new BaseResult(ResponseConstant.ERROR_CODE,"register failure", register);
        if(register != null) {
            result = new BaseResult(ResponseConstant.SUCCESS_CODE, "register success", register);
        }
        return result;
    }
}
