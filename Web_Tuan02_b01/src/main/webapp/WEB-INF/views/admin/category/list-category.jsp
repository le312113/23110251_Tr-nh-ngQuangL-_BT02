<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<p>DEBUG JSP cateList size = ${fn:length(cateList)}</p>

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
            <td>
                <c:choose>
                    <c:when test="${not empty cate.icon}">
                        <img src="${cate.icon}" width="200" height="150" alt="${cate.catename}" />
                    </c:when>
                    <c:otherwise>
                        <span>Không có ảnh</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${cate.catename}</td>
            <td class="center">
                <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>">Sửa</a> |
                <a href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>"
                   onclick="return confirm('Xóa danh mục này?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
