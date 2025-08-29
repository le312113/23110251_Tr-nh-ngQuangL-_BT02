<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Ảnh</th>
        <th>Tên</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cateList}" var="cate" varStatus="st">
        <tr class="odd gradeX">
            <td>${st.index + 1}</td>

            <c:set var="fname" value="${empty cate.icon ? '' : cate.icon}" />
            <c:url value="/image" var="imgUrl">
                <c:param name="fname" value="${fname}" />
            </c:url>

            <td>
                <c:choose>
                    <c:when test="${not empty cate.icon}">
                        <img src="${imgUrl}" width="200" height="150" alt="${cate.name}" />
                    </c:when>
                    <c:otherwise>
                        <span>Không có ảnh</span>
                    </c:otherwise>
                </c:choose>
            </td>

            <td>${cate.name}</td>

            <td class="center">
                <a href="<c:url value='/admin/category/edit?id=${cate.id}'/>">Sửa</a>
                |
                <a href="<c:url value='/admin/category/delete?id=${cate.id}'/>"
                   onclick="return confirm('Xóa danh mục này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
