package com.tma.pi.preview.execution;

import java.util.concurrent.ExecutionException;

public interface IFactoryManager {

	public void countPiNumber() throws InterruptedException, ExecutionException;
	
	public void getSumPiResult() throws InterruptedException, ExecutionException;
	
	public void calcProcess() throws InterruptedException, ExecutionException;
	
	public void stop();
}
