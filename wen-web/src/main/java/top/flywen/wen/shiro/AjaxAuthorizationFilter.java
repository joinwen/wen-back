package top.flywen.wen.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import top.flywen.wen.base.BaseResult;
import top.flywen.wen.constant.ResponseConstant;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse response1= (HttpServletResponse) response;
        ServletOutputStream outputStream = response.getOutputStream();
        BaseResult result=new BaseResult();
        try{

            result.setMessage("鉴权失败");
            result.setCode(ResponseConstant.ERROR_CODE);
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = objectMapper.writeValueAsString(result).getBytes();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            outputStream.write(bytes);
        }catch (Exception e){

        }finally {
            if(null!=outputStream){
                outputStream.flush();
                outputStream.close();
            }
        }
        return false;
    }
    @Bean
    public FilterRegistrationBean registration(AjaxAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
