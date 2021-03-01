package top.flywen.wen.base;

import top.flywen.wen.constant.ResponseConstant;

import java.io.Serializable;

public class BaseResult implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 结果集码
     **/
    private int code = ResponseConstant.SUCCESS_CODE;

    /**
     * 结果消息
     **/
    private String message;

    /**
     * 防止重复提交的Token
     **/
    private String token;

    /**
     * 结果数据
     **/
    private Object result;

    public BaseResult() {
        super();
    }

    public BaseResult(int code) {
        super();
        this.code = code;
    }

    public BaseResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BaseResult(int code, Object result) {
        super();
        this.code = code;
        this.result = result;
    }

    public BaseResult(int code, String message, Object result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
