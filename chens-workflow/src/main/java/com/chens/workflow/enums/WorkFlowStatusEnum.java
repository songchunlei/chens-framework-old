package com.chens.workflow.enums;

public enum WorkFlowStatusEnum{
	   /*流程状态 1处理中 2完成 3审核不通过 4，驳回修改，5 发起人删除，6 管理员关闭*/
		PROCESSING("PROCESSING","处理中"),
		COMPLETED("COMPLETED","完成"),
		AUDIT_NOT_APPROVED("AUDIT_NOT_APPROVED","审核不通过"),
	    REBUT("REBUT","驳回修改"),
	    AUTHOR_DELETE("AUTHOR_DELETE","发起人删除"),
	    ADMINISTRATOR_CLOSE("ADMINISTRATOR_CLOSE","管理员关闭");
		
	    private String display;
	    private String code;
	    
	    private WorkFlowStatusEnum(String code, String display) {
	        this.display = display;
	        this.code = code;
	    }

	    public String getDisplay() {
	        return display;
	    }

	    public void setDisplay(String display) {
	        this.display = display;
	    }

	    public String getCode() {
	        return code;
	    }
	/**
	 * @Description: 通过编码获取枚举值
	 */
    public static WorkFlowStatusEnum getYesNoEnumByCode(String code){           
        WorkFlowStatusEnum[] enums = WorkFlowStatusEnum.values();
        for(WorkFlowStatusEnum item: enums){          
            if(item.getCode().equalsIgnoreCase(code)){
                return item;
            }
        }      
        return null;
    }
}
