<header>
<%--The nav bar at the top of every webpage--%>
    <nav class="navbar navbar-expand navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="viewAllData.html" style="font-size: 40px">Lists</a>
            <div class="navbar-collapse" style="font-size: 20px;" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="viewAllData.html">Data</a>
                    </li>
                    <li class="nav-item" style="padding-left: 20px">
                        <a class="nav-link" href="viewAllLists.html">Editor</a>
                    </li>
                </ul>
            </div>
            <form action="search.html" method="get" class="form-inline my-2">
                <div class="form-group">
                    <input class="form-inline form-control mr-2" type="search" name="searchQuery" placeholder="Search" aria-label="Search" required>
                    <button class="btn btn-outline-success my-2 form-inline" type="submit">Search</button>
                </div>
            </form>
        </div>
    </nav>
</header>