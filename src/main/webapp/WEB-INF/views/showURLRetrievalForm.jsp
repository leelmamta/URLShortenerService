<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter the tinyURL to get Original URL </title>
</head>
<body>

    <h2>Add your shortened URL to retrieve the Original URL.</h2>
    <form action="showURLRetrievalForm" method="post">
        <label for="tinyURL">tinyURL:</label>
        <input type="text" id="tinyURL" name="tinyURL" required><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>