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
    if (user == null) {
        response.sendRedirect("login.jsp"); 
        return;
    }
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
                        <a href="profile" class="list-group-item active">Hồ Sơ</a>
                        <a href="cart.jsp" class="list-group-item">Giỏ hàng</a>
                        <a href="changepassword" class="list-group-item">Đổi mật khẩu</a>
                        <a href="logout" class="list-group-item text-danger">Đăng Xuất</a>
                    </div>
                </aside>

                <!-- Main Profile Section -->

                <main class="col-md-9">
                    <% if(mess!=null){
                    %>
                    <a><%=mess%></a>
                    <%
                        }
                    %>
                    <div class="card p-4">
                        <h1 class="text-center">Hồ Sơ Của Tôi</h1>
                        <p class="text-center text-muted">
                            Quản lý thông tin hồ sơ để bảo mật tài khoản
                        </p>

                        <form  action="updateprofile" method="POST" enctype="multipart/form-data">
                            <!-- Avatar Upload -->
                            <div class="text-center mb-3">
                                <img
                                    id="avatar"
                                    src="<%= (user.getAvatar() != null) ? user.getAvatar() : "avatar.png" %>"
                                    class="rounded-circle border"
                                    width="120"
                                    height="120"
                                    alt="Avatar"
                                    />
                                <div class="mt-2">
                                    <input
                                        type="file"
                                        id="fileInput"
                                        accept="image/jpeg, image/png"
                                        name ="avatar"
                                        hidden
                                        />
                                    <label
                                        for="fileInput"
                                        class="btn btn-dark btn-sm"

                                        >📂 Chọn Ảnh</label
                                    >
                                </div>
                                <small class="text-muted d-block mt-1"
                                       >Dung lượng tối đa 1MB. Chỉ hỗ trợ JPG,
                                    PNG.</small
                                >
                            </div>

                            <!-- User Info -->

                            <div class="mb-3">
                               
                                <input type="hidden" class="form-control" name="id" value="<%= user.getId() %>">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Họ và tên</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    name="name"
                                    placeholder="<%= user.getFullname()%>"

                                    />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Tên Tài Khoản</label>
                                <input type="text" class="form-control" name="account" value="<%= user.getAccount() %>" readonly>
                            </div>

                            <div class="mb-3">
                                
                                <input type="hidden" class="form-control" name="password" value="<%= user.getPassword() %>" >
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Địa chỉ</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    name="address"
                                    value="<%=user.getAddress()%>"
                                    />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input
                                    type="email"
                                    class="form-control"
                                    name="email"
                                    value="<%=user.getEmail()%>"
                                    readonly
                                    />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Số điện thoại</label>
                                <input
                                    type="tel"
                                    class="form-control"
                                    name="phone"
                                    value="<%=user.getPhone()%>"
                                    />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Giới Tính</label>
                                <select class="form-control" name="gender">
                                    <option value="nam" ${"nam".equals(user.getGender()) ? "selected" : ""}>Nam</option>
                                    <option value="nu" ${"nu".equals(user.getGender()) ? "selected" : ""}>Nữ</option>
                                </select>
                            </div>



                            <button type="submit" class="btn btn-dark w-100">Lưu</button>
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
