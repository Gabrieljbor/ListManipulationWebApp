<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String listName = (String) request.getAttribute("listName");
    String[] listItems = (String[]) request.getAttribute("listItems");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="meta.jsp" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <section class="bg-white">
        <div class="container" style="padding-bottom: 20px; width: 100%">
            <h1 class="text-center"><%=listName%></h1>
            <div class="input-group mb-3 ">
                <form action="changeListName.html" method="get">
                    <input type="hidden" name="listName" value="<%=listName%>">
                    <input type="text" class="form-control" name ="newListName" pattern="[a-zA-Z0-9]+" placeholder="Edit alist name" required style="width: 120px; position: center">
                    <button type="submit" class="btn btn-outline-primary">Update</button>
                </form>
            </div>
            <div class="row">
                <table class="table text-center">
                    <thead>
                        <tr>
                            <th class="text-center">Items</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (String listItem : listItems) {
                        %>
                        <tr>
                            <td>
                                <a href="viewItem.html?listName=<%=listName%>&itemName=<%=listItem%>"><%=listItem%></a>
                            </td>
                            <td>
                                <form action="deleteItem.html" method="get">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <button type="submit" class="btn btn-danger" name="itemToDelete" value="<%=listItem%>">Delete</button>
                                </form>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>

                <div class="row text-center" style="color: black">
                    <div class="col-4 offset-4">
                        <form action="addItem.html" method="get">
                            <h4>Add Item</h4>
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="text" name ="itemName" class="form-control" pattern="[a-zA-Z0-9]+" placeholder="Enter Item Name" required>
                            <button type="submit" class="btn btn-success mt-3">Add</button>
                        </form>
                        <br>
                    </div>
                </div>
            </div>

            <div class="row mb-5" style="width: 100%; text-align: center;">
                <form action="viewAllLists.html" method="get">
                    <button type="submit" class="btn btn-warning">Go Back To Lists</button>
                </form>
            </div>
        </div>

    </section>

    <jsp:include page="footer.jsp" />
</body>

</html>
