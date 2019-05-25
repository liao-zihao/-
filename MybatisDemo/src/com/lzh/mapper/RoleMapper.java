package com.lzh.mapper;

import com.lzh.pojo.Role;

import java.io.InputStream;
import java.util.List;

public interface RoleMapper {
    public int insertRole(Role role);
    public int deleteRole(Integer id);
    public int updateRole(Role role);
    public Role getRole(Integer id);
    public List<Role> findRoles(String roleName);
}
