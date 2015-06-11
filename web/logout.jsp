<%-- 
    Document   : logout
    Created on : May 13, 2015, 7:11:24 PM
    Author     : Supun Athukorala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>logout</title>
    </head>
    <body>
        <%
            session.invalidate();
            response.sendRedirect("WelcomeHome");
        %>    
    </body>
</html>
