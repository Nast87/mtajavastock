package com.mta.javacourse.model;

import java.util.Date;

/**
 * An instance of this class represents a storage place in order to manage all stocks in one place
 * @author AnastasyaZiser
 * 10/12/2014
 */

public class Portfolio {
	
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
	
	/**
	* This constructor creates new portfolio
	*/

	public Portfolio(Stock newStocks[], StockStatus newStocksStatus[], int newPortfolioSize, String newTitle) {
		stocks = newStocks;
		stocksStatus = newStocksStatus;
		portfolioSize = newPortfolioSize;
		title = newTitle;
	}
	
	/**
	* This constructor copies the data from the portfolio and creates new portfolio with the same data
	*/
	
	public Portfolio (Portfolio portfolio) {
		this(new Stock[MAX_PORTFOLIO_SIZE], new StockStatus[MAX_PORTFOLIO_SIZE], 0, " ");
		
		for (int i = 0; i < portfolio.portfolioSize; i++) {
			stocks[i] = new Stock(portfolio.stocks[i]);
			stocksStatus[i] = new StockStatus(portfolio.stocksStatus[i]);	
		}
		
		this.setTitle(portfolio.getTitle());
		this.portfolioSize = portfolio.portfolioSize;
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
	* This method gets the stock's index which you need to remove and removes it. 
	*/
	public void removeStock(int stocksIndex){
		if(stocksIndex == portfolioSize){
			this.portfolioSize--;
			}
		
		else{
			this.portfolioSize--;
			
			for(int i = stocksIndex; i<= portfolioSize-1; i++){
				this.stocks[i] = this.stocks[i+1];
			}	
		}
	}

	
	/**
	 * @return returns string with portfolio title and all stocks html code
	 */
	public String getHtmlString() {
		String htmlTitle = "<b><h1>"+ this.getTitle()+":</h1></b><br>";
		String htmlCodeString = " ";
		for (int i = 0; i < portfolioSize; i++)
			htmlCodeString += stocks[i].getHtmlDescription() + "<br>";
		return htmlTitle + htmlCodeString;
	}
	
	public class StockStatus {
		
		private final static int DO_NOTHING = 0, BUY = 1, SELL = 2;
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation, stockQuantity;
		
		/**
		* This method creates new StockStatus
		*/
		public StockStatus(String string, float cBid, float cAsk, Date date1, int rec, int stockQ){
			symbol = string;
			currentBid = cBid;
			currentAsk = cAsk;
			date = date1;
			recommendation = rec;
			stockQuantity = stockQ;
		}
		
		/**
		* This method copies the data from StockStatus and creates new StockStatus with the same data 
		*/	
		public StockStatus(StockStatus stockStatus){
			if(this.symbol != null)
		{
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentAsk = stockStatus.currentBid;
				this.date = stockStatus.date;
				this.recommendation = stockStatus.recommendation;
				this.stockQuantity = stockStatus.stockQuantity;
			}
		}
		
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getCurrentBid() {
			return currentBid;
		}
		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}
		public float getCurrentAsk() {
			return currentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuntity() {
			return stockQuantity;
		}
		public void setStockQuntity(int stockQuntity) {
			this.stockQuantity = stockQuntity;
		}
	}	
}

