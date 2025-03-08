<%-- 
    Document   : login
    Created on : Feb 27, 2025, 8:45:01 PM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->

<style>
    /* Import Google font - Poppins */
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }
    body{
        min-height: 100vh;
        width: 100%;
        background: #009579;
    }
    .container{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        max-width: 430px;
        width: 100%;
        background: #fff;
        border-radius: 7px;
        box-shadow: 0 5px 10px rgba(0,0,0,0.3);
    }
    .container .registration{
        display: none;
    }
    #check:checked ~ .registration{
        display: block;
    }
    #check:checked ~ .login{
        display: none;
    }
    #check{
        display: none;
    }
    .container .form{
        padding: 2rem;
    }
    .form header{
        font-size: 2rem;
        font-weight: 500;
        text-align: center;
        margin-bottom: 1.5rem;
    }
    .form input{
        height: 60px;
        width: 100%;
        padding: 0 15px;
        font-size: 17px;
        margin-bottom: 1.3rem;
        border: 1px solid #ddd;
        border-radius: 6px;
        outline: none;
    }
    .form input:focus{
        box-shadow: 0 1px 0 rgba(0,0,0,0.2);
    }

    .form a:hover{
        text-decoration: underline;
    }
    .form input.button{
        color: #fff;
        background: #009579;
        font-size: 1.2rem;
        font-weight: 500;
        letter-spacing: 1px;
        margin-top: 1.7rem;
        cursor: pointer;
        transition: 0.4s;
    }
    .form input.button:hover{
        background: #006653;
    }
    .signup{
        font-size: 17px;
        text-align: center;
    }
    .signup label{
        color: #009579;
        cursor: pointer;
    }
    .signup label:hover{
        text-decoration: underline;
    }
    .google-login {
        margin-top: 1rem;
    }
    .google-button {
        display: inline-block;
        color: #fff;
        background: #d9534f;
        font-size: 1rem;
        font-weight: 500;
        letter-spacing: 1px;
        cursor: pointer;
        transition: 0.4s;
        width: 80%;
        height: 40px;
        border-radius: 6px;
        line-height: 40px;
        text-decoration: none;
        text-align: center;
    }
    .google-button:hover {
        background: #c9302c;
        text-decoration: none;
    }


</style>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>DHS Shop</title>
        <!---Custom CSS File--->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
    </head>
    <body>
        <div class="container">
            <input type="checkbox" id="check">
            <div class="login form">
                <header>Đăng nhập</header>
                <form action="login" method="POST">
                    <h3 style="color:red">${requestScope.erro}</h3>
                    <input type="text" name="account" placeholder="Tên đăng nhập">
                    <input type="password" name="password" placeholder="Mật khẩu">
                    <a href="#">Quên mật khẩu?</a>

                    <div class="g_id_signin" data-type="standard"></div>
                    <input type="submit" class="button" value="Login">
                </form>

                <div class="signup">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8080/PRJ301/login&response_type=code
		   &client_id=825860550612-nl9772482dv1aa733ki7834ba24q0k43.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
                </div>

                <div class="signup" >
                    <span class="signup">
                        <a href="register" for="check">Đăng kí tài khoản</a>
                    </span>
                </div>
            </div>

        </div>
    </body>
</html>
