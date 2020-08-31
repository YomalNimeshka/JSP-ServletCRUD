<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 8/20/2020
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        .register{
            background: -webkit-linear-gradient(left, #3931af, #00c6ff);
            margin-top: 3%;
            padding: 3%;
        }
        .register-left{
            text-align: center;
            color: #fff;
            margin-top: 4%;
        }
        .register-left input{
            border: none;
            border-radius: 1.5rem;
            padding: 2%;
            width: 60%;
            background: #f8f9fa;
            font-weight: bold;
            color: #383d41;
            margin-top: 30%;
            margin-bottom: 3%;
            cursor: pointer;
        }
        .register-right{
            background: #f8f9fa;
            border-top-left-radius: 10% 50%;
            border-bottom-left-radius: 10% 50%;
        }
        .register-left img{
            margin-top: 15%;
            margin-bottom: 5%;
            width: 25%;
            -webkit-animation: mover 2s infinite  alternate;
            animation: mover 1s infinite  alternate;
        }
        @-webkit-keyframes mover {
            0% { transform: translateY(0); }
            100% { transform: translateY(-20px); }
        }
        @keyframes mover {
            0% { transform: translateY(0); }
            100% { transform: translateY(-20px); }
        }
        .register-left p{
            font-weight: lighter;
            padding: 12%;
            margin-top: -9%;
        }
        .register .register-form{
            padding: 10%;
            margin-top: 10%;
        }
        .btnRegister{
            float: right;
            margin-top: 10%;
            border: none;
            border-radius: 1.5rem;
            padding: 2%;
            background: #0062cc;
            color: #fff;
            font-weight: 600;
            width: 50%;
            cursor: pointer;
        }
        .register .nav-tabs{
            margin-top: 3%;
            border: none;
            background: #0062cc;
            border-radius: 1.5rem;
            width: 28%;
            float: right;
        }
        .register .nav-tabs .nav-link{
            padding: 2%;
            height: 34px;
            font-weight: 600;
            color: #fff;
            border-top-right-radius: 1.5rem;
            border-bottom-right-radius: 1.5rem;
        }
        .register .nav-tabs .nav-link:hover{
            border: none;
        }
        .register .nav-tabs .nav-link.active{
            width: 100px;
            color: #0062cc;
            border: 2px solid #0062cc;
            border-top-left-radius: 1.5rem;
            border-bottom-left-radius: 1.5rem;
        }
        .register-heading{
            text-align: center;
            margin-top: 8%;
            margin-bottom: -15%;
            color: #495057;
        }
        .a{
            color: white;
        }


    </style>
    <script>
        function visibilty() {
            var visibiltyToggle = document.getElementById("password");
            if (visibiltyToggle.type === "password"){
                visibiltyToggle.type="text";
            }else{
                visibiltyToggle.type="password";
            }
            
        }


    </script>
</head>
<body>
<%--<h1>Register as User</h1>
<form action="../registerUserServlet" method="post">
    User Name: <input type="text" name="user-name">
    Mobile Number: <input type="text" name="mobile-number">
    Gender:
    <select name="user-gender">
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>
    User Password: <input type="password" name="user-password">
    <button type="submit" name="add-user">Register</button><br/><br/>

    Already have a account?
    <a href="jspFiles/login.jsp">Login</a>--%>


<div class="container register" id="register">
    <div class="row">
        <div class="col-md-3 register-left">

        </div>
        <div class="col-md-9 register-right">

            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h3 class="register-heading">Register Now</h3>
                    <div class="row register-form">
                        <div class="col-md-6">
                            <form action="../registerUserServlet" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control"  maxlength="15"   name="user-name" placeholder=" Name *" value="" required/>
                                </div>
                                <div class="form-group">
                                    <input type="text"  maxlength="10" minlength="10" name="mobile-number" class="form-control" placeholder="Your Phone *" value="" pattern="[0-9]+" required/>
                                </div>


                                <div class="form-group">
                                    <select class="form-control" name="user-gender" id="genderSelection" required >
                                        <option class="hidden"  value="" selected disabled>Please select your Gender</option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                    </select>
                                </div>

                                <div class="form-group">

                                    <span class="form-control">
                                        Password must contain at least 1 number, 1 uppercase, 1 lowercase and should be at least 7 characters long.
                                    </span>
                                    <input type="password" id="password" class="form-control" name="user-password" placeholder="Password *" value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,}" required />
                                    <input type="checkbox" onclick="visibilty()">Show Password
                                </div>


                                <input type="submit" class="btnRegister" name=""  value="Register"/>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>
