<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 8/21/2020
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        body{

            background-repeat:no-repeat;
            background-size: 100%;
        }

        footer{

            margin-top: 20px;
            padding-top: 20px;
        }
        .bg__card__navbar{
            background-color: #000000;
        }
        .bg__card__footer{
            background-color: #000000 !important;
        }
        #add__new__list{
            top: -38px;
            right: 0px;
        }
        #logout_acc{
            top: -38px;
            right: 0px;
        }

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

</head>
<body>

<!---->
<main>
    <div class="container my-5">
        <div class="card-body text-center">
            <h4 class="card-title">Online Accounts</h4>
        </div>
        <div class="card">


    <form action="logoutServlet" method="post">
        <input type="submit" class="btn btn-danger" name="add-user"  id="logout_acc" value="Log Out"/>
    </form>


            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Mobile Number</th>
                    <th scope="col">Gender</th>
                    <th scope="col">Edit List </th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="listOfAcc" items="${listOfAcc}">

                    <tr>
                        <td scope="row"><c:out value="${listOfAcc.userName}"/></td>
                        <td><c:out value="${listOfAcc.mobileNumber}"/></td>
                        <td><c:out value="${listOfAcc.gender}"/></td>
                        <td>
                            <a class="btn btn-sm btn-primary" href="editAccountServlet?userName=<c:out value="${listOfAcc.userName}"/>"><i class="far fa-edit"></i> edit</a>
                            <a class="btn btn-sm btn-danger" href="deleteAccountServlet?userName=<c:out value="${listOfAcc.userName}"/>"><i class="fas fa-trash-alt"></i> delete</a>
                        </td>

                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

   <%-- &lt;%&ndash;add new account dashboard&ndash;%&gt;
    <div id="addnewAcc" class="modal fade">
        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">

                </div>
                <div class="col-md-9 register-right">

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <h3 class="register-heading">Add a new Account</h3>
                            <div class="row register-form">
                                <div class="col-md-6">
                                    <form action="addNewAccountServlet" method="post">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="user-name" placeholder=" Name *" value="" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text"  maxlength="10" name="mobile-number" class="form-control" placeholder="Your Phone *" value=""  required/>
                                        </div>

                                        <div class="form-group">
                                            <select class="form-control" name="user-gender">
                                                <option class="hidden"  selected disabled>Please select your Gender</option>
                                                <option value="male">Male</option>
                                                <option value="female">Female</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <input type="password" class="form-control" name="user-password" placeholder="Password *" value="" required />
                                        </div>

                                        <input type="submit" class="btnRegister" name="add-user"  value="Register"/>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </div>

    &lt;%&ndash;logout option in the dashboard&ndash;%&gt;
    <div id="logout" class="modal fade">
        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">

                </div>
                <div class="col-md-9 register-right">

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <h3 class="register-heading">Log out from the Account ?</h3>
                            <div class="row register-form">
                                <div class="col-md-6">
                                    <form action="logoutServlet" method="post">
                                        <input type="submit" class="btnRegister" name="add-user"  value="Log Out"/>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </div>--%>
</main>



</body>
</html>