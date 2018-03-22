<html>
<head>
    <link rel="stylesheet" type="text/css" href="/auth.css">
    <title>Login Page</title>
</head>
<body onload='document.f.username.focus();'>
<h3>Login with Username and Password</h3>
<form class="box" name='f' action='/auth' method='POST'>
    <table class="boxBody">
        <tr>
            <td class="rLink">User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td class="rLink">Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input class="btnLogin" name="submit" type="submit" value="Login"/></td>
        </tr>
    </table>
</form>
</body>
</html>