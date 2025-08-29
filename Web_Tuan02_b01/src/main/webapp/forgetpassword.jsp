<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Đặt lại mật khẩu</title>
</head>
<body>
<h2>Đặt lại mật khẩu</h2>

<form action="<c:url value='/forgetpassword'/>" method="post">
    <c:if test="${not empty alert}">
        <div style="color:red">${alert}</div>
    </c:if>

    <!-- Ô nhập email -->
    <div>
        <label>Email:</label>
        <input type="email" name="email" required placeholder="Nhập email của bạn">
    </div>

    <!-- Ô nhập mật khẩu mới -->
    <div>
        <label>Mật khẩu mới:</label>
        <input type="password" name="newpassword" required placeholder="Nhập mật khẩu mới">
    </div>

    <!-- Ô nhập lại mật khẩu mới -->
    <div>
        <label>Xác nhận mật khẩu mới:</label>
        <input type="password" name="confirmPassword" required placeholder="Nhập lại mật khẩu mới">
    </div>

    <button type="submit">Đổi mật khẩu</button>
</form>
</body>
</html>
