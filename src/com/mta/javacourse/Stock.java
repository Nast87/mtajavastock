package com.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Stock extends HttpServlet {
	
	private String symbol;
	private float ask, bid;
	private java.util.Date date;
	
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
	}
}
