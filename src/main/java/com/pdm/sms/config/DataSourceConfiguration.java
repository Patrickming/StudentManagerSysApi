package com.pdm.sms.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ExceptionSorter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xrm
 * date 2024/1/17 15:24
 * description 数据源配置和对数据库异常进行排序和处理
 */
@Configuration
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public DruidDataSource createDataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);

        // 关闭连接后不自动commit
        dataSource.setDefaultAutoCommit(false);
        // 设置连接异常处理
        dataSource.setBreakAfterAcquireFailure(true);
        // 将SQLException抛出重要配置
        dataSource.setFailFast(true);
        dataSource.setConnectionErrorRetryAttempts(0);
        // 配置自定义的异常处理器
        dataSource.setExceptionSorter(new CustomExceptionSorter());

        // 关闭Druid连接池内部的异常处理
        // dataSource.setFilters("stat");
        return dataSource;
    }

}

//对异常进行排序和处理
//当 DruidDataSource 在处理数据库连接时遇到 SQLException，它会使用 CustomExceptionSorter 来决定如何处理这个异常。
class CustomExceptionSorter implements ExceptionSorter {

    @Override
    public boolean isExceptionFatal(SQLException e) {
        // 将所有异常视为致命异常，即抛出到上层
        // 打印异常堆栈信息
        e.printStackTrace();
        return true;
    }

    @Override
    public void configFromProperties(Properties properties) {
        // 配置信息可以为空
    }
}
