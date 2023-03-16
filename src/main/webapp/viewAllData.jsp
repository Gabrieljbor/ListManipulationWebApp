<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Map<String, Map<String, String[]>> data = (Map<String, Map<String, String[]>>) request.getAttribute("data");
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

    <div class="container">
        <h1 class="text-center">Data</h1>

        <%
            for (String listName : data.keySet()) {
        %>

        <div id="<%=listName%>">
            <hr style="color: darkred;">
            <table class="table table-bordered table-striped text-center">
                <thead style="background-color: #a6b3ec;">
                    <tr>
                        <th colspan="5" style="min-width: 200px;" class="text-center"><a href="viewList.html?listName=<%=listName%>" style="color:rebeccapurple; font-size: large"><%=listName%></a></th>
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
                        for (String itemName : data.get(listName).keySet()){
                    %>
                    <tr>
                        <td><a style="color: black" href="viewItem.html?listName=<%=listName%>&itemName=<%=itemName%>"><%=itemName%></a></td>
                        <td><%=data.get(listName).get(itemName)[0]%></td>
                        <td><a href="<%=data.get(listName).get(itemName)[1]%>" target="_blank"><%=data.get(listName).get(itemName)[1]%></a></td>
                        <td>
                            <% if ((data.get(listName).get(itemName)[2]).equals("img.jpg")) {%>
                            <a href="viewImage.html?listName=<%=listName%>&itemName=<%=itemName%>" target="_blank"><img style="max-width: 100%; max-height: 75px;" src="<%=data.get(listName).get(itemName)[3]%>"/></a>
                            <%}%>
                        </td>
                        <td><a href="viewList.html?listName=<%=data.get(listName).get(itemName)[4]%>"><%=data.get(listName).get(itemName)[4]%></a></td>
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
