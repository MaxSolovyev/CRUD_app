<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%--<%--%>
    <%--String firstname = request.getParameter("firstName");--%>
<%--%>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User menu</title>
    <%--<link rel="stylesheet" href="css/styleAdmin.css">--%>
</head>
<body>

<c:if test="${update == 1}">
    <form class="form-horizontal" action='/edit' method="POST">
</c:if>
<c:if test="${update != 1}">
    <form class="form-horizontal" action='/register' method="POST">
</c:if>

    <fieldset>

        <c:if test="${update == 1}">
            <div class="control-group">
                <!-- Name -->
                <label class="control-label" for="id">code</label>
                <div class="controls">
                    <input type="text" id="id" name="id" value="${user.id}" placeholder="" class="input-xlarge">
                </div>
            </div>
        </c:if>

        <div class="control-group">
            <!-- Name -->
            <label class="control-label" for="name">name</label>
            <div class="controls">
                <input type="text" id="name" name="name" value="${user.name}" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- Login -->
            <label class="control-label"  for="login">login</label>
            <div class="controls">
                <input type="text" id="login" name="login" value="${user.login}" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- Password-->
            <label class="control-label" for="password">password</label>
            <div class="controls">
                <input type="password" id="password" name="password" value="${user.password}" placeholder="" class="input-xlarge">
            </div>
        </div>

        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button type="submit" class="btn btn-success">
                    <c:if test="${update == 1}">
                        update
                    </c:if>
                    <c:if test="${update != 1}">
                        add User
                    </c:if>
                </button>
            </div>
        </div>
    </fieldset>
</form>

<div class="container">
    <div class="row">

        <div class="col-md-10">
            <br><br>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thread>
                        <tr class="active">
                            <th>id</th>
                            <th>name</th>
                            <th>login</th>
                            <th>password</th>
                            <th>control</th>
                        </tr>
                    </thread>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.login}</td>
                            <td>${user.password}</td>
                            <td align="center">
                                <div class="btn-group">
                                    <a href="/edit?id=${user.id}"><button type="button" class="btn btn-primary">Edit</button></a>
                                    <a href="/delete?id=${user.id}"><button type="button" class="btn btn-primary">Delete</button></a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>