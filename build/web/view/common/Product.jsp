<%-- 
    Document   : Product
    Created on : Mar 2, 2025, 11:40:33 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Trang Mua Hàng</title>

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

    </head>
    <style>
        #product-main-img {
          
             pointer-events: none; 
        }

        #product-main-img img {
            
             pointer-events: none; 
        }


    </style>

    <body>
        <%@include file="../layout/header.jsp" %>





        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- Product main img -->
                    <div class="col-md-5 col-md-push-2">
                        <div id="product-main-img">
                            <div class="product-preview">
                                <img src="${pageContext.request.contextPath}${selectedVariant.product.avatar}" alt="${listvariant[0].product.name}" >

                            </div>


                        </div>
                    </div>
                    <!-- /Product main img -->

                    <!-- Product thumb imgs -->
                    <div class="col-md-2  col-md-pull-5">
                        <div id="product-imgs">

                            <c:forEach items="${img}" var="i">
                                <c:if test="${i.product.id == param.id}">
                                    <div class="product-preview">
                                        <img src="${pageContext.request.contextPath}${i.path}" alt="${i.product.id}" >
                                    </div>
                                </c:if>

                            </c:forEach> 



                        </div>
                    </div>
                    <!-- /Product thumb imgs -->

                    <!-- Product details -->
                    <div class="col-md-5">
                        <c:if test="${selectedVariant.product.status==1}">
                        <div class="product-details">
                            <h1 >${selectedVariant.product.name}</h1>

                            <div>
                                <div class="product-rating">
                                    <i class="fa fa-star">
                                        <fmt:formatNumber value="${selectedVariant.product.totalStars / selectedVariant.product.totalRating}" type="number" maxFractionDigits="2" />
                                    </i>
                                </div>

                                <a class="review-link" href="#">${selectedVariant.product.totalRating} Review(s) </a>
                            </div>

                            <div>
                                <h3 class="product-price">
                                    <fmt:formatNumber type="currency" value="${selectedVariant.price}" currencySymbol="$" />
                                    <del class="product-old-price">
                                        <fmt:formatNumber type="currency" value="${selectedVariant.price + (selectedVariant.price * selectedVariant.sale / 100)}" currencySymbol="$" />
                                    </del>
                                </h3>
                                <span class="product-available">Số lượng: ${selectedVariant.stock}</span>
                            </div>

                                <h3>${selectedVariant.product.description}</h3>

                            <div class="product-options">
                                <form method="GET" action="product-variant-details">
                                    <input type="hidden" name="id" value="${param.id}" />
                                    <label>
                                        Bộ Nhớ
                                        <select class="input-select" name="sizeId" onchange="this.form.submit()">

                                            <c:choose>
                                                <c:when test="${empty selectedSize and not empty listsize}">
                                                    <c:set var="selectedSize" value="${listsize[0].id}" scope="request"/>
                                                </c:when>
                                            </c:choose>

                                            <c:forEach var="size" items="${listsize}">
                                                <option value="${size.id}" 
                                                        <c:if test="${size.id == selectedSize}">selected</c:if>>
                                                    ${size.memorySize}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                    <c:if test="${not empty selectedSize}">

                                        <label>
                                            Màu Sắc
                                            <select class="input-select" name="colorId" onchange="this.form.submit()">
                                                <c:forEach var="color" items="${listColors}">
                                                    <option value="${color.id}" ${color.id == param.colorId ? 'selected' : ''}>
                                                        ${color.color}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </c:if>
                                </form>

                            </div>


                            <div class="add-to-cart">
                                <div class="qty-label">
                                    Số Lượng
                                    <div class="input-number">
                                        <input type="number" value="1">
                                        <span class="qty-up">+</span>
                                        <span class="qty-down">-</span>
                                    </div>
                                </div>
                                <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Thêm Vào Giỏ Hàng</button>
                            </div>

                            <ul class="product-btns">
                                <li><a href="add-wish-List?id=${selectedVariant.id}"><i class="fa fa-heart-o"></i> add to wishlist</a></li>

                            </ul>

                            <ul class="product-links">
                                <li>Category:</li>
                                <li><a href="#">${selectedVariant.product.category.name}</a></li>
                            </ul>

                            <ul class="product-links">
                                <li>Share:</li>
                                <li><a href="https://www.facebook.com/oquangai.426813?locale=vi_VN"><i class="fa fa-facebook"></i></a></li>

                                <li><a href="https://www.instagram.com/neymarjr/"><i class="fa fa-instagram"></i></a></li>
                            </ul>
                        </div>
                    </div>
                            </c:if>
                    <!-- /Product details -->


                    <!-- Product tab -->
                    <div class="col-md-12">
                        <div id="product-tab">
                            <!-- product tab nav -->
                            <ul class="tab-nav">


                                <li><a data-toggle="tab" href="#tab3">Đánh Giá </a></li>
                            </ul>
                            <!-- /product tab nav -->

                            <!-- product tab content -->
                            <div class="tab-content">



                                <div class="row">
                                    <!-- Rating -->



                                    <div id="reviews">
                                        <ul class="reviews">
                                            <c:forEach var="rating" items="${ratings}">
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">${rating.users.account}</h5>
                                                        <p class="date">${rating.createdAt}</p>
                                                        <div class="review-rating">
                                                            <c:forEach begin="1" end="${rating.numberStars}">
                                                                <i class="fa fa-star"></i>
                                                            </c:forEach>
                                                            <c:forEach begin="${rating.numberStars + 1}" end="5">
                                                                <i class="fa fa-star-o empty"></i>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>${rating.content}</p>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                    <div class="review-form">
                                        <form action="product-variant-details" method="POST">
                                            <input type="hidden" name="id" value="${requestScope.selectedVariant.product.id}" />
                                            <label>Đánh giá của bạn</label>
                                            <select name="numberStars" class="input-select">
                                                <option value="5">★★★★★</option>
                                                <option value="4">★★★★☆</option>
                                                <option value="3">★★★☆☆</option>
                                                <option value="2">★★☆☆☆</option>
                                                <option value="1">★☆☆☆☆</option>
                                            </select>
                                            <textarea name="content" class="input" placeholder="Nhập đánh giá của bạn..."></textarea>
                                            <button class="primary-btn" type="submit">Gửi đánh giá</button>
                                        </form>
                                    </div>





                                </div>
                                <!-- /tab3  -->
                            </div>
                            <!-- /product tab content  -->
                        </div>
                    </div>
                    <!-- /product tab -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->





        <%@include file="../layout/footer.jsp" %>

        <!-- jQuery Plugins -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min_1.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/slick.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/nouislider.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.zoom.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main1.js"></script>



    </body>
</html>
