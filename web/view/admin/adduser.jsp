<%-- 
    Document   : adduser
    Created on : Feb 11, 2025, 12:57:17 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String mess = (String)request.getAttribute("message");  
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tạo Mới Người Dùng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <c:if test="${not empty errorMessage}">
            <h1 style="color: red">${errorMessage}</h1>
        </c:if>
        <div class="container mt-5">
            <div class="card shadow p-4">
                <h2 class="text-center">Tạo Mới Người Dùng</h2>
                <form action="adduser" method="POST" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label class="form-label">Họ Tên</label>
                        <input type="text" class="form-control" name="fullName" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên Tài Khoản</label>
                        <input type="text" class="form-control" name="account" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mật Khẩu</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="text" class="form-control" name="email" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số Điện Thoại</label>
                        <input type="text" class="form-control" name="phone" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Giới Tính</label>
                        <select class="form-control" name="gender" required>
                            <option value="nam">Nam</option>
                            <option value="nu">Nữ</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa Chỉ</label>
                        <input type="text" class="form-control" name="address" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Avatar</label>
                        <input type="file" class="form-control" name="avatar" required>
                    </div>



                    <div class="mb-3">
                        <label class="form-label">Vai Trò</label>
                        <select class="form-control" name="role" required>
                            <option value="admin">admin</option>
                            <option value="user">user</option>
                        </select>
                    </div>

                    <div  class="text-center">
                        <button type="submit" class="btn btn-primary">Tạo Người Dùng</button>
                    </div>

                    <% if(mess!=null){%>
                    <span class="text-danger"><%= mess %></span> 
                    <% }%>
                </form>
            </div>
        </div>
    </body>
</html>