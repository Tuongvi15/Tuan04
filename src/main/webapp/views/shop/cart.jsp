<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
  java.util.Map<Integer, vn.edu.lab4.model.CartItem> cart =
      (java.util.Map<Integer, vn.edu.lab4.model.CartItem>) session.getAttribute("cart");
  request.setAttribute("cart", cart);
%>
<h2>Cart</h2>
<c:choose>
  <c:when test="${cart == null || cart.isEmpty()}">
    <p>Giỏ hàng trống.</p>
  </c:when>
  <c:otherwise>
    <table border="1" cellpadding="6">
      <tr><th>Product</th><th>Price</th><th>Qty</th><th>Amount</th><th>Actions</th></tr>
      <c:set var="total" value="0"/>
      <c:forEach var="e" items="${cart}">
        <c:set var="it" value="${e.value}"/>
        <tr>
          <td>${it.product.name}</td>
          <td>${it.product.price}</td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/cart/update">
              <input type="hidden" name="id" value="${it.product.id}"/>
              <input type="number" name="qty" value="${it.qty}" min="0" style="width:60px"/>
              <button type="submit">Update</button>
            </form>
          </td>
          <td>${it.amount}</td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/cart/remove">
              <input type="hidden" name="id" value="${it.product.id}"/>
              <button type="submit">Remove</button>
            </form>
          </td>
        </tr>
        <c:set var="total" value="${total + it.amount}"/>
      </c:forEach>
      <tr><td colspan="3" align="right"><b>Total</b></td><td colspan="2"><b>${total}</b></td></tr>
    </table>
  </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/shop">← Back to shop</a> |
<a href="${pageContext.request.contextPath}/">Trang chủ</a>
