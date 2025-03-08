<%-- 
    Document   : AccessDenied
    Created on : Mar 8, 2025, 12:12:58 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Access Denied</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <style>
            body{
                background-color: black;
                color: white;
                text-align: center;
            }

            h1 {
                color: red;
            }

            h6{
                color: red;
                text-decoration: underline;
            }

            .btn-home {
                margin-top: 20px;
                padding: 10px 20px;
                font-size: 18px;
                color: white;
                background-color: red;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: 0.3s;
            }

            .btn-home:hover {
                background-color: darkred;
            }
        </style>
    </head>
    <body>
        <div class="w3-display-middle">
            <h1 class="w3-jumbo w3-animate-top w3-center"><code>Access Denied</code></h1>
            <hr class="w3-border-white w3-animate-left" style="margin:auto;width:50%">
            <h3 class="w3-center w3-animate-right">You don't have permission to view this site.</h3>
            <h3 class="w3-center w3-animate-zoom">🚫🚫🚫🚫</h3>
            <h6 class="w3-center w3-animate-zoom">Error code: 403 Forbidden</h6>

            <!-- Nút quay về trang chủ -->
            <button class="btn-home" onclick="goHome()">🏠 Go to Home</button>
        </div>

        <script>
            function goHome() {
                window.location.href = "home"; // Thay bằng URL trang chủ của bạn
            }
        </script>
    </body>
</html>
