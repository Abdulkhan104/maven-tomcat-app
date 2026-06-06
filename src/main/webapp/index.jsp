<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Men & Women Grocery</title>
    <style>
        body { font-family: Arial; text-align: center; margin-top: 50px; }
        button { padding: 10px 20px; margin: 10px; font-size: 16px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Welcome to Grocery Store</h1>
    <p>Choose your shopping experience:</p>
    <form action="${pageContext.request.contextPath}/set-gender" method="post">
        <button type="submit" name="gender" value="MEN">👨 Shop for Men</button>
        <button type="submit" name="gender" value="WOMEN">👩 Shop for Women</button>
    </form>
</body>
</html>