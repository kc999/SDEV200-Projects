<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Test Page</title>
</head>
<body>
    <h2>Loan Calculator</h2>
    <form action="loan" method="POST">
        <input type="number" name="loanAmt" required placeholder="Loan Amount">
        <input type="number" step=".01" name="interestRate" required placeholder="Annual Interest Rate">
        <input type="number" name="years" required placeholder="Number of Years">
       <button type="submit"> calculate</button>
       <hr/>
       <h3>Loan Amount: ${loanAmount} </h3>
       <h3>Annual Interest Rate: ${annualInterestRate}</h3>
       <h3>Number of Years: ${years}</h3>
       <h3>Monthly Payment: ${monthlyPayment} </h3>
       <h3>Total Payment: ${totalPayment}</h3>
    </form>
    
</body>
</html>