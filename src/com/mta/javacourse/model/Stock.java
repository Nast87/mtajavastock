package com.mta.javacourse.model;

import java.util.Date;

public class Stock {
	
	protected String symbol;
	protected float ask, bid;
	protected java.util.Date date;
	
	/**
	* This constructor creates a new Stock
	*/
	public Stock (){
		this("",0,0,new Date(0));
	}
	
	public Stock(String string, float Ask, float Bid, Date date1) {
		symbol = string;
		ask = Ask;
		bid = Bid;
		date = date1;
	}

	/**
	* This constructor copies the data from the Stock and creates new Stock with the same data
	*/
	public Stock(Stock stock){
		this(stock.getSymbol(),stock.getAsk(),stock.getBid(), new Date (stock.getDate().getTime()));
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}
	
	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	public boolean equal (String symbol){
		if (this.symbol.equals(symbol))
			{return true;}
		else
			{return false;}
	}
	
	/**
	 * This method gets the stock's information and presents it at html page.
	 * @return details of stocks 
	 */
	public String getHtmlDescription() {
		String stockHtmlDetailsString = new String ("<b>Stock symbol</b>: "+getSymbol()+" <b>Ask</b>: "+getAsk()+" <b>Bid</b>: "+getBid()+" <b>Date</b>: "+getDate()+ "<b> Quantity of these stocks</b>: ");
		return stockHtmlDetailsString;
	}	
	
}
