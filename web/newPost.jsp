<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <title>New Post</title>



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
        <h1 align="Center">Create a new post</h1>

        <script language="javascript">
            var today = new Date();
            document.write(today);
        </script>
        <br><br><br>
        <button type="button" onclick="location.href = '/ProjectBlog/logout.jsp'">Logout</button>
        <br><br><br>

        <form action="new_post" method="POST">
            Topic:
            <input type="text" name="topic"><br><br><br>

            <div align="Left">
                Enter descriptions<br><br>
                <textarea cols="80" rows="20" name="content"></textarea>
            </div>
            <input type="submit" value="Submit">
        </form>
        <br><br>


    </body>
</html>