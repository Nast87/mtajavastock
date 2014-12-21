package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.service.PortfolioService;

/**
 * An instance of this class presents the portfolio data in web page.
 * @author AnastasyaZiser
 * 20/12/2014
 */

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		
		resp.setContentType("text/html");
		resp.getWriter().println(portfolio.getHtmlString());			
	}
}
