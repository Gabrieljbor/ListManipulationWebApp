<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Map<String, List<String>> searchResults = (Map<String, List<String>>) request.getAttribute("itemSearchResults");
    String searchQuery = (String) request.getAttribute("searchQuery");
    List<String> matchingLists = (List<String>) request.getAttribute("matchingLists");
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
                for (String listName : searchResults.keySet()) {
            %>
                    <% if (!searchResults.get(listName).isEmpty()) { %>
                        <h4><a href="viewList.html?listName=<%=listName%>" style="color: black">In <%=listName%></a></h4>
                    <%}%>

                <%
                    for (String searchResult : searchResults.get(listName)) {
                %>
                        <a href="viewItem.html?listName=<%=listName%>&itemName=<%=searchResult%>"><%=searchResult%></a>
                        <br>
                <%}%>
            <br>
            <%}%>
        </div>

        <h2><u>Lists:</u></h2>
        <%
            for (String matchingList : matchingLists) {
        %>
            <a href="viewList.html?listName=<%=matchingList%>"><%=matchingList%></a>
            <br>
        <%}%>
    </div>

    <jsp:include page="footer.jsp" />
</body>

</html>
