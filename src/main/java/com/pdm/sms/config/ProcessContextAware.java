package com.pdm.sms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author xrm
 * @date 2024/1/17 15:47
 * @description 启动时自动释放指定端口的占用，避免端口被其他应用占用
 **/

/**
 * 这个 ProcessContextAware 类实现了 ServletContextAware 接口，
 * 它是一个 Spring 组件（由 @Component 注解标记）。这个类的主要作用是在 Spring
 * 应用程序启动时，释放指定端口（由 ${server.port} 指定）上可能存在的占用。
 *具体来说，setServletContext(ServletContext servletContext) 方法会在 Spring 应用程序启动时被调用。
 * 在这个方法中，它首先获取当前操作系统的名称，然后根据操作系统的类型执行不同的命令来释放指定端口的占用：
 */
@Component
public class ProcessContextAware implements ServletContextAware {
    @Value("${server.port}")
    private String port;

    @Override
    public void setServletContext(ServletContext servletContext) {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Windows系统关闭占用指定端口的逻辑
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "netstat -ano | findstr " + port);
                Process process = processBuilder.start();
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.trim().split("\\s+");
                    String pid = tokens[tokens.length - 1];
                    ProcessBuilder killProcess = new ProcessBuilder("cmd.exe", "/c", "taskkill /F /PID " + pid);
                    killProcess.start();
                }
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Linux或Mac OS系统关闭占用指定端口的逻辑
                ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "lsof -ti:" + port + " | xargs kill -9");
                processBuilder.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
