
<%-- 
    Document   : admin.jsp
    Created on : Feb 21, 2025, 8:19:14 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <style>
            /* Đảm bảo body luôn chiếm hết chiều cao của viewport */
            html, body {
                height: 100%;
                margin: 0;
            }

            /* Container chính để sử dụng flexbox */
            .container-xxl {
                display: flex;
                flex-direction: column;
                min-height: 100%;
            }

            /* Phần nội dung chính chiếm không gian còn lại */
            .content {
                flex: 1;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                font-size: 18px;
                text-align: left;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ddd;
            }
            th {
                background-color: #f4f4f4;
            }
            /* Cải thiện button phân trang */
            .pagination {
                list-style-type: none;
                padding: 0;
                margin: 20px 0;
                text-align: center;
            }

            .pagination li {
                display: inline;
                margin-right: 5px;
            }

            .pagination li a {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .pagination li a:hover {
                background-color: #0056b3; /* Màu nền khi hover qua các link phân trang */
            }

            .pagination .active a {
                background-color: #0056b3;
            }

            .pagination .disabled a {
                background-color: #ccc;
                cursor: not-allowed;
            }

            /* Căn chỉnh footer */
            .footer {

                color: #333; /* Màu chữ tối */
                padding-top: 200px;
                padding-bottom: 0;
                margin-top: auto; /* Đảm bảo footer luôn nằm ở dưới */
                margin-bottom: 0;
                text-align: center;
            }

            .nav-item {
                white-space: nowrap;
            } /*thêm dòng này*/

        </style>
    </head>

    <body>
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
                            <a href="attributes" class="dropdown-item">Quản Lí Thuộc Tính</a>
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
            <br/>
            <br/>

            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addImageModal">
                Thêm ảnh mới
            </button>

            <!-- Modal Form -->
            <div class="modal fade" id="addImageModal" tabindex="-1" aria-labelledby="addImageModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addImageModalLabel">Thêm Ảnh Sản Phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="createimg" method="post" enctype="multipart/form-data">
                                <div class="mb-3">
                                    <label for="pid" class="form-label">Chọn Sản Phẩm</label>
                                    <select class="form-select" id="pid" name="pid" required>
                                        <c:forEach items="${listproduct}" var="p">
                                            <option value="${p.id}">${p.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="name" class="form-label">Tên Ảnh</label>
                                    <input type="text" class="form-control" id="name" name="name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="path" class="form-label">Chọn Ảnh</label>
                                    <input type="file" class="form-control" id="path" name="path" required>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-success">Thêm Ảnh</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <!-- Navbar End -->
            <table style="border: 1px solid">
                <tr>
                    <th>ID</th>
                    <th>Tên Sản Phầm</th>
                    <th>Dung Lượng</th>
                    <th>Ảnh</th>


                </tr>

                <c:forEach items="${requestScope.listimg}" var="i">
                    <tr>
                        <td>${i.id}</td>
                        <td>${i.product.name}</td>
                        <td>${i.name}</td>
                        <td><img src="${pageContext.request.contextPath}${i.path}" alt="alt" width="50px"/></td>

                    </tr>

                </c:forEach>

            </table>

            <!-- Footer Start -->
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

                <!-- Footer End -->




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
