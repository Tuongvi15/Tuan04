<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h2>Thêm tin</h2>

<c:if test="${not empty errors}">
  <ul style="color:red">
    <c:forEach var="e" items="${errors}">
      <li>${e.propertyPath}: ${e.message}</li>
    </c:forEach>
  </ul>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/news/create">
  <label>Tiêu đề:</label>
  <input name="tieuDe" value="${tin.tieuDe}" maxlength="150" required/><br/>

  <label>Nội dung (≤255):</label>
  <input name="noiDungTT" value="${tin.noiDungTT}" maxlength="255" required/><br/>

  <label>Liên kết (bắt đầu http://):</label>
  <input name="lienKet" value="${tin.lienKet}" required
         pattern="^http://.*$" title="Bắt đầu bằng http://"/><br/>

  <label>Danh mục:</label>
  <select name="madm" required>
    <c:forEach var="dm" items="${categories}">
      <option value="${dm.madm}" ${dm.madm == tin.madm ? 'selected' : ''}>${dm.tenDanhMuc}</option>
    </c:forEach>
  </select><br/>

  <button type="submit">Thêm</button>
</form>

<p><a href="${pageContext.request.contextPath}/news">← Danh sách</a> |
<a href="${pageContext.request.contextPath}/">Trang chủ</a></p>
