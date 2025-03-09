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

        <title>Trang Chủ</title>

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
            .product-label .rating1 {
                background: #ff9800;
                color: #fff;
                padding: 5px 10px;
                border-radius: 5px;
                font-weight: bold;
                font-size: 14px;
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
                display: flex;
                align-items: center;
                gap: 5px;
            }

            .product-label .rating1::before {
                content: "⭐"; /* Biểu tượng ngôi sao */
                font-size: 16px;
            }

        </style>

    </head>
    <body>
        <!-- HEADER -->
        <%@include file="../layout/header.jsp" %>
        <!-- /HEADER -->

        <!-- NAVIGATION -->

        <!-- /NAVIGATION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- shop -->
                    <div class="col-md-4 col-xs-6">

                        <div class="shop-img">
                            <img src="${pageContext.request.contextPath}/assets/images/banner/meo.jpg" style="width: 360px;height: 255px" >
                        </div>


                    </div>
                    <!-- /shop -->

                    <!-- shop -->
                    <div class="col-md-4 col-xs-6">

                        <div class="shop-img">
                            <img src="${pageContext.request.contextPath}/assets/images/banner/samsung.png" style="width: 360px;height: 255px" >
                        </div>


                    </div>
                    <!-- /shop -->

                    <!-- shop -->
                    <div class="col-md-4 col-xs-6">

                        <div class="shop-img">
                            <img src="${pageContext.request.contextPath}/assets/images/banner/anh2.jpg" style="width: 360px;height: 255px" >
                        </div>
                    </div>
                    <!-- /shop -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">Sản phẩm nổi bật</h3>

                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab1" class="tab-pane active">
                                    <div class="products-slick" data-nav="#slick-nav-1">






                                        <c:forEach items="${listvariant}" var="variant">
                                            <c:if test="${variant.product.status==1}">
                                                <a href="product-variant-details?id=${variant.product.id}&size=${variant.size.memorySize}&color=${variant.color.color}">

                                                    <!-- product -->
                                                    <div class="product">
                                                        <div class="product-img">
                                                            <img src="${pageContext.request.contextPath}${variant.product.avatar}" alt="${variant.product.name}" style="width: 253px;height: 253px">

                                                            <div class="product-label">
                                                                <span class="rating1">${variant.product.totalRating}</span>

                                                            </div>
                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">${variant.product.category.name }</p>
                                                            <h5 class="product-name">${variant.product.name}</h5>
                                                            <h5 class="product-name">${variant.size.memorySize}GB${" "}${variant.color.color}</h5>
                                                            <h4 class="product-price">
                                                                <fmt:formatNumber value="${variant.price}" type="currency" currencySymbol="" groupingUsed="true"/> VND 
                                                                <del class="product-old-price">
                                                                    <fmt:formatNumber value="${variant.price + (variant.price * variant.sale / 100)}" type="currency" currencySymbol="" groupingUsed="true"/> VND
                                                                </del>
                                                            </h4>

                                                            <div class="product-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o"></i>
                                                            </div>

                                                        </div>  
                                                        <div class="add-to-cart">
                                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                        </div>
                                                    </div>
                                                </a>
                                            </c:if>
                                            <!-- /product -->
                                        </c:forEach>

                                    </div>
                                    <div id="slick-nav-1" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- HOT DEAL SECTION -->
        <div class="section banner-container">
            <img src="assets/images/banner/banner.jpg" alt="alt" class="full-width-banner">
        </div>
        <!-- /HOT DEAL SECTION -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">HOT SALE</h3>

                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-2">


                                        <c:forEach items="${salevariants}" var="variant">
                                            <c:if test="${variant.product.status==1}">
                                                <a href="product-variant-details?id=${variant.product.id}&size=${variant.size.memorySize}&color=${variant.color.color}">

                                                    <!-- product -->
                                                    <div class="product">
                                                        <div class="product-img">
                                                            <img src="${pageContext.request.contextPath}${variant.product.avatar}" alt="${variant.product.name}" style="width: 253px;height: 253px">
                                                            <div class="product-label">
                                                                <span class="sale">-${variant.sale}%</span>
                                                                <span class="new">NEW</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">${variant.product.category.name }</p>
                                                            <h5 class="product-name">${variant.product.name}</h5>
                                                            <h5 class="product-name">${variant.size.memorySize}GB${" "}</h5>
                                                            <h4 class="product-price">
                                                                <fmt:formatNumber value="${variant.price}" type="currency" currencySymbol="" groupingUsed="true"/> VND 
                                                                <del class="product-old-price">
                                                                    <fmt:formatNumber value="${variant.price + (variant.price * variant.sale / 100)}" type="currency" currencySymbol="" groupingUsed="true"/> VND
                                                                </del>
                                                            </h4>

                                                            <div class="product-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star-o"></i>
                                                            </div>

                                                        </div>  
                                                        <div class="add-to-cart">
                                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                        </div>
                                                    </div>
                                                </a>
                                            </c:if>
                                            <!-- /product -->
                                        </c:forEach>
                                    </div>
                                    <div id="slick-nav-2" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>



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

    </body>
</html>
