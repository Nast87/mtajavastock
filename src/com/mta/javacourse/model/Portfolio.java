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
	private enum ALGO_RECOMMENDATION {DO_NOTHING,BUY,SELL};
	private float balance;
	

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	 * @return the stocks array
	 */
	public Stock[] getStocks() {
		return stocks;
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
	 * @param newStock - added to the array of stocks
	 * receives Stock and add it to portfolio’s stocks array.
	 * @param newStockStatus - receives the information about the stock recently added
	 */
	public void addStock(Stock newStock) {
		boolean flag = false;
		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.println("Can't add new stock, portfolio can have only" +MAX_PORTFOLIO_SIZE+ " stocks");
			flag = true;
		}
		for (int i = 0; i < portfolioSize && (!flag); i++) {
			if (stocks[i].getSymbol() == newStock.getSymbol()) {
				System.out.println("This stock already exists at the array. You can buy stock instead");
				flag = true;
			}
		}
		if(!flag) {
			stocks[portfolioSize] = newStock;
			stocksStatus[portfolioSize] = new StockStatus (newStock.getSymbol(), newStock.getAsk(), newStock.getBid(), new Date(newStock.getDate().getTime()), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			portfolioSize++;
			System.out.println("The stock" +newStock.getSymbol()+ "was succesfully added to the array");
		}
	}
	
	/**
	 * @return returns string with portfolio title and all stocks html code
	 */
	public String getHtmlString() {
		String htmlTitle = "<b><h1>"+ this.getTitle()+":</h1></b><br>";
		String htmlCodeString = " ";
		for (int i = 0; i < portfolioSize; i++) {
			htmlCodeString += stocks[i].getHtmlDescription() + stocksStatus[i].getStockQuantity() + "<br>";
		}
		htmlCodeString += ("<br><b>Portfolio Value: "+ this.getTotalValue()+ " $ <br> Total stock's value: "+this.getStocksValue()+" $<br>The Balance is: "+this.getBalance()+ " $");
		return htmlTitle + htmlCodeString;
	}
	
	/**
	 * This methods updates current balance at the portfolio
	 * @param amount - the sum that added to the balance
	 */
	public void updateBalance (float amount) {
		balance += amount;
	}
	
	/**
	 * This method gets the stock's symbol which you wish to remove. First of all it sells the stock and then removes it from portfolio 
	 * @param symbol - a symbol of stock you wish to sell and remove
	 * @return the answer if the stock was removed either not.
	 */
	public boolean removeStock(String symbol) {
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol == stocks[i].getSymbol()) {
				sellStock(symbol, -1);
				stocks[i] = stocks[portfolioSize - 1];
				stocksStatus[i] = stocksStatus[portfolioSize-1];
				portfolioSize--;
				System.out.println("The stock " +symbol+ " was succesfully removed");
				return true;	
			}
		}
		System.out.println("The stock " +symbol+ " you ask to remove doesn't exist in your portfolio");
		return false;
	}
	
	/**
	 * This methods sells stocks and updates stock's quantity and a current balance
	 * @param symbol - a symbol of the stock you want to sell
	 * @param quantity - a quantity of stocks you want to sell
	 * @return an answer if it is possible to sell the stocks if exists in portfolio
	 */
	public boolean sellStock (String symbol, int quantity) {
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol == stocks[i].getSymbol()) {
				if(quantity == -1) {	
					updateBalance (stocks[i].getBid() * stocksStatus[i].getStockQuantity());
					stocksStatus[i].setStockQuantity(0);
					System.out.println("All " +symbol+ " stocks were sold");
				}
				else {
					if(stocksStatus[i].getStockQuantity() - quantity > 0) {
						stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity); 
						updateBalance (stocks[i].getBid() * quantity);
						System.out.println(quantity+ " of " +symbol+ " stocks were succesfully sold");
						}
					else {
						System.out.println("The quantity you want to sell is higher than amount of stocks you own");
						return false;
					}
				}
				return true;
				}
			}
		
		System.out.println("The stock " +symbol+ " you want to sell doesn't exist in your portfolio");
		return false;		
		}
	
	/**
	 * This method buys stocks and updates stock's quantity and a current balance
	 * @param symbol - a symbol of the stock you want to sell
	 * @param quantity - a quantity of stocks you want to sell
	 * @return an answer if it is possible to buy the stocks with the the balance exists in the portfolio
	 */
	public boolean buyStock (String symbol, int quantity) {
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol == stocks[i].getSymbol()) {
				if(quantity == -1) {
					int numOfStocks = (int)(balance / stocksStatus[i].getCurrentAsk());
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + numOfStocks);
					updateBalance (-(numOfStocks * stocksStatus[i].getCurrentAsk()));
					System.out.println(numOfStocks+ " of" +symbol+ " stocks were purchased" );
				}
				else { 
					if((stocksStatus[i].currentAsk * quantity) > balance) {
						System.out.println("Not enough balance to complete purchase");
						return false;
					}
					else {
						stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + quantity); 
						updateBalance (-(quantity) * stocksStatus[i].getCurrentAsk());
						System.out.println(quantity+ " of " +symbol+ " stocks were succesfully purchased");
					}
				}
			return true;
			}
		}
		return false;
	}
	
	/**
	 * This method represents the value of all of the stocks that exist in a portfolio
	 * @return the value of the portfolio
	 */
	public float getStocksValue () {
		float value = 0;
		for (int i = 0; i < portfolioSize; i++)
			value += (stocksStatus[i].getCurrentBid() * stocksStatus[i].getStockQuantity());
		return value;
	}
	
	/**
	 * This method represents the total value of the portfolio including stock's value and the current balance
	 * @return the total value of the portfolio
	 */
	public float getTotalValue () {
		return (getStocksValue() + getBalance());
	}
	
	/**
	 * The instance of this class represents updated information about the stocks which exist in a portfolio
	 * @author Anastasya Ziser
	 * 16/12/2014
	 */
		
	public class StockStatus {
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int stockQuantity;
		private ALGO_RECOMMENDATION recommendation;
				
		/**
		* This method creates new StockStatus
		*/
		public StockStatus(String string, float cAsk, float cBid, Date date1, ALGO_RECOMMENDATION rec, int stockQ){
			symbol = string;
			currentBid = cBid;
			currentAsk = cAsk;
			date = date1;
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
				this.currentBid = stockStatus.currentBid;
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
		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}	
}

