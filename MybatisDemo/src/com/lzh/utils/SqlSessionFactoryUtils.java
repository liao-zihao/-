package com.lzh.utils;

import com.lzh.mapper.RoleMapper;
import com.lzh.pojo.Role;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private final static Class<SqlSessionFactoryUtils>LOCK=SqlSessionFactoryUtils.class;
    private static SqlSessionFactory sqlSessionFactory=null;
    private SqlSessionFactoryUtils(){}

    public static SqlSessionFactory getSqlSessionFactory2(){
        synchronized (LOCK){
            if(sqlSessionFactory!=null){
                return sqlSessionFactory;
            }
            String resource="mybatis-config.xml";
            InputStream inputStream;
            try{
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            }catch(IOException e){
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            PooledDataSource dataSource=new PooledDataSource();
            dataSource.setDriver("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setUrl("jdbc:mysql://localhost:3306/chapter3?serverTimezone=GMT%2B8");
            dataSource.setDefaultAutoCommit(true);

            TransactionFactory transactionFactory=new JdbcTransactionFactory();
            Environment environment=new Environment("development",transactionFactory,dataSource);

            Configuration configuration=new Configuration(environment);
            configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
            configuration.addMapper(RoleMapper.class);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(configuration);
            return sqlSessionFactory;
        }
    }

    public static SqlSession openSqlSession(){
        if(sqlSessionFactory==null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
