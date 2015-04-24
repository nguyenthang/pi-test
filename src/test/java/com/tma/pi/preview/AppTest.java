package com.tma.pi.preview;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tma.pi.preview.bean.NumberBean;


/**
 * @author thangnguyen
 *
 */

public class AppTest 
   
{
	List<NumberBean> listNumbers;
	
	@Before
	public void setUp() throws Exception{
		listNumbers = new ArrayList<NumberBean>();
	}
	
	@After
	public void tearDown() throws Exception{
		listNumbers = null;
	}
	
	@Test
	public void testBlockNumber(){
		
	}
}
