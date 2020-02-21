package com.muyou.common.pojo;

import java.io.Serializable;

public class SolrInfo implements Serializable {

	private String status;
	private String clusterName;
	private int nodesNum;
	private int docNum;
	private String docStr;

	public String getDocStr() {
		return docStr;
	}

	public void setDocStr(String docStr) {
		this.docStr = docStr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public int getNodesNum() {
		return nodesNum;
	}

	public void setNodesNum(int nodesNum) {
		this.nodesNum = nodesNum;
	}

	public int getDocNum() {
		return docNum;
	}

	public void setDocNum(int docNum) {
		this.docNum = docNum;
	}

}
