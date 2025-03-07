<%-- 
    Document   : bill
    Created on : Feb 26, 2025, 10:06:38 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Hóa đơn</title>
        <!-- Thiết lập viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Google Fonts (tuỳ chọn) -->
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&display=swap" rel="stylesheet">
        <!-- Bootstrap (có thể dùng nếu muốn) -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Heebo', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }
            .invoice-container {
                width: 800px;
                margin: 20px auto;
                background: #fff;
                padding: 20px 40px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                color: #333;
            }
            h1, h2, h3, h4, h5 {
                margin: 0;
                padding: 0;
            }
            .store-info, .invoice-info, .customer-info {
                margin-bottom: 20px;
            }
            .store-info h2 {
                color: #007bff;
                margin-bottom: 5px;
            }
            .store-info p {
                margin-bottom: 2px;
            }
            .invoice-info, .customer-info {
                font-size: 15px;
            }
            .invoice-info p, .customer-info p {
                margin-bottom: 5px;
            }
            .products-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            .products-table th {
                background-color: #007bff;
                color: #fff;
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }
            .products-table td {
                padding: 10px;
                border: 1px solid #ddd;
            }
            .total-amount {
                text-align: right;
                font-size: 16px;
                margin-bottom: 20px;
            }
            .print-btn {
                display: inline-block;
                margin-bottom: 20px;
            }
            /* Ẩn nút in khi in ra giấy */
            @media print {
                .print-btn {
                    display: none;
                }
            }
        </style>
    </head>
    <body>

        <div class="invoice-container">
            <!-- Nút Quay lại -->
            <a href="listorderdetail?orderId=${ORDER.id}" class="btn btn-primary print-btn" style='background:#ddd'>
                <i class="fas fa-arrow-left"></i> Quay lại
            </a>
            <!-- Nút in (hoặc bạn có thể auto print onload) -->
            <button class="btn btn-primary print-btn" onclick="window.print();">
                In hóa đơn
            </button>

            <!-- Thông tin cửa hàng -->
            <div class="store-info">
                <h2>CỬA HÀNG ABC</h2>
                <p>Địa chỉ: 123 Nguyễn Trãi, Q.1, TP.HCM</p>
                <p>Điện thoại: 0909 123 456</p>
                <p>Email: lienhe@cuahangabc.vn</p>
            </div>

            <!-- Thông tin hóa đơn -->
            <div class="invoice-info">
                <h4>HÓA ĐƠN BÁN HÀNG</h4>
                <p><strong>Mã đơn hàng:</strong> #${ORDER.id}</p>
                <p><strong>Ngày lập hóa đơn:</strong> ${ORDER.createdAt}</p>
                <p><strong>Phương thức thanh toán:</strong>
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

            <!-- Thông tin khách hàng -->
            <div class="customer-info">
                <h5>Thông tin khách hàng</h5>
                <c:choose>
                    <c:when test="${not empty ORDER.users}">
                        <p><strong>Họ tên:</strong> ${ORDER.users.fullname}</p>
                        <p><strong>Số điện thoại:</strong> ${ORDER.users.phone}</p>
                        <p><strong>Email:</strong> ${ORDER.users.email}</p>
                        <p><strong>Địa chỉ:</strong> ${ORDER.users.address}</p>
                    </c:when>
                    <c:otherwise>
                        <p>Không tìm thấy thông tin khách hàng.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Danh sách sản phẩm -->
            <table class="products-table">
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Giảm giá</th>
                        <th>Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${ORDER_DETAILS}">
                        <tr>
                            <td>${detail.name}</td>
                            <td>${detail.price}</td>
                            <td>${detail.quantity}</td>
                            <td>${detail.discount}</td>
                            <td>${detail.totalPrice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Tổng cộng -->
            <div class="total-amount">
                <strong>Tổng tiền: </strong>${ORDER.price} VNĐ
            </div>

            <!-- Ghi chú -->
            <c:if test="${not empty ORDER.note}">
                <p><strong>Ghi chú: </strong>${ORDER.note}</p>
            </c:if>

            <!-- Chữ ký -->
            <div class="row">
                <div class="col-6">
                    <p><strong>Khách hàng</strong></p>
                    <br><br><br>
                    <p>_____________________</p>
                </div>
                <div class="col-6 text-end">
                    <p><strong>Nhân viên</strong></p>
                    <br><br><br>
                    <p>_____________________</p>
                </div>
            </div>
        </div>

        <!-- Optionally, Bootstrap JS -->
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

