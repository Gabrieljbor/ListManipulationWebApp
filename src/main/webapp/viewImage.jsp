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
    <%--To view the image bigger, if you click on the image then this page pops up--%>
    <jsp:include page="header.jsp" />
    <h1 class="text-center">Photo for <%=item.getName()%> in <%=item.getList()%></h1>
    <div class="pad">
        <div class="text-center">
            <img src="<%=item.getImage()%>" class="img-fluid"/>
        </div>
    </div>
</body>
</html>
