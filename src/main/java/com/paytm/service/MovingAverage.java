package com.paytm.service;

import java.util.Queue;

import com.paytm.exceptions.InvalidInput;
/**
 * Interface to declare moving average service methods
 * @author goels10
 *
 */
public interface MovingAverage {

	float getMovingAverage();
	
	void add(int inputNumber) throws InvalidInput;
	
	Queue<Integer> getElements();
	
}
