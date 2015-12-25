package org.matcha.server.web.po;

import java.util.ArrayList;
import java.util.List;

public class TargetData {
	private String target;
	private List<Data> dataList = new ArrayList<Data>();
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<Data> getDataList() {
		return dataList;
	}
	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}
	
}
