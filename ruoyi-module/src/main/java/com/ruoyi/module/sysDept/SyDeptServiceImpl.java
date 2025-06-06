package com.ruoyi.module.sysDept;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.module.sysDept.domain.SyDept;
import com.ruoyi.module.sysDept.mapper.SyDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyDeptServiceImpl implements SyDeptService {

    @Autowired
    private SyDeptMapper syDeptMapper;

    @Override
    public List<SyDept> selectSyDeptList(SyDept dept){
      return  syDeptMapper.selectDeptList(dept);
    }

    @Override
    public SyDept selectDeptById(Long deptId){
        return  syDeptMapper.selectDeptById(deptId);
    }

    @Override
    public int insertDept(SyDept dept){
        return  syDeptMapper.insertDept(dept);
    }

    @Override
    public boolean checkDeptNameUnique(SyDept dept){
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SyDept info = syDeptMapper.checkDeptNameUnique(dept.getDeptName(),dept.getParentId());
        if(info != null && info.getDeptId().longValue() != deptId.longValue()){
             return false;
        }
        return true;
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(Long deptId)
    {
        return syDeptMapper.selectNormalChildrenDeptById(deptId);
    }


}
