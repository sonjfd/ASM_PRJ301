<%-- 
    Document   : profile
    Created on : Mar 1, 2025, 1:18:32 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Users"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<% 
    Users user = (Users) session.getAttribute("user");
    
    String mess=(String)request.getAttribute("mess");
%>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <title>Hồ Sơ Người Dùng</title>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            />
    </head>
    <body class="bg-light">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
            <div class="container-fluid">
                <a class="navbar-brand" href="home.jsp">Trang chủ</a>
                <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    >
                    <span class="navbar-toggler-icon"></span>
                </button>

            </div>
        </nav>

        <div class="container mt-4">
            <div class="row">
                <!-- Sidebar -->
                <aside class="col-md-3">
                    <div class="list-group">
                        <a href="updateprofile" class="list-group-item">Hồ Sơ</a>
                        <a href="cart.jsp" class="list-group-item">Giỏ hàng</a>
                        <a href="changepassword" class="list-group-item avtive">Đổi mật khẩu</a>
                        <a href="logout" class="list-group-item text-danger">Đăng Xuất</a>
                    </div>
                </aside>

                <!-- Main Profile Section -->

                <main class="col-md-9">
                    
                    <div class="card p-4">
                        <h1 class="text-center">Đổi mật khẩu</h1>
                        

                        <form  action="changepassword" method="POST">
                            <!-- Avatar Upload -->
                            

                            <!-- User Info -->

                            <div class="mb-3">
                               
                                <input type="hidden" class="form-control" name="id" value="<%=user.getId()%>">
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label">Mật khẩu cũ</label>
                                <input type="text" class="form-control" name="oldpass"  required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Mật khẩu mới</label>
                                <input type="text" class="form-control" name="newpass" required>
                            </div>
                                                        <% if(mess!=null){
                    %>
                    <p style="color:red"><%=mess%></p>
                    <%
                        }
                    %>
           
                            <button type="submit" class="btn btn-dark w-100">Cập nhập</button>
                        </form>
                    </div>
                </main>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
            // Xử lý chọn ảnh avatar
            document
                    .getElementById("fileInput")
                    .addEventListener("change", function (event) {
                    const file = event.target.files[0];
                            if (file) {
                    // Kiểm tra dung lượng file (tối đa 1MB)
                    if (file.size > 1024 * 1024) {
                    alert(
                            "Dung lượng ảnh vượt quá 1MB! Vui lòng chọn ảnh khác."
                            );
                            return;
                    }

                    const reader = new FileReader();
                            reader.onload = function (e) {
                            document.getElementById("avatar").src =
                                    e.target.result;
                            };
                            reader.readAsDataURL(file);
                    }
                    });


        </script>
    </body>
</html>
