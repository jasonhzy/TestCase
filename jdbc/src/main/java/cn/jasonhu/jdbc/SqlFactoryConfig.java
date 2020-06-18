package cn.jasonhu.jdbc;

import javax.sql.DataSource;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement // 加上这个注解，使得支持事务
@AutoConfigureAfter(DataSourceConfig.class)
public class SqlFactoryConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    @Value("${jdbc.prod}")
    private Boolean prod;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory bean;
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session
                    .Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            if (!prod){
                configuration.setLogImpl(StdOutImpl.class);
            }
            sessionFactoryBean.setConfiguration(configuration);
            sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
            bean = sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
