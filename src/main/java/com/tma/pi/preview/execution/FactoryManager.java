package com.tma.pi.preview.execution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tma.pi.preview.bean.NumberBean;
import com.tma.pi.preview.bean.NumberBeanService;
import com.tma.pi.preview.formula.IFormulaStrategy;
import com.tma.pi.preview.io.ConsoleManager;
import com.tma.pi.preview.io.IConsoleIO;
import com.tma.pi.preview.service.CalcStopService;
import com.tma.pi.preview.service.ThreadService;

public class FactoryManager implements IFactoryManager {
	
	private int nrThreads = Runtime.getRuntime().availableProcessors();
	
	ArrayList<Future<Double>> helperList = new ArrayList<Future<Double>>();
	
	ExecutorService eService = Executors.newFixedThreadPool(nrThreads);
	
	private IFormulaStrategy formulaStrategy;
	
	private long No;
	
	private boolean counting = false;
	
	private List<NumberBean> numbers;
	
	public FactoryManager(long inputNo, IFormulaStrategy formulaStrategy){
		this.formulaStrategy = formulaStrategy;
		numbers = NumberBeanService.getListNumbers(0, inputNo, inputNo/1000);
	}
	
	public void countPiNumber() throws InterruptedException, ExecutionException{
		
		Future<Double> countHelper = null;
		try{
			for(ThreadService service : getCalcTasks()){
				countHelper = eService.submit(service);
				helperList.add(countHelper);
			}
		}catch(Exception e){
			System.out.print("There is no more calcTasks to do.");
			e.getMessage();
		}	
	}
	
	public void stop(){
		this.counting = true;
	}
	
	public List<ThreadService> getCalcTasks(){
		List<ThreadService> threadServices = new ArrayList<ThreadService>();
		
		for(NumberBean n : numbers){
			try{
				threadServices.add(new ThreadService(n.getStartNo(), n.getEndNo(), formulaStrategy));	
			}catch(NullPointerException e){
				System.out.println("there is no task to created.");
			}			
		}
		
		return threadServices;
	}
	
	/*public long getTaskNumber(long n){
		long noTask = 0;
		
		if(nrThreads != 0){
			if(No % RANGE_PI_NO == 0){
				noTask = No / RANGE_PI_NO;
			}else {
				noTask = (No / RANGE_PI_NO) + 1;
			}
		}
		
		return noTask;
	}*/
	
	public void getSumPiResult() throws InterruptedException, ExecutionException{
		double piNumbers = 0;
		
		try{
			for(Future<Double> helper : helperList){				
				piNumbers += helper.get();
				if(counting){
					eService.shutdownNow();
					break;
				}
			}	
		}catch(ExecutionException e){
			e.getMessage();
		}
		
		ConsoleManager.getConsoleInstance().displayResult(piNumbers);
		
		eService.shutdown();
	}
	
	
	/*
	 * CalculationStop class it's extends a thread and waiting
	 * when user press any key to stop calculate process
	 */
	
	public void calcProcess() throws InterruptedException, ExecutionException {
		
		Thread thread = new CalcStopService(this);
		thread.start();
		calcProcess();
		getSumPiResult();
	}
}
