<%@ page import="item.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Item item = (Item) request.getAttribute("item");
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
    <h1 class="text-center">Photo for <%=item.name%> in <%=item.list%></h1>
    <div class="pad">
        <div class="text-center">
            <img src="<%=item.image%>" class="img-fluid"/>
        </div>
    </div>
</body>
</html>
