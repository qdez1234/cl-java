package com.ruoyi.module.sysDept.mapper;

import com.ruoyi.module.sysDept.domain.SyDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SyDeptMapper {

    public List<SyDept> selectDeptList(SyDept dept);

    public SyDept selectDeptById(Long deptId);

    public int insertDept(SyDept dept);

    public int selectNormalChildrenDeptById(Long deptId);

    public SyDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);
}
