package com.mta.javacourse.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.Stock;

/**
 * An instance of this class represents a storage place in order to manage all stocks in one place
 * @author AnastasyaZiser
 * 4.12/2014
 */

@SuppressWarnings({ "serial", "unused" })
public class Portfolio extends HttpServlet {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title = "<h1> Portfolio title </h1>";
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public Portfolio() {
		stocks = new Stock [MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus [MAX_PORTFOLIO_SIZE];
	}
	
	/**
	 * @param stock - added to the array of stocks
	 * receives Stock and add it to portfolio’s stocks array.
	 */
	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	/**
	 * @return the stocks array
	 */
	public Stock[] getStocks() {
		return stocks;
	}
	
	/**
	 * @return returns string with portfolio title and all stocks html code
	 */
	public String getHtmlString() {
		String htmlCodeString = " ";
		for (int i = 0; i < portfolioSize; i++)
			htmlCodeString += stocks[i].getHtmlDescription() + "<br>";
		return title + htmlCodeString;
	}
	
	public class StockStatus {
		
		private final static int DO_NOTHING = 0, BUY = 1, SELL = 2;
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation, stockQuantity;
	}
	
}

