package com.tma.pi.preview.service;

import java.util.Scanner;

import com.tma.pi.preview.execution.FactoryManager;

public class CalcStopService extends Thread{
	
	private FactoryManager factoryManager;
	
	public CalcStopService(FactoryManager factoryManager){
		this.factoryManager = factoryManager;
		setDaemon(true);
	}
	
	@Override
	public void run(){
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				scan.nextLine();
				scan.close();
				factoryManager.stop();
			} catch (Exception e) {

			}
		}
	}
}
