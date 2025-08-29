<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="../common/topbar.jsp"/>
<h2>Trang Manager</h2>
<p>Xin chào quản lý: ${sessionScope.account.fullName}</p>
<form action="logout" method="get">
    <button type="submit">Đăng xuất</button>
</form>