<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String listName = (String) request.getAttribute("listName");
    String itemName = (String) request.getAttribute("itemName");
    String imagePath = (String) request.getAttribute("itemImagePath");
%>

<!DOCTYPE html>
<style>
    img {
        max-width: 75vh;
        max-height: 75vh;
    }
    .pad {
        padding: 25px;
    }
</style>

<html>

<head>
    <jsp:include page="meta.jsp" />
</head>

<body>
    <jsp:include page="header.jsp" />
    <h1 class="text-center">Photo for <%=itemName%> in <%=listName%></h1>
    <div class="pad">
        <div class="text-center">
            <img src="<%=imagePath%>" class="img-fluid"/>
        </div>
    </div>
</body>
</html>
