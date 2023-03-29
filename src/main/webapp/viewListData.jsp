<%@ page import="item.Item" %>
<%@ page import="alist.AList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    AList list = (AList) request.getAttribute("list");
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
    <%--This shows the grid layout like on the data page and is when you come from the data page and want to see a list or clicked a link to a list from an item in another list--%>
    <div class="container">
        <h1 class="text-center">Data</h1>
        <div id="<%=list.getName()%>">
            <hr style="color: darkred;">
            <table class="table table-bordered table-striped text-center">
                <thead style="background-color: #a6b3ec;">
                <tr>
                    <th colspan="5" style="min-width: 200px;" class="text-center"><a href="viewList.html?listName=<%=list.getName()%>" style="color:rebeccapurple; font-size: large"><%=list.getName()%></a></th>
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
                    for (Item item : list.getItems()){
                %>
                <tr>
                    <td><a style="color: black" href="viewItem.html?listName=<%=list.getName()%>&itemName=<%=item.getName()%>"><%=item.getName()%></a></td>
                    <td><%=item.getText()%></td>
                    <td><a href="<%=item.getUrl()%>" target="_blank"><%=item.getUrl()%></a></td>
                    <td>
                        <% if (!(item.getImage()).equals("")) {%>
                        <a href="viewImage.html?listName=<%=list.getName()%>&itemName=<%=item.getName()%>" target="_blank"><img style="max-width: 100%; max-height: 75px;" src="<%=item.getImage()%>"/></a>
                        <%}%>
                    </td>
                    <td><a href="viewListData.html?listName=<%=item.getListLink()%>"><%=item.getListLink()%></a></td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
