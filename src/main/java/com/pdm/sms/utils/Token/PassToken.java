package com.pdm.sms.utils.Token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xrm
 * @date 2024/1/17 16:12
 * @description 用来跳过验证的PassToken
 * 这个注解用于标记不需要进行 token 验证的接口或类。如果一个方法或类被 PassToken 注解标记，并且 required 属性为 true，
 * 那么在处理 HTTP 请求时，不会对该方法或类进行 token 验证，即使请求头中没有 token，也可以访问该接口。
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}

