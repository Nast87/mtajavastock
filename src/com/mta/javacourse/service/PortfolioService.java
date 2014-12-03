package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import com.mta.javacourse.Stock;
import com.mta.javacourse.model.Portfolio;

@SuppressWarnings("serial")
public class PortfolioService extends HttpServlet {
	
	private Portfolio myPortfolio = new Portfolio();
	
	public Portfolio getMyPortfolio() {
		return myPortfolio;
	}


	public void setMyPortfolio(Portfolio myPortfolio) {
		this.myPortfolio = myPortfolio;
	}


	public Portfolio getPortfolio() {
		
		Calendar c = Calendar.getInstance();
		c.set(2014, 10, 15, 00, 00);
		Date myDate = c.getTime();
		
		Stock stock1;
		stock1 = new Stock();
		stock1.setSymbol("PIH");
		stock1.setAsk((float) 12.4);
		stock1.setBid((float) 13.1);
		stock1.setDate(myDate);
		
		myPortfolio.addStock(stock1);

		Stock stock2;
		stock2 = new Stock();
		stock2.setSymbol("AAL");
		stock2.setAsk((float) 5.5);
		stock2.setBid((float) 5.78);
		stock2.setDate(myDate);
		
		myPortfolio.addStock(stock2);
			
		Stock stock3;
		stock3 = new Stock();
		stock3.setSymbol("CAAS");
		stock3.setAsk((float) 31.5);
		stock3.setBid((float) 31.2);
		stock3.setDate(myDate);
		
		myPortfolio.addStock(stock3);
		
		return myPortfolio;
	
	} 
}
