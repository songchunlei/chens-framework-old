package com.chens.core.vo;

import com.chens.core.entity.SysMenu;

/**
 * 菜单树
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/19
 */
public class MenuTree extends ZTree {
    String icon;
    String url;
    String description;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void getMenu(SysMenu sysMenu){
        this.id = sysMenu.getId();
        this.name = sysMenu.getName();
        this.pId = sysMenu.getParentId();
        this.icon = sysMenu.getIcon();
        this.url = sysMenu.getUrl();
        this.description = sysMenu.getDescription();
        if(sysMenu.getIsopen()!=null && sysMenu.getIsopen()==1)
        {
            this.open = true;
        }
        else
        {
            this.open = false;
        }
    }
}
