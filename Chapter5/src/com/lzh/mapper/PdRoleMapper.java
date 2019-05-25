package com.lzh.mapper;

import com.lzh.param.PdCountRoleParams;
import com.lzh.param.PdFindRoleParams;

public interface PdRoleMapper {
    public void countRole(PdCountRoleParams pdCountRoleParams);
    public void findRole(PdFindRoleParams pdFindRoleParams);
}
