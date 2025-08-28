<%-- Created by IntelliJ IDEA. User: QUANG LE ... --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <html>
    <head>
    <title>Đăng nhập</title>
    <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
    <form action="<c:url value='/login'/>" method="post">
    <h2>Đăng Nhập Vào Hệ Thống</h2>

    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>

    <section>
    <label>
    <div class="input-group">
    <span class="input-group-addon"><i class="fa fa-user"></i></span>
    <input type="text" name="username" placeholder="Tài khoản" class="form-control">
    </div>
    </label>
    </section>

    <section>
    <label>
    <div class="input-group">
    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
    <input type="password" name="password" placeholder="Mật khẩu" class="form-control">
    </div>
    </label>
    </section>

    <button type="submit">Đăng nhập</button>
    </form>
    </body>
    </html>
