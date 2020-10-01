package com.paytm.service;

import static org.junit.Assert.assertEquals;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.paytm.exceptions.InvalidInput;


public class MovingAverageServiceTest {

	private MovingAverageService movingAverageService = new MovingAverageService();
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test to validate the proper exception is thrown when caller does not initialize the window size prior to adding elements
	 * @throws Exception
	 */
	@Test
	public void test_invalid_window_size() throws Exception {
		thrown.expect(InvalidInput.class);
		thrown.expectMessage("Invalid window size.");
		movingAverageService.add(10);	
	}
	
	/**
	 * Test to validate the moving average when the number of added elements is less than the window size. 
	 * @throws Exception
	 */
	@Test
	public void test_addedElementsLessThanWindowSize() throws Exception {
		movingAverageService.setWindowSize(5);
		movingAverageService.add(1);
		movingAverageService.add(2);
		movingAverageService.add(3);

		assertEquals(1.2f, movingAverageService.getMovingAverage(), 0.0f);
		
		Queue<Integer> mockData = new LinkedList<>();
		mockData.add(1);
		mockData.add(2);
		mockData.add(3);
		
		//Validate the added elements
		assertEquals(mockData, movingAverageService.getElements());
	}
	
	/**
	 * Test to validate the moving average when the number of added elements is more than the window size. 
	 * @throws Exception
	 */
	@Test
	public void test_addedElementsMoreThanWindowSize() throws Exception {
		movingAverageService.setWindowSize(5);
		movingAverageService.add(1);
		movingAverageService.add(2);
		movingAverageService.add(3);
		movingAverageService.add(5);
		movingAverageService.add(5);
		movingAverageService.add(10);
		movingAverageService.add(15);	
		
		assertEquals(7.6f, movingAverageService.getMovingAverage(), 0.0f);
		
		Queue<Integer> mockData = new LinkedList<>();
		mockData.add(3);
		mockData.add(5);
		mockData.add(5);
		mockData.add(10);
		mockData.add(15);
		//validate that accessed elements
		assertEquals(mockData, movingAverageService.getElements());
	}
	
	/**
	 * Test to validate the moving average when the number of added elements is equal to the window size. 
	 * @throws Exception
	 */
	@Test
	public void test_addedElementsEqualToWindowSize() throws Exception {
		movingAverageService.setWindowSize(5);

		movingAverageService.add(5);
		movingAverageService.add(5);
		movingAverageService.add(5);
		movingAverageService.add(10);
		movingAverageService.add(15);	
		
		assertEquals(8f, movingAverageService.getMovingAverage(), 0.0f);
		
		Queue<Integer> mockData = new LinkedList<>();
		mockData.add(5);
		mockData.add(5);
		mockData.add(5);
		mockData.add(10);
		mockData.add(15);
		
		//validate that accessed elements
		assertEquals(mockData, movingAverageService.getElements());
	}


}
