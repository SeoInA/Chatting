<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        background: #f8f8f8;
        padding: 60px 0;
    }
    
    #login-form > div {
        margin: 15px 0;
    }

</style>
</head>

<body>
<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title">환영합니다!</div>
            </div>
            <div class="panel-body">
                <form id="login-form" action="<%=request.getContextPath()%>/login/loginProcess" method="post" >
                    <div>
                        <input type="text" class="form-control" name="nick" placeholder="Username" autofocus>
                    </div>
                    <div>
                        <input type="password" class="form-control" name="userpw" placeholder="Password">
                    </div>
                    <div>
                        <button type="submit" class="form-control btn btn-primary">로그인</button>
                    </div>
                </form>
                <%-- <p>
                      <a href="<%=request.getContextPath()%>/login/userform">아직 회원이 아니신가요?</a>
                </p> --%>
            </div>
        </div>
    </div>
</div>

</body>
</html>