<c:url value="/admin/category/edit" var="editUrl"/>
<c:url value="/image" var="imgUrl">
  <c:param name="fname" value="${category.icon}"/>
</c:url>

<form method="post" action="${editUrl}" enctype="multipart/form-data">
  <input type="hidden" name="id" value="${category.cateid}"/>
  <input type="hidden" name="oldIcon" value="${category.icon}"/>

  <label>Tên danh mục:</label>
  <input type="text" name="name" value="${category.catename}" class="form-control"/>

  <p>Ảnh hiện tại:</p>
  <img src="${imgUrl}" width="120" alt="icon"/>

  <p>Chọn ảnh mới (nếu muốn đổi):</p>
  <input type="file" name="icon"/>

  <button type="submit" class="btn btn-default">Lưu</button>
</form>
