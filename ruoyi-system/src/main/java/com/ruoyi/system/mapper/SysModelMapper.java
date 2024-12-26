package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysModel;

import java.util.List;

public interface SysModelMapper {
    public List<SysModel> selectModelList(Long userId);

    public Long insertModel(SysModel model);

    public SysModel selectModelById(Long id);

}
