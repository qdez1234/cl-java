package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysModel;
import com.ruoyi.system.service.ISysModelServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 菜单信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/model")
public class SysModelController extends BaseController{

    @Autowired
    private ISysModelServeice modelServeice;

    /**
     * 获取模型列表
     */
    @GetMapping("/list")
    public AjaxResult list(SysModel model)
    {
        List<SysModel> menus = modelServeice.selectModelList(getUserId());
        return success(menus);
    }

    @GetMapping("/getModel/{id}")
    public AjaxResult getModel(@PathVariable Long id) {
        SysModel model = modelServeice.selectModelById(id);
        return success(model);
    }
    /**
     * 新增模型
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysModel model)
    {
        model.setCreateBy(getUsername());
        return success(modelServeice.insertModel(model));
    }
    /**
     * 修改模型
     */
    @PostMapping("/update")
    public AjaxResult update(@RequestBody SysModel model)
    {
        model.setUpdateBy(getUsername());
        return success(modelServeice.updateModel(model));
    }


}
