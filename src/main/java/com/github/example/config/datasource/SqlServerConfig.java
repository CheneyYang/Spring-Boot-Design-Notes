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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.github.example.dao.sqlserver",sqlSessionFactoryRef = "SqlServerSessionFactory")
public class SqlServerConfig {
    @Bean("SqlServerDataSource")
    @ConfigurationProperties("spring.datasource.sqlserver")
    public DataSource getSqlServerDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("SqlServerSessionFactory")
    public SqlSessionFactory SqlServerSqlSessionFactory(@Qualifier("SqlServerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/sqlserver/*.xml"));
        return bean.getObject();
    }

    @Bean("SqlServerSessionTemplate")
    public SqlSessionTemplate SqlServerSqlSessionTemplate(@Qualifier("SqlServerSessionFactory") SqlSessionFactory sessionFactory){
        return new SqlSessionTemplate(sessionFactory);
    }
}
