<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<h1>Hello!</h1>
<div>
    <form action="${pageContext.request.contextPath}/signIn" method="post">
        <input name="login" placeholder="Your login">
        <input type="password" name="pass" placeholder="Your password">
        <input type="submit" value="Send">
    </form>
</div>
</body>
</html>
