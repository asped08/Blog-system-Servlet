<%-- 
    Document   : viewpost
    Created on : May 10, 2015, 5:19:43 PM
    Author     : Supun Athukorala
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head><title>Your post Results</title></head>
    <body >
        <style>
            body {
                background-color: #d0e4fe;
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

        <h1 align="Center">University Blogging System</h1>
        <h2 align="Center"></h2>
        <%--
       show the result here by extracting the attribute you set
        --%>

        <script language="javascript">
            var today = new Date();
            document.write(today);
            }

        </script>

        <%
            JSONObject jsonObject = (JSONObject) request.getAttribute("ViewPost");
            String titleName = (String) jsonObject.get("topic");
            String postContent = (String) jsonObject.get("content");
            String postNo = (String) jsonObject.get("post No");
            JSONArray comments = (JSONArray) jsonObject.get("Comments List");
            String postId = "editPost?id=" + postNo + "&condition=edit";

            String postHitCount = (String) jsonObject.get("Page Hit");

            out.println("<P>" + "Post No: " + postNo + "</p>");
            out.println("<h2 align='Center'>" + titleName + "</h2>");
            out.println("<P>" + "Content: " + "</p>" + "<P>" + postContent + "</p>");
            out.println("<P>" + "This page has " + postHitCount + " views" + "</p>");
        %>
        <button type="button" onclick="location.href = '<%= postId%>'">Edit post / Approve Comments</button>
        <%
            out.println("<P>" + "\n <h3>Comments:</h3>" + "</p>");

            Iterator<JSONObject> iterator = comments.iterator();
            int approveComment = 0;
            while (iterator.hasNext()) {
                JSONObject commentObject = iterator.next();

                if (commentObject.get("approve").equals("true")) {

                    out.println("<P>" + commentObject.get("content") + "</p>");
                } else if (commentObject.get("approve").equals("false")) {
                    approveComment++;

                }
            }
            if (approveComment > 0) {
                out.println("<P>" + "No of Comments to approve :" + approveComment + "</p>");
            }
            if (comments.size() == 0) {
                out.println("<P>" + "No Comments:" + "</p>");
            }
            String commentId = postNo;
        %>

        <form action="commentPost" method="POST">
            <input type="hidden" name="post No" value="<%= postNo%>">
            <div align="Left">
                Enter Comments<br><br>
                <textarea cols="80" rows="4" name="Commentcontent"></textarea>
            </div>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
