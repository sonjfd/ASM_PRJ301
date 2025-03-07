<%-- 
    Document   : register
    Created on : Feb 26, 2025, 8:36:59 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import = "model.Users" %>

<!DOCTYPE html>
<!---Coding By CodingLab | www.codinglabweb.com--->
<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
    }
    body {
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 20px;
        background: rgb(130, 106, 251);
    }
    .container {
        position: relative;
        max-width: 700px;
        width: 100%;
        background: #fff;
        padding: 25px;
        border-radius: 8px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    }
    .container header {
        font-size: 1.5rem;
        color: #333;
        font-weight: 500;
        text-align: center;
    }
    .container .form {
        margin-top: 30px;
    }
    .form .input-box {
        width: 100%;
        margin-top: 20px;
    }
    .input-box label {
        color: #333;
    }
    .form :where(.input-box input, .select-box) {
        position: relative;
        height: 50px;
        width: 100%;
        outline: none;
        font-size: 1rem;
        color: #707070;
        margin-top: 8px;
        border: 1px solid #ddd;
        border-radius: 6px;
        padding: 0 15px;
    }
    .input-box input:focus {
        box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1);
    }
    .form .column {
        display: flex;
        column-gap: 15px;
    }
    .form .gender-box {
        margin-top: 20px;
    }
    .gender-box h3 {
        color: #333;
        font-size: 1rem;
        font-weight: 400;
        margin-bottom: 8px;
    }
    .form :where(.gender-option, .gender) {
        display: flex;
        align-items: center;
        column-gap: 50px;
        flex-wrap: wrap;
    }
    .form .gender {
        column-gap: 5px;
    }
    .gender input {
        accent-color: rgb(130, 106, 251);
    }
    .form :where(.gender input, .gender label) {
        cursor: pointer;
    }
    .gender label {
        color: #707070;
    }
    .address :where(input, .select-box) {
        margin-top: 15px;
    }
    .select-box select {
        height: 100%;
        width: 100%;
        outline: none;
        border: none;
        color: #707070;
        font-size: 1rem;
    }
    .form button {
        height: 55px;
        width: 100%;
        color: #fff;
        font-size: 1rem;
        font-weight: 400;
        margin-top: 30px;
        border: none;
        cursor: pointer;
        transition: all 0.2s ease;
        background: rgb(130, 106, 251);
    }
    .form button:hover {
        background: rgb(88, 56, 250);
    }
    /*Responsive*/
    @media screen and (max-width: 500px) {
        .form .column {
            flex-wrap: wrap;
        }
        .form :where(.gender-option, .gender) {
            row-gap: 15px;
        }
    }
</style>
<%
    String mess = (String) request.getAttribute("mess");
%>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <!--<title>Registration Form in HTML CSS</title>-->
        <!---Custom CSS File--->
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <section class="container">
            <header>Registration Form</header>
            <form action="register" class="form" method="POST">
                <div class="input-box">
                    <label>Họ và tên</label>
                    <input type="text" name="name" placeholder="Nhập tên..." required />
                </div>
                <div class="input-box">
                    <label>Email</label>
                    <input type="text" name="email" placeholder="Nhập email..." required />
                </div>

                <div class="input-box">
                    <label>Số điện thoại</label>
                    <input type="number" name="phone" placeholder="Nhập số điện thoại..." required />
                </div>


                <div class="gender-box">
                    <h3>Gender</h3>
                    <div class="gender-option">
                        <input type="radio" id="check-male" name="gender" value="Nam" checked />
                        <label for="check-male">Nam</label>
                        <input type="radio" id="check-female" name="gender" value="Nữ" />
                        <label for="check-female">Nữ</label>

                    </div>
                </div>
                <div class="input-box address">
                    <label>Địa chỉ</label>
                    <input type="text" name="address" placeholder="Nhập địa chỉ..." required />
                </div>
                <div class="input-box address">
                    <label>Tên tài khoản</label>
                    <input type="text" name="account" placeholder="Nhập tên tài khoản..." required />

                </div>
                <% if (mess != null && !mess.isEmpty()) { %>
                <p style="color: red;"><%= mess %></p>
                <% } %>
                <div class="input-box address">
                    <label>Mật khẩu</label>
                    <input type="number" name="password" placeholder="Nhập mật khẩu..." required />
                </div>

                <button type="submit"class="btn btn-primary">Submit</button>

            </form>
        </section>
    </body>
</html>