<%-- 
    Document   : editpost
    Created on : May 10, 2015, 1:38:09 PM
    Author     : Supun Athukorala
--%>

<%@page import="java.util.Iterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Post</title>
    </head>
    <body>
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
        <h1 align="Center">Edit post</h1>

        <script language="javascript">
        </script>
        <br><br><br>
        <%

            String result = "Nothing";

            JSONObject jsonObject = (JSONObject) request.getAttribute("editingPost");
            String titleName = (String) jsonObject.get("topic");
            String postContent = (String) jsonObject.get("content");
            String postNo = (String) jsonObject.get("post No");
            JSONArray comments = (JSONArray) jsonObject.get("Comments List");


        %>

        <button type="button" onclick="location.href = '/ProjectBlog/logout.jsp'">Logout</button>
        <br><br><br>
        <p> Post No : <%=postNo%> </p>
        <form action="editPost" method="POST">
            <input type="hidden" name="id" value=<%=postNo%>>
            <input type="hidden" name="condition" value="save">

            Topic:
            <input type="text" name="topic" value="<%  out.println(titleName); %>"><br><br><br>

            <div align="Left">
                Enter descriptions<br><br>
                <textarea cols="80" rows="20" name="content" value=""><% out.println(postContent); %></textarea>
            </div>

            <%
                Iterator<JSONObject> iterator = comments.iterator();
                int countOfComments = 0;
                while (iterator.hasNext()) {
                    JSONObject commentObject = iterator.next();

                    out.println("<P>" + commentObject.get("content") + "</p>");

                    if (commentObject.get("approve").equals("false")) {
            %> 
            <p><input type="checkbox" name="commentApprove" value="<%=countOfComments%>" checked>Approve   
                <br>

                <%
                        }
                        countOfComments++;
                    }
                    if (comments.size() == 0) {
                        out.println("<P>" + "No Comments:" + "</p>");
                    }
                %>

                <input type="submit" value="Submit">
        </form>


        <br><br>


    </body>
</html>
