<%@ page import="model.Model" %>
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

<div class="container">
    <h1 class="text-center">Data</h1>
    <div id="<%=list.name%>">
        <hr style="color: darkred;">
        <table class="table table-bordered table-striped text-center">
            <thead style="background-color: #a6b3ec;">
            <tr>
                <th colspan="5" style="min-width: 200px;" class="text-center"><a href="viewList.html?listName=<%=list.name%>" style="color:rebeccapurple; font-size: large"><%=list.name%></a></th>
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
                for (Item item : list.items){
            %>
            <tr>
                <td><a style="color: black" href="viewItem.html?listName=<%=list.name%>&itemName=<%=item.name%>"><%=item.name%></a></td>
                <td><%=item.text%></td>
                <td><a href="<%=item.url%>" target="_blank"><%=item.url%></a></td>
                <td>
                    <% if (!(item.image).equals("")) {%>
                    <a href="viewImage.html?listName=<%=list.name%>&itemName=<%=item.name%>" target="_blank"><img style="max-width: 100%; max-height: 75px;" src="<%=item.image%>"/></a>
                    <%}%>
                </td>
                <td><a href="viewListData.html?listName=<%=item.listLink%>"><%=item.listLink%></a></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
