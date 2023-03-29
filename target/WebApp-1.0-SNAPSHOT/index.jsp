<!DOCTYPE html>
<style>
    .center {
        right: 50%;
        bottom: 50%;
        transform: translate(50%,50%);
        position: absolute;
    }
</style>
<html lang="en">
<head>
    <jsp:include page="/meta.jsp"/>
</head>

<body>
    <jsp:include page="/header.jsp"/>
    <form action="viewAllLists.html" class="center">
        <button type="submit" class="btn btn-outline-success" style="width:800px;height:400px;font-size:50pt" >Create some lists!!!</button>
    </form>
</body>
</html>