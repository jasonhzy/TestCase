package cn.jasonhu.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.PropertySource;

/**
 * @description 数据库源配置
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.validationQuery}")
    private String validationQuery;

    @Value("${jdbc.initialSize}")
    private Integer initialSize;
    @Value("${jdbc.maxActive}")
    private Integer maxActive;
    @Value("${jdbc.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${jdbc.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;
    @Value("${jdbc.testWhileIdle}")
    private Boolean testWhileIdle;

    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setValidationQuery(validationQuery);

        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        return dataSource;
    }
}
