package com.paytm.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.paytm.exceptions.InvalidInput;

@Component
public class MovingAverageService implements MovingAverage {

	/**
	 * Data structure to maintain an in-memory store of last N elements where N is
	 * the window size declared by the caller
	 */
	private Queue<Integer> elements = new LinkedList<>();
	
	/**
	 * Variable to store the sum of last N elements
	 */
	private int runningSum = 0;
	
	/**
	 * Variable that defines N elements. This variable should be declared prior to
	 * adding the first element
	 */
	private int windowSize;

	/**
	 * Method to return the moving average of last N elements
	 * @return float moving average 
	 */
	@Override
	public float getMovingAverage() {
		return (float)runningSum  / windowSize;
	}

	/**
	 * Method to add elements to the in-memory data structure. Window size should be
	 * set by the caller prior to adding elements
	 * 
	 * @param inputNumber the element to be added
	 * @throws InvalidInput exception throws if the window size is invalid
	 */
	@Override
	public void add(int inputNumber) throws InvalidInput {
		
		if(windowSize <= 0) {
			throw new InvalidInput("Invalid window size.");
		}
		
		if (elements.size() >= windowSize) {
			int numberToRemove = elements.poll();
			runningSum -= numberToRemove;
		}
		elements.add(inputNumber);
		runningSum += inputNumber;
	}

	/**
	 * Method to get access to the elements
	 */
	@Override
	public Queue<Integer> getElements() {
		return elements;
	}

	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}

}
