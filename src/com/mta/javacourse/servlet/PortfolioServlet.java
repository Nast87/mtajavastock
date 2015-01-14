package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.QuantityException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.service.PortfolioService;

/**
 * An instance of this class presents the portfolio data in web page.
 * @author AnastasyaZiser
 * 20/12/2014
 */

public class PortfolioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio;
		try {
			portfolio = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio.getHtmlString());
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().println(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().println(e.getMessage());
		} catch (BalanceException e) {
			resp.getWriter().println(e.getMessage());
		} catch (StockNotExistException e) {
			resp.getWriter().println(e.getMessage());
		} catch (QuantityException e) {
			resp.getWriter().println(e.getMessage());
		}
		resp.setContentType("text/html");
	}
}
