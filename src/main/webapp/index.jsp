<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 15/06/2020
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Website</title>
    <link href="resources/css/website.css" type="text/css" rel="stylesheet">
    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>
    <!-- <script src="js/app-ajax.js" type="text/javascript"></script>-->
    <script type="text/javascript" src="resources/js/script.js"></script>

</head>
<body>

<header>
    <div id="logo" onclick="slowScroll('#top')">
        <img alt="cap" src="resources/assets/logo.png" height="425" width="920"/>
    </div>
    <div id="about">
        <a href="AboutUs.jsp" title="AboutUs" <%--onclick="slowScroll('#main')"--%>>About Us</a>

    </div>
</header>
<div id="top">
    <h1>Citadel Of Knowledge</h1>
    <h6>Library for everyone</h6>
</div>
<main>
    <div class="book++Table">
        <h2>Book table</h2>
    </div>
    <div class="book table">
        <table id="bookTable" style="width:100%">
            <thead>
            <tr>
                <th>book id</th>
                <th>book name</th>
                <th>book author</th>
                <th>book was lost</th>
                <th>book was deleted</th>
            </tr>
            </thead>
            <tbody>
            <td></td>
            </tbody>
        </table>
        <div id="bookOptions">
            <br> <input type="button" onclick="addBook()" value="add book">
            <br> <input type="button" onclick="deleteBook()" value="delete book">
            <br><input type="button" onclick="editBook()" value="edit book">
        </div>
    </div>
</main>
<div id="overview">
    <h2>User table</h2>
    <table id="userTable" style="width:100%">
        <thead>
        <tr>
            <th>user id</th>
            <th>user name</th>
            <th>user adress</th>

        </tr>
        </thead>
        <tbody>
        <td></td>
        </tbody>
    </table>
    <%

    %><!--simply java code without printing it on front -->
    <%= " "%> <!-- java code that must return string and it string will print on front -->
    <div class="user table">

    </div>

    <form>
        <label for="userId">Input your id</label>
        <br> <input type="number" name="userId" id="userId" required pattern="[0-9]">
        <!-- <br><label for="unreturned">Show only unreturned book</label>
       <br><input type="checkbox" name="unreturned" id="unreturned">-->
        <br> <input type="button" id="BookHistoryButton" value="отпр">

    </form>
    <table id="bookStoryTable" class="display" style="width:100%">
        <thead>
        <tr>
            <th>book id</th>
            <th> Book name</th>
            <th>Book was taken</th>
            <th>Book was returned</th>

        </tr>
        </thead>
    </table>
</div>
</body>
</html>
