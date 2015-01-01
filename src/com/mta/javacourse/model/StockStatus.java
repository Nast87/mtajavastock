package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

public class StockStatus extends Stock {
	
	private int stockQuantity;
	private ALGO_RECOMMENDATION recommendation;
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	
	/**
	 * This c'tor creates new stockStatus
	 */
	public StockStatus(String symbol, float ask, float bid, Date date, int stockQuantity, ALGO_RECOMMENDATION recommendation) {
		super(symbol, ask, bid, date);
		this.stockQuantity = stockQuantity;
		this.recommendation = recommendation;
	}
	
	/**
	 * This copy-c'tor copies the data from the stockStatus and creates new stockStatus with the same data
	 */
	public StockStatus (StockStatus stocksStatus) {
		this(stocksStatus.getSymbol(), stocksStatus.getAsk(), stocksStatus.getBid(), new Date(stocksStatus.getDate().getTime()), stocksStatus.getStockQuantity(), stocksStatus.getRecommendation());
	}
	
}
