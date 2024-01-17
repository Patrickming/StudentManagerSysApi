package com.pdm.sms.utils.Token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xrm
 * @date 2024/1/17 16:13
 * @description 需要登录才能进行操作的注解UserLoginToken
 * 这个注解用于标记需要进行 token 验证的接口或类。如果一个方法或类被 UserLoginToken 注解标记，并且 required 属性为 true，
 * 那么在处理 HTTP 请求时，会对该方法或类进行 token 验证。只有当请求头中的 token 有效时，才能访问该接口。
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
