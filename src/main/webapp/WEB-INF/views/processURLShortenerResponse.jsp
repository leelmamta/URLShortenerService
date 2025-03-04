<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shortened URL</title>
</head>
<body>
    <h2>URL Shortening Result</h2>

    <p><strong>Original URL:</strong> ${originalURL}</p>
    <p><strong>Shortened URL:</strong> <a href="${shortenedURL}">${shortenedURL}</a></p>

    <%-- Display optional fields only if provided --%>
    <c:if test="${not empty customURL}">
        <p><strong>Custom URL:</strong> ${customURL}</p>
    </c:if>

    <c:if test="${not empty expiryTimeStamp}">
        <p><strong>Expiry Timestamp:</strong> ${expiryTimeStamp}</p>
    </c:if>
    <c:if test="${not empty createdTimeStamp}">
            <p><strong>created Timestamp:</strong> ${createdTimeStamp}</p>
    </c:if>

    <c:if test="${not empty userId}">
        <p><strong>User ID:</strong> ${userId}</p>
    </c:if>

    <br>
    <a href="/ui/showURLShortenerForm">Shorten Another URL</a>
</body>
</html>
