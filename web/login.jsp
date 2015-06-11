<%-- 
    Document   : login
    Created on : May 13, 2015, 6:34:37 PM
    Author     : Supun Athukorala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <style>
            body {
                background-color: #d0e4fe;
            }

            a{

                font-family: sans-serif;
                font-size: 20px;
            }

            h1 {
                color: blue;
                text-align: center;
            }

            p {
                font-family: "Times New Roman";
                font-size: 20px;
            }
            form {
                font-family: "Times New Roman";
                font-size: 20px;
            }
        </style>
        <form method="POST" action="j_security_check">
            <table>
                <tr>
                    <td colspan="2">Login to the University Blogging System:</td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="j_username" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="j_password"/ ></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Go" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
