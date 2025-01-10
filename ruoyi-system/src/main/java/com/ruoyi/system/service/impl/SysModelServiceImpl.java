package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysModel;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.mapper.SysModelMapper;
import com.ruoyi.system.service.ISysModelServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModelServiceImpl implements ISysModelServeice {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysModelMapper modelMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysModel> selectModelList(Long userId){
        return modelMapper.selectModelList(userId);
    }

    public SysModel selectModelById(Long id){
        return modelMapper.selectModelById(id);
    }

    public int updateModel(SysModel model){
        return modelMapper.updateModel(model);
    }
    /*
    * 新增模型信息
    * */
    @Override
    public Long insertModel(SysModel model){
        Long menuId = model.getMenuId();
        modelMapper.insertModel(model);
        Long modelId = model.getId();
        updateModelIdInMenu(menuId,modelId);
        System.out.println("插入成功后的 ID：" + modelId);
        return modelId;
    }
    /*
    * 更新菜单对应的modelId
    * */
    private void updateModelIdInMenu(Long menuId,Long modelId){
        SysMenu menu = menuMapper.selectMenuById(menuId);
        if(menu!=null){
            menu.setModelId(modelId);
            menuMapper.updateMenu(menu);
        }else{
           System.out.println("找不到菜单");
        }
    }

}
