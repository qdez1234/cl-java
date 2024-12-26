package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysModel;

import java.util.List;

/**
 * 菜单 业务层
 *
 * @author ruoyi
 */
public interface ISysModelServeice
{
    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysModel> selectModelList(Long userId);

    public Long insertModel(SysModel model);

    public SysModel selectModelById(Long id);
}

