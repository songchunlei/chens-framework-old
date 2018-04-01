package com.chens.bpm.activiti.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.impl.util.io.BytesStreamSource;
import org.activiti.engine.impl.util.io.StreamSource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlActivitiUtil {

	/**
	 * 通过解析xml文件，判断该节点是否是会签节点
	 * 
	 * @param resourceAsStream
	 * @param taskkey
	 * @throws Exception
	 */
	public static boolean parseXml(String doc, String taskkey) {
		Element root = null;
		try {
			Document document = null;
			try {
				SAXReader reader = new SAXReader();
				//解决报错：Dom4j中文异常处理:Invalid byte 2 of 2-byte UTF-8 sequence
				reader.setEncoding("GBK");
				InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
				document = reader.read(inputStream);  
			} catch (Exception e) {
				SAXReader reader = new SAXReader();
				InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
				document = reader.read(inputStream);  
			}
			
			root = document.getRootElement();
			  for ( Iterator<?> iter = root.elementIterator(); iter.hasNext(); ) {
				  Element element = (Element) iter.next();
	              for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext(); ) {
		              Element elementInner = (Element) iterInner.next();
		              if(elementInner.getName().equals("userTask")){
			               //获取节点的id属性的值
			               Attribute leaderAttr=elementInner.attribute("id");
			               if(leaderAttr!=null){
			            	   if(leaderAttr.getValue().equals(taskkey)){
				            	   Element e_adds = elementInner.element("multiInstanceLoopCharacteristics");
				            	   if (e_adds != null ) {
				            		   return true;
				            	   }else{
				            		   return false;
				            	   }
			            	   }
					      }
		              }
	             }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     * 通过解析xml文件生成 开始节点属性
     * 
     * @param resourceAsStream
     * @throws Exception
     */
    public static Map<String,String> parseXml(String doc,String type,String field,String value) {
        Element root = null;
        Map<String,String> map = new HashMap<String,String>();
        try {
            Document document = null;
            try {
                SAXReader reader = new SAXReader();
                //解决报错：Dom4j中文异常处理:Invalid byte 2 of 2-byte UTF-8 sequence
                reader.setEncoding("UTF-8");
                InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
                document = reader.read(inputStream);  
            } catch (Exception e) {
                SAXReader reader = new SAXReader();
                InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
                document = reader.read(inputStream);  
            }
            
            root = document.getRootElement();
              for ( Iterator<?> iter = root.elementIterator(); iter.hasNext(); ) {
                  Element element = (Element) iter.next();
                  for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext(); ) {
                      Element elementInner = (Element) iterInner.next();
                      if(elementInner.getName().equals(type)  &&
                             value.equals(elementInner.attribute(field).getValue())          ){
                          for (Iterator<?> iter1 = elementInner.attributeIterator();iter1.hasNext(); ) {  
                              Attribute attribute = (Attribute) iter1.next();
                              map.put(attribute.getName(), attribute.getValue());
                          }  
                      }
                 }
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    
    /**
     * 通过解析xml文件，判断该节点是否是会签节点
     * 
     * @param resourceAsStream
     * @param taskkey
     * @throws Exception
     */
    public static Map<String,String> parseStartXml(String doc) {
        Element root = null;
        Map<String,String> map = new HashMap<String,String>();
        try {
            Document document = null;
            try {
                SAXReader reader = new SAXReader();
                //解决报错：Dom4j中文异常处理:Invalid byte 2 of 2-byte UTF-8 sequence
                reader.setEncoding("GBK");
                InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
                document = reader.read(inputStream);  
            } catch (Exception e) {
                SAXReader reader = new SAXReader();
                InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
                document = reader.read(inputStream);  
            }
            
            root = document.getRootElement();
              for ( Iterator<?> iter = root.elementIterator(); iter.hasNext(); ) {
                  Element element = (Element) iter.next();
                  for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext(); ) {
                      Element elementInner = (Element) iterInner.next();
                      if(elementInner.getName().equals("startEvent")){
                          for (Iterator<?> iter1 = elementInner.attributeIterator();iter1.hasNext(); ) {  
                              Attribute attribute = (Attribute) iter1.next();
                              map.put(attribute.getName(), attribute.getValue());
                          }  
                      }
                 }
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
	/**
	 * 解析流程配置文件获取流程元素节点
	 * 
	 * @param processXml
	 *            流程二进制内容，可通过process.getProcessxml()获取
	 * @return
	 */
	public static Collection<FlowElement> getFlowElementsByBpmXML(byte[] processXml) {
		BpmnXMLConverter converter = new BpmnXMLConverter();
		StreamSource streamSource = new BytesStreamSource(processXml);
		BpmnModel bpmnModel = converter.convertToBpmnModel(streamSource, false, false);
		org.activiti.bpmn.model.Process processModel = bpmnModel.getProcesses().get(0);
		return processModel.getFlowElements();
	}
	
	/**
	 * 
	* @Title: getTargetRefByFlowId 
	* @Description: (这里用一句话描述这个方法的作用) 
	* @return String    返回类型 
	* @throws 
	* @author wudp
	 */
	public static String getTargetRefByFlowId(String xmlString, String flowId){
        Element root = null;
        String targetRef = "";
        try {
            Document document = null;
            try {
                SAXReader reader = new SAXReader();
                //解决报错：Dom4j中文异常处理:Invalid byte 2 of 2-byte UTF-8 sequence
                reader.setEncoding("GBK");
                InputStream   inputStream   =  new  ByteArrayInputStream(xmlString.toString().getBytes());
                document = reader.read(inputStream);  
            } catch (Exception e) {
                SAXReader reader = new SAXReader();
                InputStream   inputStream   =  new  ByteArrayInputStream(xmlString.toString().getBytes());
                document = reader.read(inputStream);  
            }
            
            root = document.getRootElement();
              for ( Iterator<?> iter = root.elementIterator(); iter.hasNext(); ) {
                  Element element = (Element) iter.next();
                  for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext(); ) {
                      Element elementInner = (Element) iterInner.next();
                      if(elementInner.getName().equals("sequenceFlow") && elementInner.attribute("id").getValue().equals(flowId)){
                    	  targetRef = elementInner.attribute("targetRef").getValue();
                      }
                 }
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetRef;
    };
    
    /**
     * 通过解析xml来获取会签节点的通过条件
     * @param doc
     * @param taskkey
     * @return
     */
    public static String getCompletionCondition(String doc, String taskkey) {
		Element root = null;
		String conditionString = "";
		try {
			Document document = null;
			try {
				SAXReader reader = new SAXReader();
				//解决报错：Dom4j中文异常处理:Invalid byte 2 of 2-byte UTF-8 sequence
				reader.setEncoding("GBK");
				InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
				document = reader.read(inputStream);  
			} catch (Exception e) {
				SAXReader reader = new SAXReader();
				InputStream   inputStream   =  new  ByteArrayInputStream(doc.toString().getBytes());
				document = reader.read(inputStream);  
			}
			
			root = document.getRootElement();
			  for ( Iterator<?> iter = root.elementIterator(); iter.hasNext(); ) {
				  Element element = (Element) iter.next();
	              for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext(); ) {
		              Element elementInner = (Element) iterInner.next();
		              if(elementInner.getName().equals("userTask")){
			               //获取节点的id属性的值
			               Attribute leaderAttr=elementInner.attribute("id");
			               if(leaderAttr!=null){
			            	   if(leaderAttr.getValue().equals(taskkey)){
				            	   Element e_adds = elementInner.element("multiInstanceLoopCharacteristics");
				            	   if(e_adds != null ){
				            		   Element conditionE = e_adds.element("completionCondition");
				            		   if(conditionE != null){
				            			   conditionString = conditionE.getText();
				            			   break;
				            		   }
				            		   
				            	   }
			            	   }
					      }
		              }
	             }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conditionString;
	}
    
    
 /*   public static List<ActTaskDto> getUserTaskListByParseXml(String xmlDoc) {
    	List<ActTaskDto> userTaskList = new ArrayList<ActTaskDto>();
		Element root = null;
		try {
			Document document = null;
			try {
				SAXReader reader = new SAXReader();
				//解决报错：Dom4j中文异常处理:Invalid byte 2 of 2-byte UTF-8 sequence
				reader.setEncoding("GBK");
				InputStream   inputStream   =  new  ByteArrayInputStream(xmlDoc.toString().getBytes());
				document = reader.read(inputStream);  
			} catch (Exception e) {
				SAXReader reader = new SAXReader();
				InputStream   inputStream   =  new  ByteArrayInputStream(xmlDoc.toString().getBytes());
				document = reader.read(inputStream);  
			}
			
			root = document.getRootElement();
			for(Iterator<?> iter = root.elementIterator(); iter.hasNext();){
				 Element element = (Element) iter.next();
	             for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext(); ) {
		              Element elementInner = (Element) iterInner.next();
		              if(elementInner.getName().equals("userTask")){
		            	  ActTaskDto actTaskDto = new ActTaskDto();
			              Attribute userTaskKeyAttr = elementInner.attribute("id");
			              if(userTaskKeyAttr != null){
			            	  actTaskDto.setTaskKey(userTaskKeyAttr.getValue());	 
			              }			              		               
			              Attribute userTaskKeyNameAttr = elementInner.attribute("name");
			              if(userTaskKeyNameAttr != null){
			            	  actTaskDto.setTaskName(userTaskKeyNameAttr.getValue());
			              }	             
			              userTaskList.add(actTaskDto);		               
		              }
	             }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userTaskList;
	}*/
    
    
}
