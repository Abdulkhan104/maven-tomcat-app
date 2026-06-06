<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Products</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        .product { border:1px solid #ccc; padding:10px; margin:10px; width:300px; display:inline-block; }
        .cart-link { float:right; margin-right:20px; font-size:18px; }
    </style>
</head>
<body>
    <div class="cart-link">
        <a href="${pageContext.request.contextPath}/cart">🛒 View Cart</a>
    </div>
    <h2>Products for ${selectedGender}</h2>
    <c:forEach items="${products}" var="product">
        <div class="product">
            <h3>${product.name}</h3>
            <p>Price: $${product.price}</p>
            <form action="${pageContext.request.contextPath}/add-to-cart" method="post">
                <input type="hidden" name="productId" value="${product.id}">
                <label>Qty: <input type="number" name="quantity" value="1" min="1" style="width:50px;"></label>
                <button type="submit">Add to Cart</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>