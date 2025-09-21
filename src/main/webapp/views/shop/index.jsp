<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h2>Products</h2>
<c:forEach var="p" items="${products}">
  <div style="margin:8px 0">
    <b>${p.name}</b> - ${p.price}đ
    <form method="post" action="${pageContext.request.contextPath}/cart/add" style="display:inline">
      <input type="hidden" name="id" value="${p.id}"/>
      <input type="number" name="qty" value="1" min="1" style="width:60px"/>
      <button type="submit">Add to cart</button>
    </form>
  </div>
</c:forEach>
<a href="${pageContext.request.contextPath}/cart">View cart</a> |
<a href="${pageContext.request.contextPath}/">Trang chủ</a>
