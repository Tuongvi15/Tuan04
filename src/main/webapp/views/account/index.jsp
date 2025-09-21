<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><meta charset="UTF-8"><title>Bài 1 - Đăng ký</title></head>
<body>
<h2>Register</h2>
<form method="post" action="${pageContext.request.contextPath}/account/create">
  <label>Full name:</label>
  <input name="fullName" value="${form.fullName}" required/><br/>
  <label>Email:</label>
  <input name="email" type="email" value="${form.email}" required/><br/>
  <label>Password:</label>
  <input type="password" name="password" minlength="6" required/><br/>
  <button type="submit">Create</button>
</form>

<c:if test="${not empty errors}">
  <ul style="color:red">
    <c:forEach var="e" items="${errors}">
      <li>${e.propertyPath}: ${e.message}</li>
    </c:forEach>
  </ul>
</c:if>

<hr/>
<h2>Accounts</h2>
<c:choose>
  <c:when test="${empty accounts}">No data</c:when>
  <c:otherwise>
    <table border="1" cellpadding="6">
      <tr><th>ID</th><th>FullName</th><th>Email</th></tr>
      <c:forEach var="a" items="${accounts}">
        <tr>
          <td>${a.id}</td>
          <td>${a.fullName}</td>
          <td>${a.email}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
<p><a href="${pageContext.request.contextPath}/">Trang chủ</a></p>
</body>
</html>
