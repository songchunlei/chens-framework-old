package com.chens.core.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description: Ztree节点
 * @author scl
 * @date 2016年11月17日 上午8:56:18
 * @version V1.0
 */
public class TreeVo<T extends TreeVo> implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4252766469774648992L;
    /**
     * id
     */
	protected String id;
    /**
     * 父id
     */
    protected String pId;
    /**
     * 名称
     */
    protected String name;
    /**
     * 分类
     */
    protected String codeType;
    /**
     * 是否选中
     */
    protected boolean checked;
    /**
     * 子列表
     */
    protected List<T> children;
    
    public TreeVo()
    {
    	
    }
    public TreeVo(String id, String pId, String name, String codeType,boolean checked)
    {
    	this.id = id;
    	this.pId = pId;
    	this.name = name;
    	this.codeType = codeType;
    	this.checked = checked;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getpId()
    {
        return pId;
    }
    public void setpId(String pId)
    {
        this.pId = pId;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
    public boolean isChecked()
    {
        return checked;
    }
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
    
    /**
     * 重构等于
     */
    @Override
    public boolean equals(Object obj) {
        //如果是引用同一个实例
        if(this ==obj){
            return true;            
        } if (obj!=null && obj instanceof TreeVo) {
            //当id一样，就认为树结构一致
            TreeVo temp = (TreeVo) obj;
            return this.id.equals(temp.id);   
        }else if(obj!=null && obj instanceof String){
            return this.id.equals((String)obj);
        }else{
            return false;
        }
    }
    
    @Override
    public TreeVo clone(){
        TreeVo newzTreeDto = new TreeVo(this.id,this.pId,this.name,this.codeType,this.checked);
        return newzTreeDto;
    }


    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    /**
     * 加入子列表
     * @param node
     */
    public void add(T node){
        if(children==null)
        {
            children = new ArrayList<>();
        }
        children.add(node);
    }
}
