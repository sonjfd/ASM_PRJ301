<%-- 
    Document   : listorder
    Created on : Feb 26, 2025, 4:44:43 PM
    Author     : ADMIN
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>Quản Lý Đơn Hàng</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

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

        <!-- Custom CSS riêng -->
        <style>
            /* container */
            .container {
                max-width: 1200px;
                margin: auto;
                padding: 20px;
                background: white;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                min-height: 700px;
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
                font-weight: 600;
                color: #007bff;
            }

            /* Table Styling */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
                background: white;
                border-radius: 8px;
                overflow: hidden;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: center;
            }
            th {
                background-color: #007bff;
                color: white;
                font-weight: bold;
            }
            td {
                background-color: #f4f4f4;
            }
            /* Status label */
            .status {
                padding: 6px 12px;
                border-radius: 20px;
                font-size: 14px;
                font-weight: bold;
            }
            .pending {
                background-color: #f8d7da;
                color: #721c24;
            }
            .processing {
                background-color: #cce5ff;
                color: #004085;
            }
            .completed {
                background-color: #d4edda;
                color: #155724;
            }

            /* Pagination Styling */
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .pagination a {
                text-decoration: none;
                color: #007bff;
                padding: 8px 12px;
                margin: 0 5px;
                border: 1px solid #007bff;
                border-radius: 5px;
                transition: background 0.3s;
            }
            .pagination a.active, .pagination a:hover {
                background-color: #007bff;
                color: white;
            }
            /* Empty Order Message */
            .no-orders {
                text-align: center;
                font-size: 18px;
                color: #666;
                background: #f4f4f4;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }

            /* Search form */
            .search-form {
                margin-bottom: 20px;
                display: flex;
                justify-content: flex-start;
                align-items: center;
            }
            .search-form input[type="text"] {
                width: 250px;
                margin-right: 10px;
                padding: 6px 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .search-form button {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 7px 12px;
                border-radius: 5px;
            }
            .search-form button:hover {
                background-color: #0056b3;
            }

            /* Định nghĩa chung cho nút và trạng thái */
            .table-btn,
            .status {
                border: 1px solid #007bff;
                border-radius: 5px;
            }

            /* Định nghĩa nút chung */
            .table-btn {
                display: inline-block;
                padding: 6px 12px;

                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                transition: background-color 0.3s, border-color 0.3s;
                cursor: pointer;
            }
            .table-btn:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }

            /* Định nghĩa chung cho trạng thái */
            .status {
                display: inline-block;
                padding: 6px 12px;
                border: 1px solid #007bff;
                border-radius: 5px;
                font-size: 14px;
                font-weight: bold;
                text-decoration: none;
                cursor: pointer;
            }
            
            


            /* Các trạng thái thanh toán (chỉ định màu nền và màu chữ) */
            .status-payment-0 {
                background-color: #f8d7da;
                color: #721c24;
            }
            .status-payment-1 {
                background-color: #fff3cd;
                color: #856404;
            }
            .status-payment-2 {
                background-color: #d4edda;
                color: #155724;
            }

            /* Các trạng thái vận chuyển (chỉ định màu nền và màu chữ) */
            .status-transport-0 {
                background-color: #e2e3e5;
                color: #383d41;
            }
            .status-transport-1 {
                background-color: #cce5ff;
                color: #004085;
            }
            .status-transport-2 {
                background-color: #d1ecf1;
                color: #0c5460;
            }
            .status-transport-3 {
                background-color: #d4edda;
                color: #155724;
            }
            .nav-item {
                white-space: nowrap;
            } /*thêm dòng này*/


        </style>
    </head>

    <body>
        <div class="container-xxl position-relative bg-white d-flex p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <!-- Sidebar Start -->
             <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-light navbar-light">
                    <a href="home" class="navbar-brand mx-4 mb-3">
                        <h3 class="text-primary">Trang Admin</h3>
                    </a>
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <div class="position-relative">
                            <img class="rounded-circle" src="<c:url value="'${user.avatar}'"></c:url>" alt="" style="width: 40px; height: 40px;">
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
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="far fa-file-alt me-2"></i>Sản Phẩm</a>
                            <div class="dropdown-menu bg-transparent border-0">
                                <a href="listproduct" class="dropdown-item">Quản Lí Sản Phẩm</a>
                                <a href="listvariant" class="dropdown-item">Quản Lí Biến Thể</a>
                                <a href="attributes" class="dropdown-item">Quản Lí Thuộc Tính</a>
                                <a href="img" class="dropdown-item">Quản Lí Ảnh</a>
                            </div>
                        </div>
                        <a href="listorder" class="nav-item nav-link active"><i class="fa fa-table me-2"></i>Quản Lí Đơn Hàng</a>


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
                                <img class="rounded-circle me-lg-2" src="<c:url value="'${user.avatar}'"></c:url>" alt="" style="width: 40px; height: 40px;">
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

                <!-- Main Container -->
                <div class="container">
                    <h2>Danh Sách Đơn Hàng</h2>

                    <!-- Search Form -->
                    <!-- Form Tìm kiếm & Lọc -->
                    <form action="listorder" method="get" class="mb-4">

                        <!-- DÒNG 1: Tìm kiếm -->
                        <div class="d-flex flex-wrap align-items-end gap-2 mb-3">
                            <!-- Ô tìm kiếm -->
                            <div>
                                <label class="form-label fw-semibold">Tìm kiếm đơn hàng</label>
                                <input type="text" name="keyword" 
                                       class="form-control" 
                                       placeholder="Nhập từ khóa..." 
                                       value="${param.keyword}" />
                            </div>
                            <!-- Nút Tìm kiếm -->
                            <div>
                                <button type="submit" class="btn btn-primary mt-2">
                                    Tìm kiếm
                                </button>
                            </div>
                        </div>

                        <!-- DÒNG 2: Trạng thái thanh toán & Trạng thái vận chuyển -->
                        <div class="d-flex flex-wrap align-items-end gap-2 mb-3">
                            <!-- Trạng thái thanh toán -->
                            <div>
                                <label for="paymentStatus" class="form-label fw-semibold">
                                    Trạng thái thanh toán
                                </label>
                                <select name="paymentStatus" id="paymentStatus" class="form-select">
                                    <option value="">Tất cả</option>
                                    <option value="0" ${paymentStatus == '0' ? 'selected' : ''}>Chưa thanh toán</option>
                                    <option value="1" ${paymentStatus == '1' ? 'selected' : ''}>Đang xử lý</option>
                                    <option value="2" ${paymentStatus == '2' ? 'selected' : ''}>Đã thanh toán</option>
                                </select>
                            </div>
                            <!-- Trạng thái vận chuyển -->
                            <div>
                                <label for="transportStatus" class="form-label fw-semibold">
                                    Trạng thái vận chuyển
                                </label>
                                <select name="transportStatus" id="transportStatus" class="form-select">
                                    <option value="">Tất cả</option>
                                    <option value="0" ${transportStatus == '0' ? 'selected' : ''}>Chưa giao</option>
                                    <option value="1" ${transportStatus == '1' ? 'selected' : ''}>Đang giao</option>
                                    <option value="2" ${transportStatus == '2' ? 'selected' : ''}>Đã giao</option>
                                    <option value="3" ${transportStatus == '3' ? 'selected' : ''}>Hoàn tất</option>
                                </select>
                            </div>
                            <!-- Từ ngày -->
                            <div>
                                <label for="fromDate" class="form-label fw-semibold">Từ ngày</label>
                                <input type="date" name="fromDate" id="fromDate"
                                       class="form-control"
                                       value="${fromDate}" />
                            </div>
                            <!-- Dấu "đến" -->
                            <div class="mt-4 fw-semibold">đến</div>
                            <!-- Đến ngày -->
                            <div>
                                <label for="toDate" class="form-label fw-semibold">Đến ngày</label>
                                <input type="date" name="toDate" id="toDate"
                                       class="form-control"
                                       value="${toDate}" />
                            </div>
                            <!-- Nút Lọc -->
                            <div>
                                <button type="submit" class="btn btn-secondary mt-4">
                                    Lọc
                                </button>
                            </div>
                        </div>

                        
                    </form>






                    <c:choose>
                        <c:when test="${empty ORDER_LIST}">
                            <div class="no-orders">
                                <p>🚀 Hiện tại không có đơn hàng nào.</p>
                                <p>Hãy kiểm tra lại sau hoặc thêm đơn hàng mới!</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Khách hàng</th>
                                        <th>Ngày đặt</th>
                                        <th>Giao đến</th>
                                        <th>Tổng tiền</th>
                                        <th>TT Thanh Toán</th>
                                        <th>TT Vận Chuyển</th>

                                        <th>Cập Nhật</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${ORDER_LIST}">
                                        <tr>
                                            <td>
                                                <a href="listorderdetail?orderId=${order.id}" class="table-btn">${order.id}</a>
                                            </td>
                                            <td>${order.users.fullname}</td>
                                            <td>${order.createdAt}</td>
                                            <td>${order.users.address}</td>
                                            <td>$${order.price}</td>
                                            <td>
                                                <span class="status ${order.orderStatusPayment == 0 ? 'status-payment-0' : (order.orderStatusPayment == 1 ? 'status-payment-1' : 'status-payment-2')}">
                                                    <c:choose>
                                                        <c:when test="${order.orderStatusPayment == 0}">Chưa thanh toán</c:when>
                                                        <c:when test="${order.orderStatusPayment == 1}">Đang xử lý</c:when>
                                                        <c:when test="${order.orderStatusPayment == 2}">Đã thanh toán</c:when>
                                                    </c:choose>
                                                </span>
                                            </td>

                                            <td>
                                                <span class="status ${order.orderStatusTransport == 0 ? 'status-transport-0' : (order.orderStatusTransport == 1 ? 'status-transport-1' : (order.orderStatusTransport == 2 ? 'status-transport-2' : 'status-transport-3'))}">
                                                    <c:choose>
                                                        <c:when test="${order.orderStatusTransport == 0}">Chưa xử lý</c:when>
                                                        <c:when test="${order.orderStatusTransport == 1}">Đang vận chuyển</c:when>
                                                        <c:when test="${order.orderStatusTransport == 2}">Đã giao</c:when>
                                                        <c:when test="${order.orderStatusTransport == 3}">Hoàn tất</c:when>
                                                    </c:choose>
                                                </span>
                                            </td>



                                            <td>
                                                <button type="button" class="table-btn" 
                                                        data-bs-toggle="modal" 
                                                        data-bs-target="#updateModal${order.id}">
                                                    Update
                                                </button>
                                            </td>
                                        </tr>

                                        <!-- Modal Popup cho mỗi đơn hàng -->
                                    <div class="modal fade" id="updateModal${order.id}" tabindex="-1" aria-labelledby="updateModalLabel${order.id}" aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <!-- Modal Header -->
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="updateModalLabel${order.id}">
                                                        Cập Nhật Đơn Hàng #${order.id}
                                                    </h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>

                                                <!-- Modal Body -->
                                                <div class="modal-body">
                                                    <!-- Form cập nhật -->
                                                    <form action="updateorder" method="post">
                                                        <!-- Ẩn ID đơn hàng -->
                                                        <input type="hidden" name="orderId" value="${order.id}" />

                                                        <div class="mb-3">
                                                            <label><strong>Khách hàng:</strong></label>
                                                            <p>${order.users.fullname}</p>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label><strong>Địa chỉ:</strong></label>
                                                            <p>${order.users.address}</p>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label><strong>Ngày đặt:</strong></label>
                                                            <p>${order.createdAt}</p>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label><strong>Tổng tiền:</strong></label>
                                                            <p>$${order.price}</p>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label><strong>Ghi chú:</strong></label>
                                                            <p>${order.note}</p>
                                                        </div>

                                                        <!-- Trạng thái thanh toán -->
                                                        <div class="mb-3">
                                                            <label for="paymentStatus${order.id}" class="form-label">
                                                                <strong>Trạng thái thanh toán:</strong>
                                                            </label>
                                                            <select name="paymentStatus" id="paymentStatus${order.id}" class="form-select">
                                                                <option value="0" ${order.orderStatusPayment == 0 ? "selected" : ""}>Chưa thanh toán</option>
                                                                <option value="1" ${order.orderStatusPayment == 1 ? "selected" : ""}>Đang xử lý</option>
                                                                <option value="2" ${order.orderStatusPayment == 2 ? "selected" : ""}>Đã thanh toán</option>
                                                            </select>
                                                        </div>

                                                        <!-- Trạng thái vận chuyển -->
                                                        <div class="mb-3">
                                                            <label for="transportStatus${order.id}" class="form-label">
                                                                <strong>Trạng thái vận chuyển:</strong>
                                                            </label>
                                                            <select name="transportStatus" id="transportStatus${order.id}" class="form-select">
                                                                <option value="0" ${order.orderStatusTransport == 0 ? "selected" : ""}>Chưa xử lý</option>
                                                                <option value="1" ${order.orderStatusTransport == 1 ? "selected" : ""}>Đang vận chuyển</option>
                                                                <option value="2" ${order.orderStatusTransport == 2 ? "selected" : ""}>Đã giao</option>
                                                                <option value="3" ${order.orderStatusTransport == 3 ? "selected" : ""}>Hoàn tất</option>
                                                            </select>
                                                        </div>

                                                        <!-- Modal Footer -->
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                            <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                                        </div>
                                                    </form>
                                                </div> <!-- end modal-body -->
                                            </div> <!-- end modal-content -->
                                        </div> <!-- end modal-dialog -->
                                    </div> <!-- end modal fade -->

                                </c:forEach>
                                </tbody>
                            </table>

                            <!-- Phân trang -->
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <a href="listorder?page=${currentPage - 1}">&laquo; Prev</a>
                                </c:if>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <a href="listorder?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <a href="listorder?page=${currentPage + 1}">Next &raquo;</a>
                                </c:if>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- Main Container End -->

                <!-- Footer Start -->
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

            </div>
            <!-- Content End -->
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
