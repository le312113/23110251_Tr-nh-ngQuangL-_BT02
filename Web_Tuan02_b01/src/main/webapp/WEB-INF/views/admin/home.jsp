<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="../common/topbar.jsp"/>

<!-- Chặn truy cập nếu chưa đăng nhập -->
<c:if test="${sessionScope.account == null}">
    <c:redirect url='${pageContext.request.contextPath}/views/login.jsp'/>
</c:if>

<h2>Trang Admin</h2>
<p>Xin chào quản trị: ${sessionScope.account.fullName}</p>

<!-- Menu đi tới các trang Category -->
<nav style="margin:12px 0;">
    <a href="<c:url value='/admin/category/list'/>">📂 Danh sách Category</a>
    &nbsp;|&nbsp;
    <a href="<c:url value='/admin/category/add'/>">➕ Thêm Category</a>
</nav>

<!-- Đăng xuất: nên dùng POST và c:url để đúng context path -->
<form action="<c:url value='/logout'/>" method="post">
    <button type="submit">Đăng xuất</button>
</form>
