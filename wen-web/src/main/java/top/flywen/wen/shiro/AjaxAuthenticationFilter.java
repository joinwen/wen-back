package top.flywen.wen.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import top.flywen.wen.base.BaseResult;
import top.flywen.wen.constant.ResponseConstant;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        ServletOutputStream writer = response.getOutputStream();
        BaseResult result = new BaseResult();
        try{
            result.setCode(ResponseConstant.ERROR_CODE);
            result.setMessage("认证失败");
            ObjectMapper objectMapper = new ObjectMapper();
            byte [] bytes = objectMapper.writeValueAsBytes(result);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            writer.write(bytes);
        }catch (Exception e){
        }finally {
            if(null != writer){
                writer.flush();
                writer.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(AjaxAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
