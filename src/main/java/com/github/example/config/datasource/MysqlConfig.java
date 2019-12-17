package com.github.example.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 使用mybatis对实现类分包映射,实现不同的双数据源
 */
@Primary
@Configuration
@MapperScan(basePackages = "com.github.example.dao.mysql",sqlSessionFactoryRef = "MysqlSessionFactory")

public class MysqlConfig {
    @Bean("MysqlDataSource")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSource getMysqlDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("MysqlSessionFactory")
    public SqlSessionFactory MysqlSqlSessionFactory(@Qualifier("MysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/mysql/*.xml"));
        return bean.getObject();
    }

    @Bean("MysqlSessionTemplate")
    public SqlSessionTemplate MysqlSqlSessionTemplate(@Qualifier("MysqlSessionFactory") SqlSessionFactory sessionFactory){
        return new SqlSessionTemplate(sessionFactory);
    }
}
