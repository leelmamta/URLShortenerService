<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>URL Shortener Form</title>
</head>
<body>
    <h2>Enter the entries for URL shortening to shorten with custom add the CustomURL of your choice </h2>
    <form action="/ui/processURLShortenerResponse" method="post">
        <label for="longURL">longURL:</label>
        <input type="text" id="longURL" name="longURL" required><br><br>

        <label for="customURL">shortenedURL</label>
        <input type="text" id="customURL" name="customURL" ><br><br>

        <label for="expiryTimeStamp">expiryTimeStamp:</label>
        <input type="text" id="expiryTimeStamp" name="expiryTimeStamp" ><br><br>

        <label for="userId">userId:</label>
        <input type="text" id="userId" name="userId" ><br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>