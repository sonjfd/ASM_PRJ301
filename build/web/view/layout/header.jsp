<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - HTML Ecommerce Template</title>

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
            /* Căn chỉnh danh sách sang bên phải */
            .pull-right {
                float: right;
                list-style: none;
                margin: 0;
                padding: 0;
            }
            
            


            /* Kiểu dáng avatar */
            .avatar-img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                object-fit: cover;
                border: 2px solid #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                margin-right: 10px;
                vertical-align: middle;
            }

            /* Kiểu dáng chào người dùng */
            .user-greeting {
                font-size: 16px;
                font-weight: bold;
                color: #cccccc; /* Màu đỏ tươi */
                padding: 5px 10px;

                border-radius: 8px;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
                display: inline-block;
                transition: all 0.3s ease-in-out;
            }

            .user-greeting:hover {
                background: linear-gradient(45deg, #ff758c, #ff7eb3);
                color: white;
                transform: scale(1.05);
            }

            /* Kiểu dáng dropdown */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown a {
                text-decoration: none;
                display: flex;
                align-items: center;
                padding: 10px;
                border-radius: 8px;
                transition: 0.3s;
            }

            .dropdown a:hover {
                background: rgba(255, 255, 255, 0.2);
            }

            /* Menu dropdown */
            .dropdown-content {
                display: none;
                position: absolute;
                right: 0;
                background: white;
                min-width: 180px;
                border-radius: 8px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                z-index: 1000;
            }

            .dropdown-content a {
                display: block;
                color: #333;
                padding: 10px;
                text-decoration: none;
                transition: background 0.3s;
            }

            .dropdown-content a:hover {
                background: #f5f5f5;
            }

            /* Hiển thị dropdown khi hover */
            .dropdown:hover .dropdown-content {
                display: block;
            }

            /* Nút đăng nhập */
            .user-name {
                font-size: 16px;
                font-weight: bold;
                color: #3498db; /* Màu xanh nhẹ */
                transition: color 0.3s;
            }

            .user-name:hover {
                color: #2980b9;
            }

        </style>

    <body>
        <!-- HEADER -->
        <header>
            <!-- TOP HEADER -->
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> 0379798606</a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i> dhFpt@fpt.edu.vn</a></li>
                        <li><a href="#"><i class="fa fa-map-marker"></i> Đại học Fpt</a></li>
                    </ul>
                    <ul class=" pull-right">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <c:set var="user" value="${sessionScope.user}" />

                                <li class="dropdown">
                                    <a href="profile" class="user-info">
                                        <c:choose>
                                            <c:when test="${not empty user.avatar}">
                                                <img src="${user.avatar}" alt="Avatar" class="avatar-img" />
                                            </c:when>
                                            <c:otherwise>
                                                <img src="avatar.png" alt="Default Avatar" class="avatar-img" />
                                            </c:otherwise>
                                        </c:choose>
                                        <i class="user-greeting">Hi, ${user.account}</i>

                                    </a>

                                    <!-- Dropdown Menu -->
                                    <div class="dropdown-content">
                                        <a href="profile">👤 Trang cá nhân</a>
                                        <a href="#">🛒 Đơn hàng</a>
                                        <a href="#">🏠 Địa chỉ</a>
                                        <c:if test="${user.role == 'admin'}">
                                            <a href="admin">🛠️ Quản trị</a>
                                        </c:if>
                                        <a href="logout">🚪 Đăng xuất</a>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise>

                                <li>
                                    <a href="login" style="color:  #fff;"><i class="user-name"></i> Đăng nhập</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="home" class="logo">
                                    <img src="${pageContext.request.contextPath}/assets/images/logo/logo.jpg" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="headersearch" method="get">
                                    <div class="search-container">
                                        <select class="input-select" id="categorySelect">

                                            <c:forEach items="${listcategori}" var="c">
                                                <c:if test="${c.status==1}">
                                                    <option value="${c.id}">${c.name}</option>
                                                </c:if>
                                            </c:forEach>

                                        </select>
                                        <input class="input" name="name"  placeholder="Tìm kiếm....">
                                        <button class="search-btn">Tìm kiếm</button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <script>
                            document.getElementById("categorySelect").addEventListener("change", function () {
                                var cid = this.value;
                                if (cid) {
                                    window.location.href = "shopdetails?service=fillterbycid&cid=" + cid;
                                }
                            });
                        </script>

                        <!-- /SEARCH BAR -->

                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <c:set var="wishlist" value="${sessionScope.listvariant}" />
                            <c:set var="totalWishlist" value="${wishlist != null ? wishlist.size() : 0}" />
                            <div class="header-ctn">
                                <!-- Wishlist -->
                                <div>
                                    <a href="wishlist">
                                        <i class="fa fa-heart-o"></i>
                                        <span>Danh sách yêu thích</span>
                                        <div class="qty">${totalWishlist}</div> 
                                    </a>
                                </div>
                                <!-- /Wishlist -->

                                <!-- Cart -->
                                <div class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">3</div>
                                    </a>
                                    <div class="cart-dropdown">
                                        <div class="cart-list">
                                            <div class="product-widget">
                                                <div class="product-img">
                                                    <img src="./img/product01.png" alt="">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-name"><a href="#">product name goes here</a></h3>
                                                    <h4 class="product-price"><span class="qty">1x</span>$980.00</h4>
                                                </div>
                                                <button class="delete"><i class="fa fa-close"></i></button>
                                            </div>

                                            <div class="product-widget">
                                                <div class="product-img">
                                                    <img src="./img/product02.png" alt="">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-name"><a href="#">product name goes here</a></h3>
                                                    <h4 class="product-price"><span class="qty">3x</span>$980.00</h4>
                                                </div>
                                                <button class="delete"><i class="fa fa-close"></i></button>
                                            </div>
                                        </div>
                                        <div class="cart-summary">
                                            <small>3 Item(s) selected</small>
                                            <h5>SUBTOTAL: $2940.00</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="#">View Cart</a>
                                            <a href="#">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Cart -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>
        <!-- /HEADER -->

        <nav id="navigation">
            <!-- container -->
            <div class="container">
                <!-- responsive-nav -->
                <div id="responsive-nav">
                    <!-- NAV -->
                    <ul class="main-nav nav navbar-nav">
                        <li class="active"><a href="home">Trang chủ</a></li>
                        <li><a href="shopdetails">Trang Sản Phẩm</a></li>
                        <li><a href="wishlist">Sản Phẩm ưa thích</a></li>

                    </ul>
                    <!-- /NAV -->
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
    </body>