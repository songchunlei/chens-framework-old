package com.chens.admin.vo;

import com.chens.admin.entity.SysMenu;
import com.chens.core.vo.TreeVo;

/**
 * 菜单树
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/19
 */
public class MenuTree extends TreeVo {
    private String icon;
    private String url;
    private String description;
    private String code;

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

    public MenuTree(SysMenu sysMenu){
        this.id = sysMenu.getId();
        this.name = sysMenu.getName();
        this.pId = sysMenu.getParentId();
        this.icon = sysMenu.getIcon();
        this.url = sysMenu.getUrl();
        this.description = sysMenu.getDescription();
        this.codeType = sysMenu.getType();
        this.code = sysMenu.getCode();
        if(sysMenu.getIsopen()!=null && sysMenu.getIsopen()==1)
        {
            this.checked = true;
        }
        else
        {
            this.checked = false;
        }
    }

    public MenuTree()
    {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MenuTree(String icon, String url, String description) {
        this.icon = icon;
        this.url = url;
        this.description = description;
    }

    public MenuTree(String id, String pId, String name, String codeType, boolean open, String icon, String url, String description) {
        super(id, pId, name, codeType, open);
        this.icon = icon;
        this.url = url;
        this.description = description;
    }

    @Override
    public MenuTree clone() {
        return new MenuTree(this.id,this.pId,this.name,this.codeType,this.checked,this.icon,this.url,this.description);
    }
}
