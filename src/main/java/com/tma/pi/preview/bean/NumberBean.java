package com.tma.pi.preview.bean;

public class NumberBean {

	private long startNo;
	
	private long endNo;
	
	public NumberBean(){}

	public NumberBean(long i, long endofList) {
		this.startNo = i;
		this.endNo = endofList;
	}

	public long getStartNo() {
		return startNo;
	}

	public void setStartNo(long startNo) {
		this.startNo = startNo;
	}

	public long getEndNo() {
		return endNo;
	}

	public void setEndNo(long endNo) {
		this.endNo = endNo;
	}
	
	
}
