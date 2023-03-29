<%@ page import="model.Model" %>
<%@ page import="item.Item" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Model model = (Model) request.getAttribute("data");
%>

<!DOCTYPE html>

<style>
    .container {
        padding-bottom: 50px;
    }
</style>

<html>

<head>
    <jsp:include page="meta.jsp"/>
</head>

<body>
    <jsp:include page="header.jsp"/>

    <%--To view all data of each item stored grouped by list--%>
    <div class="container">
        <h1 class="text-center">Data</h1>

        <%
            for (String listName : model.getListNames()) {
        %>

        <div id="<%=listName%>">
            <hr style="color: darkred;">
            <table class="table table-bordered table-striped text-center">
                <thead style="background-color: #a6b3ec;">
                    <tr>
                        <th colspan="5" style="min-width: 200px;" class="text-center"><a href="viewListData.html?listName=<%=listName%>" style="color:rebeccapurple; font-size: large"><%=listName%></a></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th class="text-center">Item Name</th>
                        <th class="text-center">Item Text</th>
                        <th class="text-center">Item URL</th>
                        <th class="text-center">Item Image</th>
                        <th class="text-center">List Link</th>
                    </tr>
                    <%
                        for (Item item : model.getList(listName).getItems()){
                    %>
                    <tr>
                        <td><a style="color: black" href="viewItem.html?listName=<%=listName%>&itemName=<%=item.getName()%>"><%=item.getName()%></a></td>
                        <td><%=item.getText()%></td>
                        <td><a href="<%=item.getUrl()%>" target="_blank"><%=item.getUrl()%></a></td>
                        <td>
                            <% if (!(item.getImage()).equals("")) {%>
                            <a href="viewImage.html?listName=<%=listName%>&itemName=<%=item.getName()%>" target="_blank"><img style="max-width: 100%; max-height: 75px;" src="<%=item.getImage()%>"/></a>
                            <%}%>
                        </td>
                        <td><a href="viewListData.html?listName=<%=item.getListLink()%>"><%=item.getListLink()%></a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
        <%}%>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
