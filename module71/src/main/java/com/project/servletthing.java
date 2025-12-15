package com.project;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loan")
public class servletthing extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanAmount = req.getParameter("loanAmt");
        String interestRate = req.getParameter("interestRate");
        String loanYears = req.getParameter("years");
        

        try{
            double loanDouble = Double.parseDouble(loanAmount);
            double interestDouble = Double.parseDouble(interestRate);
            double monthlyInterest = interestDouble/100 / 12;
            double loanYearsDouble = Double.parseDouble(loanYears);
            double totalMonths = loanYearsDouble * 12;
            double monthlyPayment = loanDouble * monthlyInterest /( 1-1 / Math.pow(1+ monthlyInterest,loanYearsDouble * 12));
            double totalPayment = totalMonths * monthlyPayment;
            

            
            req.setAttribute("loanAmount", loanDouble);
            req.setAttribute("annualInterestRate", interestDouble);
            req.setAttribute("years", loanYearsDouble);
            req.setAttribute("monthlyPayment", monthlyPayment);
            req.setAttribute("totalPayment", totalPayment);
        }
        catch (NumberFormatException e)
        {
            req.setAttribute("answer", "Invalid Input");
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
