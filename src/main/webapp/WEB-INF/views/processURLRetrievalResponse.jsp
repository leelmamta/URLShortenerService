<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Original Redirected Response is </title>
</head>
<body>

    <h2>Here is your Original URL </h2>
    <form action="ui/processURLRetrievalResponse" method="post">
        <label for="longURL">longURL:</label>
        <input type="text" id="longURL" name="longURL" required><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>