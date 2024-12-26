package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;

public class SysModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String source;


    private Long menuId;

    public Long getId() {
        return id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "SysModel{" +
                "id=" + id +
                ", source='" + source + '\'' +
                '}';
    }

}
