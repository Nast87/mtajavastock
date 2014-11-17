package com.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class JavaProjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int num1 = 4, num2 = 3, num3 = 7;
		int result = (num1+num2)*num3;
		String resultStr = new String("<h1>Result of ("+num1+" + "+num2+") * "+num3+" = "+result+"</h1>");
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
				
		int radius = 50;
		int area = (int)(Math.PI * Math.pow(radius, 2));
		int hypotenuse = 50;
		double angleB = Math.PI/6;
		int opposite = (int)(Math.sin(angleB) * hypotenuse);
		int base = 20, exp = 13;
		int result1 = (int)(Math.pow(base, exp));
				
		String line1 = new String("Calculation 1: Area of circle with radius " +radius+ " is : "+area+" square-cm");
		String line2 = new String("Calculation 2: Length of opposite where angle B is 30 degrees and Hypotenuse lenth is 50 cm is : "+opposite+" cm");
		String line3 = new String("Calculation 3: Power of 20 with exp of 13 is : "+result1);
		String resultStr1 = "<br>" + line1 + "<br>" + line2 + "<br>" +line3;
				
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr1);
		
	}
}
