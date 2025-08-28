<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><meta charset="UTF-8"><title>Đăng ký</title></head>
<body>
<jsp:include page="/WEB-INF/views/common/topbar.jsp"/>

<h2>Đăng ký tài khoản</h2>

<c:if test="${alert != null}">
  <div style="color:red">${alert}</div>
</c:if>

<form action="<c:url value='/register'/>" method="post">
  <div>Email*: <input type="email" name="email"></div>
  <div>Username*: <input type="text" name="username"></div>
  <div>Họ tên: <input type="text" name="fullname"></div>
  <div>Điện thoại: <input type="text" name="phone"></div>
  <div>Mật khẩu*: <input type="password" name="password"></div>
  <div>Nhập lại mật khẩu*: <input type="password" name="repass"></div>
  <button type="submit">Đăng ký</button>
</form>
</body>
</html>
