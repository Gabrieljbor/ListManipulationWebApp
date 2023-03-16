package servlets;

import model.Model;
import model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/search.html")
public class SearchServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to search for an item
        Model model = ModelFactory.getModel();
        String searchQuery = Objects.requireNonNullElse(request.getParameter("searchQuery"), "");
        Map<String, List<String>> itemSearchResults = model.searchForItem(searchQuery);
        List<String> matchingLists = model.searchForList(searchQuery);

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("itemSearchResults", itemSearchResults);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("matchingLists", matchingLists);

        // Invokes the 'searchResult' JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchResults.jsp");
        dispatch.forward(request, response);
    }
}
