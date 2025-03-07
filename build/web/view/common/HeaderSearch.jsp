<%-- 
    Document   : shop
    Created on : Mar 2, 2025, 11:35:51 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Sản Phẩm</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min_2.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min_1.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style1.css"/>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .store-sort ul {
                list-style: none;
                padding: 0;
                margin: 0;
                display: flex;
                gap: 15px;
            }

            .store-sort ul li {
                display: inline-block;
            }

            .store-sort ul li a {
                text-decoration: none;
                color: #333;
                font-weight: bold;
                padding: 8px 12px;
                border-radius: 5px;
                transition: all 0.3s ease;
            }

            .store-sort ul li a:hover {
                background-color: #007bff;
                color: #fff;
            }


        </style>

    </head>
    <body>
        <!-- HEADER -->
        <%@include file="../layout/header.jsp" %>
        <!-- /HEADER -->






        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- ASIDE -->
                    <form id="filter-form" action="shopdetails" method="GET">
                        <div id="aside" class="col-md-3">
                            <!-- Filter theo danh mục -->
                            <div class="aside">
                                <h3 class="aside-title">Categories</h3>
                                <div class="checkbox-filter">
                                    <c:forEach items="${listcategori}" var="cate">
                                        <c:if test="${cate.status == 1}">
                                            <div class="input-checkbox">
                                                <input type="checkbox" id="category-${cate.id}" name="category[]" value="${cate.id}">
                                                <label for="category-${cate.id}">
                                                    <span></span>
                                                    ${cate.name}
                                                </label>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>

                            <!-- Filter theo giá -->
                            <div class="aside">
                                <h3 class="aside-title">Mức giá</h3>
                                <div class="checkbox-filter">
                                    <div class="input-checkbox">
                                        <input type="checkbox" id="price-0-5" name="price[]" value="0-5">
                                        <label for="price-0-5">
                                            <span></span>
                                            0 - 5 triệu
                                        </label>
                                    </div>
                                    <div class="input-checkbox">
                                        <input type="checkbox" id="price-5-10" name="price[]" value="5-10">
                                        <label for="price-5-10">
                                            <span></span>
                                            5 - 10 triệu
                                        </label>
                                    </div>
                                    <div class="input-checkbox">
                                        <input type="checkbox" id="price-10+" name="price[]" value="10+">
                                        <label for="price-10+">
                                            <span></span>
                                            Trên 10 triệu
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <!-- Filter theo dung lượng -->
                            <div class="aside">
                                <h3 class="aside-title">Dung Lượng</h3>
                                <div class="checkbox-filter">
                                    <c:forEach items="${listsize}" var="s">
                                        <div class="input-checkbox">
                                            <input type="checkbox" id="size-${s.id}" name="size[]" value="${s.id}">
                                            <label for="size-${s.id}">
                                                <span></span>
                                                ${s.memorySize}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <!-- Filter theo màu sắc -->
                            <div class="aside">
                                <h3 class="aside-title">Màu Sắc</h3>
                                <div class="checkbox-filter">
                                    <c:forEach items="${listColors}" var="color">
                                        <div class="input-checkbox">
                                            <input type="checkbox" id="color-${color.id}" name="color[]" value="${color.id}">
                                            <label for="color-${color.id}">
                                                <span></span>
                                                ${color.color}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <input type="hidden" name="service" value="filterproduct"/>

                            <!-- Nút áp dụng bộ lọc -->
                            
                        </div>
                    </form>



                    <!-- /ASIDE -->

                    <!-- STORE -->
                    <div id="store" class="col-md-9">
                        <!-- store top filter -->
                        <div class="store-filter clearfix">
                            <div class="store-sort">
                                <label>

                                    <ul>
                                        <li><a href="shopdetails?service=sortbyasc">Giá Tăng Dần</a></li>
                                        <li><a href="shopdetails?service=sortbydsc">Giá Giảm Dần</a></li>

                                    </ul>




                                </label>


                            </div>

                        </div>
                        <!-- /store top filter -->

                        <!-- store products -->



                        <!-- /product -->
                        <c:forEach var="p" items="${listproduct}">
                            <!-- Tìm sản phẩm tương ứng với variant -->


                            <div class="col-md-4 col-xs-6">
                                <div class="product">
                                    <div class="product-img">
                                        <img src="${pageContext.request.contextPath}${p.avatar}" alt="${variant.product.name}" style="width: 253px;height: 172px">
                                        
                                    </div>
                                    <div class="product-body">
                                        <p class="product-category">${p.category.name }</p>
                                        <h5 class="product-name"><a href="shopdetails}">${p.name}</a></h5>
                                        
                                        <div class="product-rating">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-o"></i>
                                        </div>
                                        
                                    </div>
                                    
                                </div>
                            </div>
                            <!-- /product -->




                        </c:forEach>
                        <!-- product -->















                    </div>
                    <!-- /store products -->



                    <!-- store bottom filter -->
                    <div class="store-filter clearfix">
                        <span class="store-qty">Showing ${currentPage} of ${totalPages} pages</span>
                        <ul class="store-pagination">
                            <c:if test="${currentPage > 1}">
                                <li><a href="shopdetails?page=${currentPage - 1}"><i class="fa fa-angle-left"></i></a></li>
                                    </c:if>
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="${i == currentPage ? 'active' : ''}"><a href="shopdetails?page=${i}">${i}</a></li>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                <li><a href="shopdetails?page=${currentPage + 1}"><i class="fa fa-angle-right"></i></a></li>
                                    </c:if>
                        </ul>
                    </div>

                    <!-- /store bottom filter -->
                </div>
                <!-- /STORE -->
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /SECTION -->



    <!-- FOOTER -->
    <%@include file="../layout/footer.jsp" %>
    <!-- /FOOTER -->

    <!-- jQuery Plugins -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min_1.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/slick.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/nouislider.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.zoom.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/main1.js"></script>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            document.querySelectorAll(".add-to-wishlist a").forEach(link => {
                link.addEventListener("click", function (event) {
                    event.preventDefault(); // Ngăn chặn điều hướng ngay lập tức
                    const url = this.href;

                    // Hiển thị hộp thoại xác nhận
                    const isConfirmed = confirm("Bạn có muốn thêm sản phẩm này vào wishlist không?");

                    if (isConfirmed) {
                        console.log(`Đang chuyển hướng đến: ${url}`);
                        window.location.href = url; // Điều hướng đến wishlist
                    } else {
                        console.log("Hủy thêm vào wishlist.");
                    }
                });
            });
        });



    </script>





</body>
</html>

