package com.lzh.mapper;

import com.lzh.param.PageParams;
import com.lzh.param.RoleParams;
import com.lzh.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    public Role getRole(Long id);
    public List<Role> findRolesByMap (Map<String,Object> parameterMap);
    public List<Role> findRolesByAnnotation(@Param("roleName")String roleName,@Param("note") String note);
    public List<Role> findRolesByBean(RoleParams roleParams);
    public List<Role> findByMix(@Param("params") RoleParams roleParams,@Param("page") PageParams PageParams);
    public Role getRoleUseResultMap(Long id);
    public int insertRole(Role role);
    public int insertRole2(Role role);
    public int updateRole(Role role);
    public int deleteRole(Long id);
}
