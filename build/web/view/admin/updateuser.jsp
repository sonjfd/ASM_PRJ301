<%-- 
    Document   : adduser
    Created on : Feb 11, 2025, 12:57:17 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Users"%>
<!DOCTYPE html>
<%
    
    Users user = (Users) request.getAttribute("user");
    if (user == null) {
        user = new Users(); // Đảm bảo không bị lỗi null
    }
    String mess = (String) request.getAttribute("message");

%>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cập Nhập Người Dùng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        
        <div class="container mt-5">
            <div class="card shadow p-4">
                <h2 class="text-center">Tạo Mới Người Dùng</h2>
                <form action="updateuser" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label class="form-label">ID</label>
                        <input type="number" class="form-control" name="id" value="<%= user.getId() %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Họ Tên</label>
                        <input type="text" class="form-control" name="fullName" value="<%= user.getFullname() %>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên Tài Khoản</label>
                        <input type="text" class="form-control" name="account" value="<%= user.getAccount() %>" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mật Khẩu</label>
                        <input type="password" class="form-control" name="password" value="<%= user.getPassword() %>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="text" class="form-control" name="email" value="<%= user.getEmail() %>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số Điện Thoại</label>
                        <input type="text" class="form-control" name="phone" value="<%= user.getPhone() %>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Giới Tính</label>
                        <select class="form-control" name="gender">
                            <option value="nam" ${"nam".equals(user.getGender()) ? "selected" : ""}>Nam</option>
                            <option value="nu" ${"nu".equals(user.getGender()) ? "selected" : ""}>Nữ</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa Chỉ</label>
                        <input type="text" class="form-control" name="address" value="<%= user.getAddress() %>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Avatar</label>
                        <input type="file" class="form-control" name="avatar">
                        <c:if test="${not empty user.avatar}">
                            <img src="${user.avatar}" alt="Avatar" class="img-thumbnail mt-2" width="100">
                        </c:if>
                    </div>



                    <div class="mb-3"style="display: none;">
                        <label class="form-label">Vai Trò</label>
                        <select class="form-control" name="role">
                            <option value="admin" ${"admin".equals(user.getRole()) ? "selected" : ""}>Admin</option>
                            <option value="user" ${"user".equals(user.getRole()) ? "selected" : ""}>User</option>
                        </select>
                    </div>




                    <div class="text-center" >
                        <button type="submit" class="btn btn-primary">Cập nhập</button>
                    </div>
                    
                        <% if(mess!=null){ %>
                            <span class="text-danger"><%= mess %></span> 
                            <% } %>
                </form>
            </div>
        </div>
    </body>
</html>