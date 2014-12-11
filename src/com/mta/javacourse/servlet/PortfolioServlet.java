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
		Portfolio portfolio1 = portfolioService.getPortfolio();
		Portfolio portfolio2 = new Portfolio(portfolio1);
		
		resp.setContentType("text/html");
		portfolio2.setTitle("Portfolio #2");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio1.removeStock(0);
		resp.getWriter().println("<h1><b>First Stock Removed</h1></b>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.getStocks()[2].setBid((float)55.55);
		resp.getWriter().println("<h1><b>Last Bid Changed</h1></b>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());		
	}
}
