<%-- 
    Document   : result
    Created on : May 9, 2015, 3:43:54 PM
    Author     : Supun Athukorala
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Your post Results</title></head>
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
    <body>

        <h1 align="Center">University Blogging System</h1>
        <h2 align="Center">Welcome to the Blog</h2>
 
        <script language="javascript">
            var today = new Date();
            document.write(today);
        </script><br><br>
        <button type="button" onclick="location.href = '/ProjectBlog/newPost.jsp'">New Post</button>
        <%
            session = request.getSession(false);
            if (session != null) {
        %>
        <button type="button" onclick="location.href = '/ProjectBlog/logout.jsp'">Logout</button>
        <%              }
        %>
        <a href="/ProjectBlog/newPost.jsp">Create a new post</a>
        <%
            List list1 = new ArrayList();
            list1 = (ArrayList) request.getAttribute("result");

            for (int i = 0; i < list1.size(); i++) {
                JSONObject jsonObject = (JSONObject) list1.get(i);
                String titleName = (String) jsonObject.get("topic");
                String postContent = (String) jsonObject.get("content");
                String postNo = (String) jsonObject.get("post No");
                JSONArray comments = (JSONArray) jsonObject.get("Comments List");

                // out.print("<P>"  + postNo + ": ");
                out.println("<h3>" + postNo + ": " + titleName + "</h3></p>");
                if (postContent.length() > 200) {
                    out.println("<P><u>" + "Content: " + "</u></p>" + "<P>" + postContent.substring(0, 200) + " ... </p>");
                } else {
                    out.println("<P><u>" + "Content: " + "</u></p>" + "<P>" + postContent + " ... </p>");
                }
                String postId = "ViewPost?id=" + postNo;

                Iterator<JSONObject> iterator = comments.iterator();
                int approveComment = 0;
                while (iterator.hasNext()) {
                    JSONObject commentObject = iterator.next();
                    if (commentObject.get("approve").equals("false")) {
                        approveComment++;

                    }
                }
                if (approveComment > 0) {
                    out.println("<P>" + "No of Comments to approve :" + approveComment + "</p>");
                }

        %>
        <button type="button" onclick="location.href = '<%= postId%>'">View Full Post</button>
        <%
                out.println("<P>" + "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- " + "</p>");
            }

            String AllId = "countfile?count=" + "all";
        %>

        <button type="button" onclick="location.href = '<%= AllId%>'">See More</button>
    </body>
</html>
