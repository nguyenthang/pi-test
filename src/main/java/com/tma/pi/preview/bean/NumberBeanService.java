package com.tma.pi.preview.bean;

import java.util.ArrayList;
import java.util.List;

public class NumberBeanService {
	
	public static List<NumberBean> getListNumbers(long startNo, long endNo,
			long listSize) {
		List<NumberBean> numberBeans = new ArrayList<NumberBean>();
		for (long i = startNo; i <= endNo; i += (listSize + 1)) {
			if ((listSize + i) <= endNo) {
				long endofList = listSize + i;
				numberBeans.add(new NumberBean(i, endofList));
			} else {
				numberBeans.add(new NumberBean(i, endNo));
				break;
			}
		}
		return numberBeans;
	}
}
