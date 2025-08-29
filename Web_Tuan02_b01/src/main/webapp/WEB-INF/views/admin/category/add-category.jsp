<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:url value="/admin/category/add" var="addUrl" />
<form role="form" action="${addUrl}" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label>Tên danh mục:</label>
        <input class="form-control" name="name" placeholder="please enter category Name" />
    </div>

    <div class="form-group">
        <label>Ảnh đại diện</label>
        <input type="file" name="icon" />
    </div>

    <button type="submit" class="btn btn-default">Thêm</button>
    <button type="reset" class="btn btn-primary">Hủy</button>
</form>
