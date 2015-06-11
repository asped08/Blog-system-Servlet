<%-- 
    Document   : login-failed
    Created on : May 13, 2015, 5:24:19 PM
    Author     : Supun Athukorala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login Failed</title>
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
            
        </style>
        <p>
            Sorry, login failed!
        </p>
        <button type="button" onclick="location.href = 'WelcomeHome'">Go to Home</button>
    </body>
</html>
