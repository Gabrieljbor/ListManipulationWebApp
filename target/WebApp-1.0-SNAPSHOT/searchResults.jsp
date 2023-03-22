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
        <h2><u>Items</u></h2>
        <div>
            <%
                String currentListName = "";
                for (Item item : searchResults) {
                    if (!item.list.equals(currentListName)) { %>
                        <h4><a href="viewList.html?listName=<%=item.list%>" style="color: black">In <%=item.list%></a></h4>
                    <%}%>
                    <a href="viewItem.html?listName=<%=item.list%>&itemName=<%=item.name%>"><%=item.name%></a>
                    <br>
            <%}%>
        </div>
        <br>
        <h2><u>Lists:</u></h2>
        <%
            for (AList list : matchingLists) {
        %>
            <a href="viewList.html?listName=<%=list.name%>"><%=list.name%></a>
            <br>
        <%}%>
    </div>

    <jsp:include page="footer.jsp" />
</body>

</html>
