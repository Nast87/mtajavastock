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
	
	Portfolio myPortfolio;
	
	public PortfolioService() {
		myPortfolio = new Portfolio(new Stock[Portfolio.getMaxPortfolioSize()], new StockStatus[Portfolio.getMaxPortfolioSize()], 0, " ");
	}
	
	/**
	* This method initializes all fields of the stocks, adds them to the new portfolio and returns the new portfolio. 
	*/
	public Portfolio getPortfolio(){
		
		Stock stock1, stock2, stock3;
		@SuppressWarnings("deprecation")
		Date date = new Date("12/15/2014");
		
		stock1 = new Stock("PIH",(float)10,(float)8.5,date);
		myPortfolio.addStock(stock1);
		
		stock2 = new Stock("AAL",(float)30,(float)25.5,date);
		myPortfolio.addStock(stock2);
		
		stock3 = new Stock("CAAS",(float)20,(float)15.5,date);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("Exercise 07 Portfolio");
		myPortfolio.setBalance(10000);
		
		//buying stocks
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
						
		//selling stocks
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
	
	