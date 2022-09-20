package top.flywen.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import top.flywen.asset.base.BaseResult;
import top.flywen.asset.constant.ResponseConstant;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        ServletOutputStream writer = response.getOutputStream();
        BaseResult result= new BaseResult();
        try{
            result.setCode(ResponseConstant.ERROR_CODE);
            result.setMessage("鉴权失败");
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = objectMapper.writeValueAsBytes(result);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            writer.write(bytes);
        }catch (Exception e){
        }finally {
            if(writer != null){
                writer.flush();
                writer.close();
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
