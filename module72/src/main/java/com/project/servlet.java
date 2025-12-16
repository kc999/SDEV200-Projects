package com.project;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/additionquizzes")
public class servlet extends HttpServlet{
    Random ran = new Random();
    //Create random values to pass to the JSP
    double question1Num1;
    double question1Num2;
    
    double question2Num1;
    double question2Num2;

    double question3Num1;
    double question3Num2;

    double question4Num1;
    double question4Num2;

    double question5Num1;
    double question5Num2;

    double question6Num1;
    double question6Num2;

    double question7Num1;
    double question7Num2;

    double question8Num1;
    double question8Num2;

    double question9Num1;
    double question9Num2;

    double question10Num1;
    double question10Num2;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException
    {
        question1Num1 = ran.nextInt(0,100);
        question1Num2 = ran.nextInt(0,100);
        
        question2Num1 = ran.nextInt(0,100);
        question2Num2 = ran.nextInt(0,100);

        question3Num1 = ran.nextInt(0,100);
        question3Num2 = ran.nextInt(0,100);

        question4Num1 = ran.nextInt(0,100);
        question4Num2 = ran.nextInt(0,100);

        question5Num1 = ran.nextInt(0,100);
        question5Num2 = ran.nextInt(0,100);

        question6Num1 = ran.nextInt(0,100);
        question6Num2 = ran.nextInt(0,100);

        question7Num1 = ran.nextInt(0,100);
        question7Num2 = ran.nextInt(0,100);

        question8Num1 = ran.nextInt(0,100);
        question8Num2 = ran.nextInt(0,100);

        question9Num1 = ran.nextInt(0,100);
        question9Num2 = ran.nextInt(0,100);

        question10Num1 = ran.nextInt(0,100);
        question10Num2 = ran.nextInt(0,100);

            req.setAttribute("question1Num1", question1Num1);
            req.setAttribute("question1Num2", question1Num2);

            req.setAttribute("question2Num1", question2Num1);
            req.setAttribute("question2Num2", question2Num2);

            req.setAttribute("question3Num1", question3Num1);
            req.setAttribute("question3Num2", question3Num2);

            req.setAttribute("question4Num1", question4Num1);
            req.setAttribute("question4Num2", question4Num2);

            req.setAttribute("question5Num1", question5Num1);
            req.setAttribute("question5Num2", question5Num2);

            req.setAttribute("question6Num1", question6Num1);
            req.setAttribute("question6Num2", question6Num2);

            req.setAttribute("question7Num1", question7Num1);
            req.setAttribute("question7Num2", question7Num2);

            req.setAttribute("question8Num1", question8Num1);
            req.setAttribute("question8Num2", question8Num2);

            req.setAttribute("question9Num1", question9Num1);
            req.setAttribute("question9Num2", question1Num2);

            req.setAttribute("question10Num1", question10Num1);
            req.setAttribute("question10Num2", question10Num2);

            try{
            //Forward request
            RequestDispatcher request = req.getRequestDispatcher("/index.jsp");
            request.forward(req, resp);
            }
            catch(IOException e)
            {
                System.out.println("Error Caught" + e);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            double q1 = Double.parseDouble(req.getParameter("question1"));
            double q2 = Double.parseDouble(req.getParameter("question2"));
            double q3 = Double.parseDouble(req.getParameter("question3"));
            double q4 = Double.parseDouble(req.getParameter("question4"));
            double q5 = Double.parseDouble(req.getParameter("question5"));
            double q6 = Double.parseDouble(req.getParameter("question6"));
            double q7 = Double.parseDouble(req.getParameter("question7"));
            double q8 = Double.parseDouble(req.getParameter("question8"));
            double q9 = Double.parseDouble(req.getParameter("question9"));
            double q10 = Double.parseDouble(req.getParameter("question10"));

            double a1 = question1Num1 + question1Num2;
            double a2 = question2Num1 + question2Num2;
            double a3 = question3Num1 + question3Num2;
            double a4 = question4Num1 + question4Num2;
            double a5 = question5Num1 + question5Num2;
            double a6 = question6Num1 + question6Num2;
            double a7 = question7Num1 + question7Num2;
            double a8 = question8Num1 + question8Num2;
            double a9 = question9Num1 + question9Num2;
            double a10 = question10Num1 + question10Num2;

            String answer1;
            if (q1 == a1) answer1 = "Right"; else answer1 = "Wrong";
            String answer2;
            if (q2 == a2) answer2 = "Right"; else answer2 = "Wrong";
            String answer3;
            if (q3 == a3) answer3 = "Right"; else answer3 = "Wrong";
            String answer4;
            if (q4 == a4) answer4 = "Right"; else answer4 = "Wrong";
            String answer5;
            if (q5 == a5) answer5 = "Right"; else answer5 = "Wrong";
            String answer6;
            if (q6 == a6) answer6 = "Right"; else answer6 = "Wrong";
            String answer7;
            if (q7 == a7) answer7 = "Right"; else answer7 = "Wrong";
            String answer8;
            if (q8 == a8) answer8 = "Right"; else answer8 = "Wrong";
            String answer9;
            if (q9 == a9) answer9 = "Right"; else answer9 = "Wrong";
            String answer10;
            if (q10 == a10) answer10 = "Right"; else answer10 = "Wrong";

            req.setAttribute("answer1", answer1);
            req.setAttribute("answer2", answer2);
            req.setAttribute("answer3", answer3);
            req.setAttribute("answer4", answer4);
            req.setAttribute("answer5", answer5);
            req.setAttribute("answer6", answer6);
            req.setAttribute("answer7", answer7);
            req.setAttribute("answer8", answer8);
            req.setAttribute("answer9", answer9);
            req.setAttribute("answer10", answer10);

            req.setAttribute("question1Num1", question1Num1);
            req.setAttribute("question1Num2", question1Num2);

            req.setAttribute("question2Num1", question2Num1);
            req.setAttribute("question2Num2", question2Num2);

            req.setAttribute("question3Num1", question3Num1);
            req.setAttribute("question3Num2", question3Num2);

            req.setAttribute("question4Num1", question4Num1);
            req.setAttribute("question4Num2", question4Num2);

            req.setAttribute("question5Num1", question5Num1);
            req.setAttribute("question5Num2", question5Num2);

            req.setAttribute("question6Num1", question6Num1);
            req.setAttribute("question6Num2", question6Num2);

            req.setAttribute("question7Num1", question7Num1);
            req.setAttribute("question7Num2", question7Num2);

            req.setAttribute("question8Num1", question8Num1);
            req.setAttribute("question8Num2", question8Num2);

            req.setAttribute("question9Num1", question9Num1);
            req.setAttribute("question9Num2", question1Num2);

            req.setAttribute("question10Num1", question10Num1);
            req.setAttribute("question10Num2", question10Num2);
        }
        catch (NumberFormatException e)
        {
            req.setAttribute("answer1", "Invalid Input");
            req.setAttribute("answer2", "Invalid Input");
            req.setAttribute("answer3", "Invalid Input");
            req.setAttribute("answer4", "Invalid Input");
            req.setAttribute("answer5", "Invalid Input");
            req.setAttribute("answer6", "Invalid Input");
            req.setAttribute("answer7", "Invalid Input");
            req.setAttribute("answer8", "Invalid Input");
            req.setAttribute("answer9", "Invalid Input");
            req.setAttribute("answer10", "Invalid Input");
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
