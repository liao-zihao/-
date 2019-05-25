package com.lzh.main;

import com.lzh.mapper.RoleMapper;
import com.lzh.pojo.Role;
import com.lzh.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args){
        testRoleMapper();
    }
    private static void testRoleMapper(){
        Logger log=Logger.getLogger(Main.class);
        SqlSession sqlSession=null;
        try{
            sqlSession= SqlSessionFactoryUtils.openSqlSession();
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            Role role=new Role(1,"abc","def");
            roleMapper.updateRole(role);
            //Role role=roleMapper.getRole(2);
            //System.out.println(role.getRoleName()+' '+role.getNote());
            //role=new Role("abc","def");
            //roleMapper.insertRole(role);
            System.out.println(role.getRoleName()+' '+role.getNote());
            log.info(role.getRoleName());
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
