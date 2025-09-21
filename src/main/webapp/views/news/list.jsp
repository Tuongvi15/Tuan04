<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<h2>Danh sách tin theo danh mục</h2>

<form method="get" action="${pageContext.request.contextPath}/news">
  <select name="madm">
    <c:forEach var="dm" items="${categories}">
      <option value="${dm.madm}" ${dm.madm == madm ? 'selected' : ''}>${dm.tenDanhMuc}</option>
    </c:forEach>
  </select>
  <button type="submit">Xem</button>
</form>

<c:if test="${not empty news}">
  <table border="1" cellpadding="6">
    <tr><th>Mã</th><th>Tiêu đề</th><th>Nội dung</th><th>Liên kết</th></tr>
    <c:forEach var="t" items="${news}">
      <tr>
        <td>${t.matt}</td>
        <td>${t.tieuDe}</td>
        <td>${t.noiDungTT}</td>
        <td><a href="${t.lienKet}" target="_blank">${t.lienKet}</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<p><a href="${pageContext.request.contextPath}/news/form">+ Thêm tin mới</a> |
   <a href="${pageContext.request.contextPath}/news/admin">Quản lý (xóa)</a> |
   <a href="${pageContext.request.contextPath}/">Trang chủ</a></p>
