package com.mta.javacourse.model;

import java.util.Date;

public class Stock {
	
	private String symbol;
	private float ask, bid;
	private java.util.Date date;
	
	/**
	* This constructor creates a new Stock
	*/
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
		this(stock.getSymbol(),stock.getAsk(),stock.getBid(),stock.getDate());
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

	public String getHtmlDescription() {
		String stockHtmlDetailsString = new String ("<b>Stock symbol</b>: "+getSymbol()+" <b>Ask</b>: "+getAsk()+" <b>Bid</b>: "+getBid()+" <b>Date</b>: "+getDate());
		return stockHtmlDetailsString;
	}
}
