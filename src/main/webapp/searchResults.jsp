<%@ page import="java.util.List" %>
<%@ page import="item.Item" %>
<%@ page import="alist.AList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String searchQuery = (String) request.getAttribute("searchQuery");
    List<Item> searchResults = (List<Item>) request.getAttribute("itemSearchResults");
    List<AList> matchingLists = (List<AList>) request.getAttribute("matchingLists");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="meta.jsp" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <h1 class="text-center">Search results for: "<%=searchQuery%>"</h1>
    <div class="col-10 offset-1 table-responsive table-scrollable-wrapper table-scrollbar table-fixed-header" style="padding-bottom: 30px">
        <%--Shows the items that match the searchQuery and the list that they are in--%>
        <h2><u>Items</u></h2>
        <div>
            <%
                String currentListName = "";
                for (Item item : searchResults) {
                    if (!item.getList().equals(currentListName)) { %>
                        <h4><a href="viewList.html?listName=<%=item.getList()%>" style="color: black">In <%=item.getList()%></a></h4>
                    <%}%>
                    <a href="viewItem.html?listName=<%=item.getList()%>&itemName=<%=item.getName()%>"><%=item.getName()%></a>
                    <br>
            <%}%>
        </div>
        <br>
        <%--Shows the lists that match the searchQuery--%>
        <h2><u>Lists:</u></h2>
        <%
            for (AList list : matchingLists) {
        %>
            <a href="viewList.html?listName=<%=list.getName()%>"><%=list.getName()%></a>
            <br>
        <%}%>
    </div>

    <jsp:include page="footer.jsp" />
</body>

</html>
