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
	public StockStatus(){
		super();
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	
	public StockStatus(String symbol, float ask, float bid, Date date){
		super(symbol, ask, bid, date);
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	
	public StockStatus(Stock stock){
		super(stock);
		this.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	
	//copy c'tor
	public StockStatus (StockStatus stockStatus){
		super((Stock)stockStatus);
		this.recommendation = stockStatus.recommendation;
		this.stockQuantity = stockStatus.stockQuantity;
	}
	
}
	

