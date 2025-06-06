package com.ruoyi.module.sysDept;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.module.sysDept.domain.SyDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/dept")
public class SyDeptController extends BaseController {
    @Autowired
    private SyDeptService syDeptService;

    @GetMapping("/list")
    public AjaxResult list(SyDept dept) {
        List<SyDept> list = syDeptService.selectSyDeptList(dept);
        return success(list);
    }

    @GetMapping("/{deptId}")
    public AjaxResult get(@PathVariable Long deptId) {
        return success(syDeptService.selectDeptById(deptId));
    }

    @GetMapping("/add")
    public AjaxResult add(SyDept dept) {
        if (!syDeptService.checkDeptNameUnique(dept))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && syDeptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            return error("该部门包含未停用的子部门！");
        }
        dept.setCreateBy(getUsername());

        return success(syDeptService.insertDept(dept));
    }

//    /**
//     * 修改部门
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
//    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(SyDept dept)
//    {
//        Long deptId = dept.getDeptId();
//        syDeptService.checkDeptDataScope(deptId);
//        if (!syDeptService.checkDeptNameUnique(dept))
//        {
//            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
//        }
//        else if (dept.getParentId().equals(deptId))
//        {
//            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
//        }
//        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && syDeptService.selectNormalChildrenDeptById(deptId) > 0)
//        {
//            return error("该部门包含未停用的子部门！");
//        }
//        dept.setUpdateBy(getUsername());
//        return toAjax(syDeptService.updateDept(dept));
//    }

}