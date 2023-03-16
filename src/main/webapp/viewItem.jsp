<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String listName = (String) request.getAttribute("listName");
    String itemName = (String) request.getAttribute("itemName");
    String itemListLink = (String) request.getAttribute("itemListLink");
    String itemText = (String) request.getAttribute("itemText");
    String itemFileName = (String) request.getAttribute("itemImageName");
    String itemFilePath = (String) request.getAttribute("itemImagePath");
    String itemURL = (String) request.getAttribute("itemURL");
    String[] listNames = (String[]) request.getAttribute("listNames");
%>

<!DOCTYPE html>
<style>
    .btn-space {
        margin-top: 5px;
    }
    form {
        display: inline;
    }
</style>

<html>

<head>
    <jsp:include page="meta.jsp" />
</head>
<body>
    <jsp:include page="header.jsp" />
    <section class="bg-white">
        <h1 class="text-center"><%=itemName%></h1>
        <br>
        <div class="container" >
            <div class="row">
                <div class="col-6">
                    <div>
                        <label>Item Name:</label>
                        <form action="changeItemName.html" method="get">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="text" class="form-control" name ="newItemName" pattern="[a-zA-Z0-9]+" placeholder="<%=itemName%>" required>
                            <button type="submit" class="btn btn-outline-primary btn-space" name="itemName" value="<%=itemName%>">Update</button>
                        </form>
                        <form action="deleteItem.html" method="get">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <button type="submit" class="btn btn-outline-danger btn-space" name="itemToDelete" value="<%=itemName%>">Delete</button>
                        </form>
                    </div>
                    <br>
                    <div>
                        <label>Text:</label>
                        <form action="setItemText.html" method="post">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <textarea class="form-control" name ="itemText" rows="6" required><%=itemText%></textarea>
                            <button type="submit" class="btn btn-outline-primary btn-space" name="itemName" value="<%=itemName%>">Submit</button>
                        </form>
                        <form action="setItemText.html" method="post">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="hidden" name="itemName" value="<%=itemName%>">
                            <button type="submit" class="btn btn-outline-danger btn-space" name="itemText" value="">Clear</button>
                        </form>
                    </div>
                    <br>
                    <div>
                        <label>Link to another List:</label>
                        <a href="viewList.html?listName=<%=itemListLink%>" style="padding-left: 5px"><%=itemListLink%></a>
                        <form action="setItemLink.html" method="post">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="hidden" name="itemName" value="<%=itemName%>">
                            <select name="itemLinkedList" class="form-control"  required>
                                <option selected disabled value="">Other lists</option>
                                <%
                                    for (String currentListName : listNames){
                                        if (!currentListName.equals(listName)){
                                %>
                                <option value="<%=currentListName%>"><%=currentListName%></option>
                                <%
                                        }
                                    }
                                %>>
                            </select>
                            <button type="submit" class="btn btn-outline-primary btn-space" name="itemName" value="<%=itemName%>">Submit</button>
                        </form>
                        <form action="setItemLink.html" method="post">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="hidden" name="itemName" value="<%=itemName%>">
                            <button type="submit" class="btn btn-outline-danger btn-space" name="itemLinkedList" value="">Reset</button>
                        </form>
                    </div>
                </div>
                    <br>
                <div class="col-6">
                    <div>
                        <label>URL:</label>
                        <a href="<%=itemURL%>" target="_blank" style="padding-left: 5px"><%=itemURL%></a>
                        <form action="setItemURL.html" method="post">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="url" class="form-control" name ="itemURL" placeholder="https://" required>
                            <button type="submit" class="btn btn-outline-primary btn-space" name="itemName" value="<%=itemName%>">Submit</button>
                        </form>
                        <form action="setItemURL.html" method="post">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="hidden" name="itemName" value="<%=itemName%>">
                            <button type="submit" class="btn btn-outline-danger btn-space" name="itemURL" value="">Clear</button>
                        </form>
                    </div>
                    <br>
                    <div>
                        <label>Image:</label>
                        <br>
                        <% if (itemFileName.equals("img.jpg")) {%>
                        <form action="viewImage.html" method="post" target="_blank">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <button type="submit" class="btn" name="itemName" value="<%=itemName%>"><img src="<%=itemFilePath%>" style="max-width: 100%; max-height: 250px;" class="img-thumbnail"/></button>
                        </form>
                        <%}%>
                        <form action="setItemImage.html" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <input type="file" accept="image/png, image/jpeg" class="form-control" name="image" required>
                            <button type="submit" class="btn btn-outline-primary btn-space" name="itemName" value="<%=itemName%>">Submit</button>
                        </form>
                        <form action="deleteItemFile.html" method="get" enctype="multipart/form-data">
                            <input type="hidden" name="listName" value="<%=listName%>">
                            <button type="submit" class="btn btn-outline-danger btn-space" name="itemName" value="<%=itemName%>">Clear</button>
                        </form>
                    </div>
                </div>
            </div>
            <br><br><br>
            <div class="row mb-5" style="left: 0; bottom: 0; width: 100%; text-align: center;">
                <form action="viewList.html" method="get">
                    <input type="hidden" name="listName" value="<%=listName%>">
                    <button type="submit" class="btn btn-warning">Go Back To List: <%=listName%></button>
                </form>
            </div>
        </div>

    </section>

    <jsp:include page="footer.jsp" />
</body>

</html>
