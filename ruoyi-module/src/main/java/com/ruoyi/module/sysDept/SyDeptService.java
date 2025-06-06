package com.ruoyi.module.sysDept;

import com.ruoyi.module.sysDept.domain.SyDept;

import java.util.List;

public interface SyDeptService {

    public List<SyDept> selectSyDeptList(SyDept syDept);

    public SyDept selectDeptById(Long id);

    public int insertDept(SyDept syDept);

    public boolean checkDeptNameUnique(SyDept syDept);

    public int selectNormalChildrenDeptById(Long deptId);

}
