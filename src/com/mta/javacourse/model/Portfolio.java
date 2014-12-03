package com.mta.javacourse.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.Stock;

@SuppressWarnings({ "serial", "unused" })
public class Portfolio extends HttpServlet {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
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
	
	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public String getHtmlString() {
		String stockTitle = "<h1> Portfolio title </h1>";
		String htmlCodeString = " ";
		for (int i = 0; i < portfolioSize; i++)
			htmlCodeString += stocks[i].getHtmlDescription() + "<br>";
		return stockTitle + htmlCodeString;
	}
	
	public class StockStatus {
		
		private final static int DO_NOTHING = 0, BUY = 1, SELL = 2;
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation, stockQuantity;
	}
	
}

