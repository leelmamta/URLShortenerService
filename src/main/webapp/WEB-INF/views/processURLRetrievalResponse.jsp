<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Original Redirected Response is </title>
</head>
<body>
    <h2>URL Shortening Result</h2>

    <p><strong>Original URL:</strong> ${longURL}</p>
    <p><strong>Shortened URL:</strong> <a href="${shortenedURL}">${shortenedURL}</a></p>

    <br>
    <a href="/ui/processURLRetrievalResponse">Shorten Another URL</a>
</body>
</html>