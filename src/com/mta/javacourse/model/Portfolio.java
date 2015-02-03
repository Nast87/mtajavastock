package com.mta.javacourse.model;

import java.util.List;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.IllegalQuantityException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;


/**
 * An instance of this class represents a storage place in order to manage all stocks in one place
 * @author AnastasyaZiser
 * 10/12/2014
 */

public class Portfolio {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	public enum ALGO_RECOMMENDATION {DO_NOTHING,BUY,SELL};
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
	
	public StockStatus[] getStocks(){
		return this.stocksStatus;
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
	
	/**
	* This constructor creates new portfolio
	*/
	public Portfolio(){
		this.title = " ";
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		this.balance = 0;
		this.portfolioSize = 0;
	}
	
	public Portfolio(List<StockStatus> stockStatuses) {
		this();
		for (int i=0; i<stockStatuses.size(); i++){
			this.stocksStatus[i] = stockStatuses.get(i);
		}
	}
	
	/**
	* This constructor copies the data from the portfolio and creates new portfolio with the same data
	*/
	public Portfolio(Portfolio p){
		this.title = p.title;
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		for(int i=0;i<MAX_PORTFOLIO_SIZE;i++){
			if (p.stocksStatus[i] != null){
				this.stocksStatus[i] = new StockStatus(p.stocksStatus[i]);	
			}
		}	
		this.balance = p.balance;
		this.portfolioSize = p.portfolioSize;
	}

	
	/**
	 * @param newStock - added to the array of stocks
	 * receives Stock and add it to portfolio’s stocks array.
	 * @param newStockStatus - receives the information about the stock recently added
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	 */
	public void addStock(Stock newStock) throws PortfolioFullException, StockAlreadyExistsException {
		
		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.println("Can't add new stock, portfolio can have only" +MAX_PORTFOLIO_SIZE+ " stocks");
			throw new PortfolioFullException();
		}
		
		for (int i = 0; i < portfolioSize; i++) {
			if (stocksStatus[i].getSymbol().equalsIgnoreCase(newStock.getSymbol())) {
				System.out.println("This stock already exists at the array");
				throw new StockAlreadyExistsException(newStock.symbol);
			}
		} 
		
		stocksStatus[portfolioSize] = new StockStatus (newStock);
		portfolioSize++;
		System.out.println("The stock " +newStock.getSymbol()+ " was succesfully added to the array");
	}
	
