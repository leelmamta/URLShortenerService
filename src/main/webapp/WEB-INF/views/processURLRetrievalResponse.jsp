<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Original Redirected Response is </title>
</head>
<body>
    <h2>URL Shortening Result</h2>

    <p><strong>Original URL:</strong> ${tinyURL}</p>
    <p><strong>Shortened URL:</strong>${originalURL}</p>
    <br>
    <a href="/ui/showURLRetrievalForm">Shorten Another URL</a>
</body>
</html>