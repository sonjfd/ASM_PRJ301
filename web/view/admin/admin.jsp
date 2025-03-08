<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Users" %>
<%@page import="jakarta.servlet.http.HttpSession" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Trang Thống Kê - Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->
        <link href="${pageContext.request.contextPath}/assets/img/favicon.ico" rel="icon">
        <!-- Google Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Bootstrap and Library Stylesheets -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet">
        <!-- Template Stylesheet -->
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
        <style>
            /* Các style bổ sung cho Dashboard */
            footer {
                background-color: #f8f9fa;
                color: #333;
                padding-top: 3rem;
                padding-bottom: 3rem;
            }
            .footer-title {
                font-size: 1.25rem;
                font-weight: bold;
                text-align: center;
                margin-bottom: 1rem;
            }
            .footer-title + .d-flex {
                justify-content: center;
                align-items: center;
            }
            .footer-title + .d-flex a {
                font-size: 1.5rem;
                transition: color 0.3s ease;
            }
            .footer-title + .d-flex a:hover {
                color: #007bff;
            }
            footer .row > .col-12 {
                text-align: center;
            }
            footer .small {
                font-size: 0.9rem;
                color: #666;
            }
            footer .small a {
                color: #333;
            }
            footer .small a:hover {
                text-decoration: underline;
            }
            .nav-item {
                white-space: nowrap;
            }
            .card-title {
                font-weight: 600;
            }
            .small-text {
                font-size: 0.9rem;
                color: #6c757d;
            }
            .chart-container {
                width: 100%;
                height: 300px;
            }
        </style>
    </head>
    <c:set var="user" value="${sessionScope.user}"></c:set>
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

                <!-- Dashboard Content Start -->
                <div class="container-fluid py-4">
                    <!-- Tiêu đề -->
                    <div class="row mb-3">
                        <div class="col-12">
                            <h3 class="fw-bold">Dashboard Thống Kê</h3>
                        </div>
                    </div>
                    <!-- Các thẻ chỉ số thống kê -->
                    <div class="row g-3">
                        <!-- Weekly Sales Card -->
                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Weekly Sales</h5>
                                    <h3 class="fw-bold">$<c:out value="${weeklySales}" /></h3>
                                    <c:choose>
                                        <c:when test="${weeklySalesLast == 0}">
                                            <p class="small-text mb-0">So sánh với tuần trước: N/A</p>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="weeklyChange" value="${(weeklySales - weeklySalesLast) * 100 / weeklySalesLast}" />
                                            <p class="small-text mb-0">
                                                So sánh với tuần trước: 
                                                <c:choose>
                                                    <c:when test="${weeklyChange >= 0}">
                                                        +<c:out value="${weeklyChange}" />%
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${weeklyChange}" />%
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <!-- Total Orders Card -->
                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Total Orders</h5>
                                    <h3 class="fw-bold"><c:out value="${totalOrders}" /></h3>
                                    <c:choose>
                                        <c:when test="${totalOrdersLast == 0}">
                                            <p class="small-text mb-0">So sánh với tuần trước: N/A</p>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="orderChange" value="${(totalOrders - totalOrdersLast) * 100 / totalOrdersLast}" />
                                            <p class="small-text mb-0">
                                                So sánh với tuần trước: 
                                                <c:choose>
                                                    <c:when test="${orderChange >= 0}">
                                                        +<c:out value="${orderChange}" />%
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${orderChange}" />%
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <!-- Total Revenue Card -->
                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Total Revenue</h5>
                                    <h3 class="fw-bold">$<c:out value="${totalRevenue}" /></h3>
                                    <c:choose>
                                        <c:when test="${totalRevenueLast == 0}">
                                            <p class="small-text mb-0">So sánh với tuần trước: N/A</p>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="revenueChange" value="${(totalRevenue - totalRevenueLast) * 100 / totalRevenueLast}" />
                                            <p class="small-text mb-0">
                                                So sánh với tuần trước: 
                                                <c:choose>
                                                    <c:when test="${revenueChange >= 0}">
                                                        +<c:out value="${revenueChange}" />%
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${revenueChange}" />%
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Hàng thứ hai: Biểu đồ Line (Monthly Sales) và Revenue List (Top Products) -->
                    <div class="row g-3 mt-3">
                        <!-- Biểu đồ Line Chart -->
                        <div class="col-lg-8">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Total Sales (Monthly)</h5>
                                    <div class="chart-container">
                                        <canvas id="lineChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Danh sách doanh thu Top Products -->
                        <div class="col-lg-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Revenue (Q1/${year})</h5>
                                    <ul class="list-group list-group-flush">
                                        <c:forEach var="item" items="${topProductVariants}">
                                            <li class="list-group-item d-flex justify-content-between">
                                                <span>
                                                    ${item.productVariant.product.name} - 
                                                    <c:out value="${item.productVariant.color.color}" /> - 
                                                    <c:out value="${item.productVariant.size.memorySize}" />GB
                                                </span>
                                                <span>$<c:out value="${item.revenue}" /></span>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Hàng thứ ba: Bar Chart (Top Products) và danh sách Active Users -->
                    <div class="row g-3 mt-3">
                        <!-- Biểu đồ Bar Chart -->
                        <div class="col-lg-6">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Top Products (Bar Chart)</h5>
                                    <div class="chart-container">
                                        <canvas id="barChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Danh sách Active Users -->
                        <div class="col-lg-6">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Active Users</h5>
                                    <ul class="list-group list-group-flush">
                                        <c:forEach var="user" items="${activeUsers}">
                                            <li class="list-group-item"><c:out value="${user.fullname}" /></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- Dashboard Content End -->

                <!-- Footer -->
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
        <script src="${pageContext.request.contextPath}/assets/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/lib/tempusdominus/js/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

        <!-- Khởi tạo Line Chart cho Monthly Sales -->
        <script>
            var lineLabels = [
            <c:forEach var="entry" items="${monthlySales}" varStatus="status">
            "Month ${entry.key}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
            ];
            var lineData = [
            <c:forEach var="entry" items="${monthlySales}" varStatus="status">
                <c:out value="${entry.value}" /><c:if test="${!status.last}">,</c:if>
            </c:forEach>
            ];

            const ctxLine = document.getElementById("lineChart").getContext("2d");
            const lineChart = new Chart(ctxLine, {
                type: "line",
                data: {
                    labels: lineLabels,
                    datasets: [{
                            label: "Sales",
                            data: lineData,
                            borderColor: "#4e73df",
                            backgroundColor: "rgba(78, 115, 223, 0.1)",
                            tension: 0.3,
                            fill: true
                        }]
                },
                options: {
                    responsive: true,
                    scales: {y: {beginAtZero: true}}
                }
            });
        </script>

        <!-- Khởi tạo Bar Chart cho Top Products -->
        <script>
            var barLabels = [
            <c:forEach var="item" items="${topProductVariants}" varStatus="status">
            "${item.productVariant.product.name}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
            ];
            var barData = [
            <c:forEach var="item" items="${topProductVariants}" varStatus="status">
                <c:out value="${item.revenue}" /><c:if test="${!status.last}">,</c:if>
            </c:forEach>
            ];

            const ctxBar = document.getElementById("barChart").getContext("2d");
            const barChart = new Chart(ctxBar, {
                type: "bar",
                data: {
                    labels: barLabels,
                    datasets: [{
                            label: "Sales",
                            data: barData,
                            backgroundColor: ["#4e73df", "#1cc88a", "#36b9cc", "#f6c23e", "#e74a3b"]
                        }]
                },
                options: {
                    responsive: true,
                    scales: {y: {beginAtZero: true}}
                }
            });
        </script>
    </body>     
</html>