	/**
	 * This method updates current balance at the portfolio
	 * @param amount - the sum that added to the balance
	 */
	public void updateBalance(float amount) throws BalanceException{
		if (this.balance + amount >= 0){
			this.balance += amount;
		}
		else{
			throw new BalanceException();
		}
	}
	
	
	/**
	 * This method checks if the stock exists in portfolio
	 * @param symbol
	 * @return an answer if the stocks exists (true) or not (false)
	 */
	public boolean CheckIfStockExists (String symbol) {
		int counter = 0;
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equalsIgnoreCase(stocksStatus[i].getSymbol())) {
				counter ++;
			}
		}
		if (counter != 0)
			return true;
		else
			return false;
	}
	
	/**
	 * This method gets the stock's symbol which you wish to remove. First of all it sells the stock and then removes it from portfolio 
	 * @param symbol - a symbol of stock you wish to sell and remove
	 * @return the answer if the stock was removed either not.
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 * @throws QuantityException 
	 */
	public void removeStock(String symbol) throws StockNotExistException, IllegalQuantityException, BalanceException {
		boolean exists = CheckIfStockExists (symbol);
		
		if (exists == false) {
			System.out.println("The stock " +symbol+ " you want to remove doesn't exist in your portfolio");
			throw new StockNotExistException (symbol);
		}
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equalsIgnoreCase(stocksStatus[i].getSymbol())) {
				sellStock(symbol, -1);
				stocksStatus[i] = stocksStatus[portfolioSize - 1];
				stocksStatus[i] = stocksStatus[portfolioSize-1];
				portfolioSize--;
				System.out.println("The stock " +symbol+ " was succesfully removed");	
			}
		}
	}
	
	/**
	 * This method sells stocks and updates stock's quantity and a current balance
	 * @param symbol - a symbol of the stock you want to sell
	 * @param quantity - a quantity of stocks you want to sell
	 * @return an answer if it is possible to sell the stocks if exists in portfolio
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 * @throws QuantityException 
	 */
	public void sellStock (String symbol, int quantity) throws StockNotExistException, IllegalQuantityException, BalanceException {
		boolean exists = CheckIfStockExists (symbol);
		
		if (exists == false) {
			System.out.println("The stock " +symbol+ " you want to sell doesn't exist in your portfolio");
			throw new StockNotExistException (symbol);
		}
		
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equalsIgnoreCase(stocksStatus[i].getSymbol())) {
				if(quantity == -1) {	
					updateBalance (stocksStatus[i].getBid() * stocksStatus[i].getStockQuantity());
					stocksStatus[i].setStockQuantity(0);
					System.out.println("All " +symbol+ " stocks were sold");
				}
				else {
					if(stocksStatus[i].getStockQuantity() - quantity > 0) {
						stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity); 
						updateBalance (stocksStatus[i].getBid() * quantity);
						System.out.println(quantity+ " of " +symbol+ " stocks were succesfully sold");
						}
					else {
						System.out.println("The quantity you want to sell is higher than amount of stocks you own");
						throw new IllegalQuantityException (symbol);
					}
				}
			}
		}
	}
	
	
	/**
	 * This method buys stocks and updates stock's quantity and a current balance
	 * @param symbol - a symbol of the stock you want to sell
	 * @param quantity - a quantity of stocks you want to sell
	 * @return an answer if it is possible to buy the stocks with the the balance exists in the portfolio
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 */
	public void buyStock (String symbol, int quantity) throws BalanceException, StockNotExistException {
		boolean exists = CheckIfStockExists (symbol);
		
		if (exists == false) {
			System.out.println("The stock " +symbol+ " you want to buy doesn't exist in your portfolio");
			throw new StockNotExistException (symbol);
		}
		
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equalsIgnoreCase(stocksStatus[i].getSymbol())) {
				if(quantity == -1) {
					int numOfStocks = (int)(balance / stocksStatus[i].getAsk());
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + numOfStocks);
					updateBalance (-(numOfStocks * stocksStatus[i].getAsk()));
					System.out.println(numOfStocks+ " of" +symbol+ " stocks were purchased" );
				}
				else { 
					if((stocksStatus[i].ask * quantity) > balance) {
						throw new BalanceException();
					}
					else {
						stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity() + quantity); 
						updateBalance (-(quantity) * stocksStatus[i].getAsk());
						System.out.println(quantity+ " of " +symbol+ " stocks were succesfully purchased");
					}
				}
			}
		}
	}
	
	
	/**
	 * This method represents the value of all of the stocks that exist in a portfolio
	 * @return the value of the portfolio
	 */
	public float getStocksValue () {
		float value = 0;
		for (int i = 0; i < portfolioSize; i++)
			value += (stocksStatus[i].getBid() * stocksStatus[i].getStockQuantity());
		return value;
	}
	
	/**
	 * This method represents the total value of the portfolio including stock's value and the current balance
	 * @return the total value of the portfolio
	 */
	public float getTotalValue () {
		return (getStocksValue() + getBalance());
	}
	
	public StockStatus findBySymbol(String symbol) {
		for (int i=0; i<MAX_PORTFOLIO_SIZE ;i++){
			if(this.stocksStatus[i] != null && 
					symbol.equalsIgnoreCase(stocksStatus[i].getSymbol()) == true){
				return stocksStatus[i];
			}
		}
		return null;
	}
	
	/**
	 * @return returns string with portfolio title and all stocks html code
	 */
	public String getHtmlString() {
		String htmlTitle = "<b><h1>"+ this.getTitle()+":</h1></b><br>";
		String htmlCodeString = " ";
		for (int i = 0; i < portfolioSize; i++) {
			htmlCodeString += stocksStatus[i].getHtmlDescription() + stocksStatus[i].getStockQuantity() + "<br>";
		}
		htmlCodeString += ("<br><b>Portfolio Value: "+ this.getTotalValue()+ " $ <br> Total stock's value: "+this.getStocksValue()+" $<br>The Balance is: "+this.getBalance()+ " $");
		return htmlTitle + htmlCodeString;
	}	
}

