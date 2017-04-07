package org.rachel.graph.informationflow;

import java.util.Date;

public class InfoflowNode {
    String id;
    String name;    
    Date createDate;
    int numberOfEdges;    
    String createBy;
    
    public InfoflowNode(String id,String name,Date createdDate,int numberOfEdges,String createdBy){
    	this.id= id;
    	this.name=name;
    	this.createDate=createdDate;
    	this.numberOfEdges=numberOfEdges;
    	this.createBy =createdBy;
    		
    	
    }
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	public void setNumberOfEdges(int numberOfEdges) {
		this.numberOfEdges = numberOfEdges;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
    
    

}
