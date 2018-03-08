package com.chens.core.entity;

import java.io.Serializable;

/**
 * 
 * @Description: Ztree节点
 * @author scl
 * @date 2016年11月17日 上午8:56:18
 * @version V1.0
 */
public class ZTree implements Serializable{

	private static final long serialVersionUID = -7247934205818328691L;

    private Long id;//id
    private Long pId;//父节点id
    private String name;//节点名称
    private String codeType;//分类
    private boolean open;//打开
    private boolean checked;//选中
    
    public ZTree()
    {
    	
    }
    public ZTree(Long id,Long pId,String name,String codeType,boolean open)
    {
    	this.id = id;
    	this.pId = pId;
    	this.name = name;
    	this.codeType = codeType;
    	this.open = open;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getpId()
    {
        return pId;
    }
    public void setpId(Long pId)
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
	public boolean isOpen()
    {
        return open;
    }
    public void setOpen(boolean open)
    {
        this.open = open;
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
        if(this ==obj){//如果是引用同一个实例
            return true;            
        } if (obj!=null && obj instanceof ZTree) {
            //当id一样，就认为树结构一致
            ZTree temp = (ZTree) obj;   
            return this.id.equals(temp.id);   
        }else if(obj!=null && obj instanceof String){
            return this.id.equals((String)obj);
        }else{
            return false;
        }
    }
    
    public ZTree clone(){
        ZTree newzTreeDto = new ZTree(this.id,this.pId,this.name,this.codeType,this.open);
        return newzTreeDto;
    }
    
   
}
