package com.mta.javacourse.service;

import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Portfolio.StockStatus;
import com.mta.javacourse.model.Stock;

/**
 * An instance of this class activate actions defined in Portfolio Class.
 * @author AnastasyaZiser
 * 20/12/2014
 */

public class PortfolioService {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	Portfolio myPortfolio;
	
	public PortfolioService() {
		myPortfolio = new Portfolio(new Stock[ MAX_PORTFOLIO_SIZE], new StockStatus[ MAX_PORTFOLIO_SIZE], 0, " ");
	}
	
	/**
	* This method initializes all fields of the stocks, adds them to the new portfolio and returns the new portfolio. 
	*/
	public Portfolio getPortfolio(){

		Stock stock1, stock2, stock3;
		Date date = new Date();
		
		stock1 = new Stock("PIH",(float)12.4,(float)13.1,date);
		myPortfolio.addStock(stock1);
		
		stock2 = new Stock("AAL",(float)5.5,(float)5.78,date);
		myPortfolio.addStock(stock2);
		
		stock3 = new Stock("CAAS",(float)31.5,(float)31.2,date);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("Potfolio #1");

		return myPortfolio;
	}
}
	
	