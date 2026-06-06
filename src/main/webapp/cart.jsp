<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
        body { font-family: Arial; margin:20px; }
        table { border-collapse: collapse; width: 80%; }
        th, td { border:1px solid #ddd; padding:8px; text-align:left; }
        .total { font-size:1.2em; margin-top:20px; }
    </style>
</head>
<body>
    <h2>Your Cart</h2>
    <c:choose>
        <c:when test="${empty cart.items}">
            <p>Your cart is empty.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr><th>Product</th><th>Price</th><th>Quantity</th><th>Total</th></tr>
                <c:forEach items="${cart.items}" var="item">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>$${item.product.price}</td>
                        <td>${item.quantity}</td>
                        <td>$${item.totalPrice}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="total"><strong>Grand Total: $${cart.total}</strong></div>
            <form action="${pageContext.request.contextPath}/checkout" method="post">
                <button type="submit">Proceed to Checkout</button>
            </form>
        </c:otherwise>
    </c:choose>
    <p><a href="${pageContext.request.contextPath}/products">Continue Shopping</a></p>
</body>
</html>