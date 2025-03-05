<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shortened URL</title>
</head>
<body>
    <h2>URL Shortening Result</h2>

    <p><strong>Original URL:</strong> ${longURL}</p>
    <p><strong>Shortened URL:</strong> <a href="${customURL}">${customURL}</a></p>
    <c:if test="${not empty expiryTimeStamp}">
        <p><strong>Expiry Timestamp:</strong> ${expiryTimeStamp}</p>
    </c:if>
    <c:if test="${not empty createdTimeStamp}">
            <p><strong>created Timestamp:</strong> ${createdTimeStamp}</p>
    </c:if>

    <c:if test="${not empty userId}">
        <p><strong>User ID:</strong> ${user_Id}</p>
    </c:if>

    <br>
    <a href="/ui/processURLShortenerResponse">Shorten Another URL</a>
</body>
</html>
