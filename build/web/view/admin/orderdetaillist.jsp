<%-- 
    Document   : orderdetaillist
    Created on : Feb 26, 2025, 8:46:31 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Chi Tiết Đơn Hàng</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/assets/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Bootstrap CSS -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">

        <!-- Custom CSS -->
        <style>
            body {
                font-family: 'Heebo', sans-serif;
                background-color: #f8f9fa;
            }
            .container-custom {
                max-width: 1200px;
                margin: auto;
                padding: 20px;
            }
            .order-header {
                background: #fff;
                border-radius: 8px;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            }
            .order-header h4 {
                margin: 0;
                color: #007bff;
                font-weight: 600;
            }
            .order-header .order-info {
                font-size: 14px;
                color: #555;
            }
            .order-header .status-badge {
                font-size: 14px;
                padding: 5px 10px;
                border-radius: 20px;
                margin-right: 10px;
                color: #fff;
            }
            .status-payment-0 {
                background: #dc3545;
            } /* Chưa thanh toán */
            .status-payment-1 {
                background: #ffc107;
            } /* Đang xử lý */
            .status-payment-2 {
                background: #28a745;
            } /* Đã thanh toán */

            .status-transport-0 {
                background: #6c757d;
            } /* Chưa xử lý */
            .status-transport-1 {
                background: #17a2b8;
            } /* Đang vận chuyển */
            .status-transport-2 {
                background: #007bff;
            } /* Đã giao */
            .status-transport-3 {
                background: #20c997;
            } /* Hoàn tất */

            .btn-back {
                margin-right: 10px;
            }
            .btn-print {
                margin-right: 10px;
            }

            /* Left & Right columns */
            .order-content {
                margin-top: 20px;
            }
            .order-left, .order-right {
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                padding: 20px;
            }
            .order-left {
                margin-bottom: 20px;
            }

            /* Table of products */
            .products-table table {
                width: 100%;
                border-collapse: collapse;
            }
            .products-table th, .products-table td {
                padding: 12px;
                border: 1px solid #ddd;
                vertical-align: middle;
            }
            .products-table th {
                background-color: #007bff;
                color: #fff;
            }

            /* Payment & Shipping Info */
            .info-box {
                background: #f8f9fa;
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 5px;
                margin-top: 15px;
                font-size: 14px;
            }
            .info-box strong {
                color: #333;
            }

            /* Customer Info */
            .customer-info .title {
                font-size: 16px;
                font-weight: 600;
                color: #007bff;
                margin-bottom: 10px;
            }
            .customer-info p {
                margin-bottom: 6px;
            }
            .customer-info p strong {
                width: 100px;
                display: inline-block;
                color: #555;
            }

            /* Summary / note */
            .summary {
                margin-top: 20px;
                font-size: 16px;
            }
            .summary p {
                margin-bottom: 6px;
            }
            .summary .total-price {
                font-size: 18px;
                color: #dc3545;
                font-weight: 600;
            }

        </style>
    </head>
    <body>
        <div class="container-xxl position-relative d-flex p-0">
            <!-- Sidebar Start -->
             <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-light navbar-light">
                    <a href="home" class="navbar-brand mx-4 mb-3">
                        <h3 class="text-primary">Trang Chủ</h3>
                    </a>
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <div class="position-relative">
                            <img class="rounded-circle" src="${pageContext.request.contextPath}/assets/img/user.jpg" alt="" style="width: 40px; height: 40px;">
                            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                        </div>
                        <div class="ms-3">
                            <h6 class="mb-0">Jhon Doe</h6>
                            <span>Admin</span>
                        </div>
                    </div>


                    <div class="navbar-nav w-100">
                        <a href="admin" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>Bảng Điều Khiển</a>
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
                        <a href="listorder" class="nav-item nav-link"><i class="fa fa-table me-2"></i>Quản Lí Đơn Hàng</a>


                    </div>
                </nav>
            </div>
            <!-- Sidebar End -->

            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                    <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                        <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                    </a>
                    <a href="#" class="sidebar-toggler flex-shrink-0">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="navbar-nav align-items-center ms-auto">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <img class="rounded-circle me-lg-2" src="${pageContext.request.contextPath}/assets/img/user.jpg" alt="" style="width: 40px; height: 40px;">
                                <span class="d-none d-lg-inline-flex">John Doe</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                                <a href="#" class="dropdown-item">Trang Cá Nhân</a>
                                <a href="#" class="dropdown-item">Cài Đặt Tài Khoản</a>
                                <a href="#" class="dropdown-item">Đổi Mật Khẩu</a>
                                <a href="#" class="dropdown-item">Đăng Xuất</a>
                            </div>
                        </div>
                    </div>
                </nav>
                <!-- Navbar End -->

                <div class="container-custom">
                    <!-- Order Header -->
                    <div class="order-header d-flex flex-wrap align-items-center justify-content-between">
                        <div>
                            <h4>Đơn hàng #${ORDER.id}</h4>
                            <div class="order-info mt-1">
                                Ngày đặt: ${ORDER.createdAt}
                            </div>
                        </div>
                        <div class="d-flex align-items-center">
                            <!-- Trạng thái thanh toán -->
                            <span class="status-badge status-payment-${ORDER.orderStatusPayment}">
                                <c:choose>
                                    <c:when test="${ORDER.orderStatusPayment == 0}">Chưa thanh toán</c:when>
                                    <c:when test="${ORDER.orderStatusPayment == 1}">Đang xử lý</c:when>
                                    <c:when test="${ORDER.orderStatusPayment == 2}">Đã thanh toán</c:when>
                                </c:choose>
                            </span>
                            <!-- Trạng thái vận chuyển -->
                            <span class="status-badge status-transport-${ORDER.orderStatusTransport}">
                                <c:choose>
                                    <c:when test="${ORDER.orderStatusTransport == 0}">Chưa xử lý</c:when>
                                    <c:when test="${ORDER.orderStatusTransport == 1}">Đang vận chuyển</c:when>
                                    <c:when test="${ORDER.orderStatusTransport == 2}">Đã giao</c:when>
                                    <c:when test="${ORDER.orderStatusTransport == 3}">Hoàn tất</c:when>
                                </c:choose>
                            </span>

                            <!-- Nút quay lại -->
                            <a href="listorder?page=1" class="btn btn-secondary btn-back ms-3">
                                <i class="fas fa-arrow-left"></i> Quay lại
                            </a>
                            <!-- Nút in bill -->
                            <a href="bill?orderId=${ORDER.id}" class="btn btn-info btn-print ms-2">
                                <i class="fas fa-print"></i> In Bill
                            </a>

                        </div>
                    </div>
                    <!-- End Order Header -->

                    <div class="order-content row mt-4">
                        <!-- Left column: Products & note -->
                        <div class="col-lg-8 order-left mb-4 mb-lg-0">
                            <h5 class="mb-3">Chi tiết đơn hàng</h5>
                            <div class="products-table table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Sản phẩm</th>
                                            <th>Hình ảnh</th>

                                            <th>Giá</th>
                                            <th>Số lượng</th>
                                            <th>Giảm giá</th>
                                            <th>Thành tiền</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="detail" items="${ORDER_DETAILS}">
                                            <tr>
                                                <td>
                                                    <c:out value="${detail.name}" />
                                                </td>
                                                <td>
                                                    <img src="<c:url value='${detail.avatar}' />" alt="alt" style="height: 20px; width: 20px;"/>
                                                </td>
                                                <td>${detail.price}</td>
                                                <td>${detail.quantity}</td>
                                                <td>${detail.discount}</td>
                                                <td>${detail.totalPrice}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <!-- Ghi chú đơn hàng (nếu có) -->
                            <div class="info-box mt-3">
                                <strong>Ghi chú:</strong> 
                                <p class="mb-0">${ORDER.note}</p>
                            </div>

                            <!-- Tổng cộng -->
                            <div class="summary">
                                <p><strong>Tổng tiền:</strong> ${ORDER.price}</p>
                                <!-- Bạn có thể hiển thị thêm phí ship, thuế,... tuỳ nghiệp vụ -->
                                <!-- <p>Phí ship: 30000</p> -->
                                <!-- <p class="total-price">Tổng thanh toán: 730000</p> -->
                            </div>
                        </div>

                        <!-- Right column: Customer info, shipping/payment -->
                        <div class="col-lg-4 order-right">
                            <div class="customer-info mb-4">
                                <div class="title">Khách hàng</div>
                                <p><strong>Họ tên:</strong> 
                                    <c:choose>
                                        <c:when test="${not empty ORDER.users}">
                                            ${ORDER.users.fullname}
                                        </c:when>
                                        <c:otherwise>
                                            [Chưa có]
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p><strong>SĐT:</strong>
                                    <c:choose>
                                        <c:when test="${not empty ORDER.users}">
                                            ${ORDER.users.phone}
                                        </c:when>
                                        <c:otherwise>
                                            [N/A]
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p><strong>Email:</strong>
                                    <c:choose>
                                        <c:when test="${not empty ORDER.users}">
                                            ${ORDER.users.email}
                                        </c:when>
                                        <c:otherwise>
                                            [N/A]
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p><strong>Địa chỉ:</strong>
                                    <c:choose>
                                        <c:when test="${not empty ORDER.users}">
                                            ${ORDER.users.address}
                                        </c:when>
                                        <c:otherwise>
                                            [N/A]
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>

                            <div class="info-box mb-4">
                                <strong>Phương thức thanh toán:</strong>
                                <p class="mb-1">
                                    <c:choose>
                                        <c:when test="${ORDER.payment.id == 1}">Thanh toán khi nhận hàng (COD)</c:when>
                                        <c:when test="${ORDER.payment.id == 2}">Thanh toán qua thẻ ngân hàng</c:when>
                                        <c:when test="${ORDER.payment.id == 3}">Thanh toán qua ví điện tử</c:when>
                                        <c:when test="${ORDER.payment.id == 4}">Thanh toán chuyển khoản</c:when>
                                        <c:when test="${ORDER.payment.id == 5}">Thanh toán qua PayPal</c:when>
                                        <c:otherwise>Không xác định</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>

                            <div class="info-box">
                                <strong>Phương thức vận chuyển:</strong>
                                <p class="mb-1">
                                    <c:choose>
                                        <c:when test="${ORDER.shipping.id == 1}">Giao hàng tiêu chuẩn</c:when>
                                        <c:when test="${ORDER.shipping.id == 2}">Giao hàng nhanh</c:when>
                                        <c:when test="${ORDER.shipping.id == 3}">Giao hàng hỏa tốc</c:when>
                                        <c:when test="${ORDER.shipping.id == 4}">Giao hàng quốc tế</c:when>
                                        <c:when test="${ORDER.shipping.id == 5}">Giao hàng tiết kiệm</c:when>
                                        <c:otherwise>Không xác định</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Footer Start -->
                <div class="container-fluid pt-5 px-4 bg-light text-dark mt-3">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start mb-4 mb-sm-0">
                            <p class="text-dark text-decoration-none">&copy; Được tài trợ bởi SE1903</p>
                        </div>
                        <div class="col-md-6 mb-3 mb-md-0">
                            <div class="d-flex justify-content-center justify-content-sm-end">
                                <a href="#" class="text-primary me-3"><i class="fab fa-facebook"></i></a>
                                <a href="#" class="text-danger me-3"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
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
