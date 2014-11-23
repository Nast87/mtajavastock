package com.mta.javacourse;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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
		
		Calendar c = Calendar.getInstance();
		c.set(2014, 10, 15, 00, 00);
		Date myDate = c.getTime();
		
		Stock stock1;
		stock1 = new Stock();
		stock1.setSymbol("PIH");
		stock1.setAsk((float) 12.4);
		stock1.setBid((float) 13.1);
		stock1.setDate(myDate);

		Stock stock2;
		stock2 = new Stock();
		stock2.setSymbol("AAL");
		stock2.setAsk((float) 5.5);
		stock2.setBid((float) 5.78);
		stock2.setDate(myDate);
			
		Stock stock3;
		stock3 = new Stock();
		stock3.setSymbol("CAAS");
		stock3.setAsk((float) 31.5);
		stock3.setBid((float) 31.2);
		stock3.setDate(myDate);
		
		String line1 = new String (stock1.getHtmlDescription());
		String line2 = new String (stock2.getHtmlDescription());
		String line3 = new String (stock3.getHtmlDescription());
		//String stockHtmlDetailsString = line1 + "<br>" + line2 + "<br>" +line3;
		
		resp.setContentType("text/html");
		resp.getWriter().println(line1 + "<br>" + line2 + "<br>" +line3);
	}
}
