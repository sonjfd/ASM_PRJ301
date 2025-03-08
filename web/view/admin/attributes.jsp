<%-- 
    Document   : admin.jsp
    Created on : Feb 21, 2025, 8:19:14 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Trang Admin</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/assets/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">


    </head>
    
    <style>
        .nav-item {
                white-space: nowrap;
            } /*thêm dòng này*/
    </style>

    <body>
        <div class="container-xxl position-relative bg-white d-flex p-0">
            <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-light navbar-light">
                    <a href="home" class="navbar-brand mx-4 mb-3">
                        <h3 class="text-primary">Trang Chủ</h3>
                    </a>
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <div class="position-relative">
                            <img class="rounded-circle" src="${pageContext.request.contextPath}/${user.avatar}" alt="" style="width: 40px; height: 40px;">
                            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                        </div>
                        <div class="ms-3">
                            <h6 class="mb-0">${user.fullname}</h6>
                            <span>Admin</span>
                        </div>
                    </div>


                    <div class="navbar-nav w-100">
                        <a href="admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Bảng Điều Khiển</a>
                        <a href="user" class="nav-item nav-link"><i class="fa fa-laptop me-2"></i>Quản Lí Người Dùng</a>
                        <a href="listcategories" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Quản Lí Danh Mục</a>

                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown"><i class="far fa-file-alt me-2"></i>Sản Phẩm</a>
                            <div class="dropdown-menu bg-transparent border-0">
                                <a href="listproduct" class="dropdown-item">Quản Lí Sản Phẩm</a>
                                <a href="listvariant" class="dropdown-item">Quản Lí Biến Thể</a>
                                <a href="attributes" class="dropdown-item ">Quản Lí Thuộc Tính</a>
                                <a href="img" class="dropdown-item">Quản Lí Ảnh</a>
                            </div>
                        </div>
                        <a href="listorder" class="nav-item nav-link"><i class="fa fa-table me-2"></i>Quản Lí Đơn Hàng</a>


                    </div>
                </nav>
            </div>


            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                    <a href="admin" class="navbar-brand d-flex d-lg-none me-4">
                        <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                    </a>
                    <a href="#" class="sidebar-toggler flex-shrink-0">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="navbar-nav align-items-center ms-auto">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <img class="rounded-circle me-lg-2" src="${pageContext.request.contextPath}/${user.avatar}" alt="" style="width: 40px; height: 40px;">
                                <span class="d-none d-lg-inline-flex">${user.fullname}</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                                <a href="home" class="dropdown-item">Trang chủ</a>
                                <a href="updateprofile" class="dropdown-item">Cài Đặt Tài Khoản</a>
                                <a href="changepassword" class="dropdown-item">Đổi Mật Khẩu</a>
                                <a href="logout" class="dropdown-item">Đăng Xuất</a>
                            </div>
                        </div>
                    </div>
                </nav>
                <!-- Navbar End -->




                <!-- Colors Table -->
                <h2>Colors</h2>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addColorModal">Thêm Màu</button>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Duyệt qua danh sách Colors -->
                        <c:forEach items="${listcolor}" var="color">
                            <tr>
                                <td>${color.id}</td>
                                <td>${color.color}</td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                <div class="modal fade" id="addColorModal" tabindex="-1" aria-labelledby="addColorModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addColorModalLabel">Thêm màu</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="addcolor" method="POST">
                                    <div class="mb-3">
                                        <label for="colorName" class="form-label">Tên Màu</label>
                                        <input type="text" class="form-control" id="colorName" name="color" required>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                        <button type="submit" class="btn btn-primary">Thêm màu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Sizes Table -->
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addSizeModal">Thêm Dung Lượng</button>
                <h2>Sizes</h2>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Duyệt qua danh sách Sizes -->
                        <c:forEach items="${listsize}" var="size">
                            <tr>
                                <td>${size.id}</td>
                                <td>${size.memorySize}</td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>



                <!-- Modal for Add Size -->
                <div class="modal fade" id="addSizeModal" tabindex="-1" aria-labelledby="addSizeModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addSizeModalLabel">Thêm Dung Lượng</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="addsize" method="POST">
                                    <div class="mb-3">
                                        <label for="sizeName" class="form-label">GB</label>
                                        <input type="text" class="form-control" id="sizeName" name="memorySize" required>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                        <button type="submit" class="btn btn-primary"> Thêm</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="footer">
                    <div class="container-fluid pt-5 px-4 bg-light text-dark mt-3">
                        <div class="row">
                            <!-- Liên kết bản quyền -->
                            <div class="col-12 col-sm-6 text-center text-sm-start mb-4 mb-sm-0">
                                <p class="text-dark text-decoration-none">&copy;Được tài trợ bởi SE1903</p>
                            </div>

                            <!-- Liên kết MXH -->
                            <div class="col-md-6 mb-3 mb-md-0">

                                <div class="d-flex justify-content-center justify-content-sm-end">
                                    <a href="#" class="text-primary me-3"><i class="fab fa-facebook"></i></a>
                                    <a href="#" class="text-danger me-3"><i class="fab fa-instagram"></i></a>
                                </div>
                            </div>
                        </div>

                        <!-- Thông tin thiết kế và phân phối -->
                        <div class="row mt-3">
                            <div class="col-12 text-center">
                                <h5 class="mb-0">Nhóm DSH</h5>
                                <h5 class="mb-0">se1903@fpt.edu.vn</h5>
                                <h5 class="mb-0">Hân hạnh được phục vụ quý khách</h5>
                            </div>
                        </div>
                    </div>






                    <!-- JavaScript Libraries -->
                    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/chart/chart.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/easing/easing.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/waypoints/waypoints.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/owlcarousel/owl.carousel.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/tempusdominus/js/moment.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/tempusdominus/js/moment-timezone.min.js"></script>
                    <script src="${pageContext.request.contextPath}/assets/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
                    </body>

                    </html>
