<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String[] listNames = (String[]) request.getAttribute("listNames");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="meta.jsp"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <%--List of the list names and the ability to go into the list or delete it--%>
    <section class="bg-white">
        <div class="container" style="padding-bottom: 20px; width: 100%">
            <h1 class="text-center">Lists</h1>
            <div class="row">
                <table class="table text-center">
                    <thead>
                        <tr>
                            <th class="text-center">List Name</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (String listName : listNames) {
                        %>
                        <tr>
                            <td>
                                <a href="viewList.html?listName=<%=listName%>"><%=listName%></a>
                            </td>
                            <td>
                                <form action="deleteList.html" method="get">
                                    <input type="hidden" name="listToDelete" value="<%=listName%>">
                                    <button type="submit" class="btn btn-danger" name="listToDelete" value="<%=listName%>">Delete</button>
                                </form>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>

            <div class="row text-center" style="color: black">
                <div class="col-4 offset-4">
                    <form action="addList.html" method="get">
                        <h4>Add List</h4>
                        <input type="text" name ="addListName" class="form-control" pattern="[a-zA-Z0-9]+" placeholder="Enter List Name" required>
                        <button type="submit" class="btn btn-success mt-3">Add</button>
                    </form>
                    <br>
                </div>
            </div>

            <div class="row mb-5" style="text-align: center;">
                <form action="viewAllData.html" method="get">
                    <button type="submit" class="btn btn-warning">Go To All Data</button>
                </form>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>
</body>
</html>
