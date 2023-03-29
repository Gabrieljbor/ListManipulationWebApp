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

@WebServlet("/deleteList.html")
public class DeleteListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Retrieves the model and gets the necessary parameters
        Model model = ModelFactory.getModel();
        String listToDelete = request.getParameter("listToDelete");

        model.deleteList(listToDelete);

        //Dispatches the JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewAllLists.html");
        dispatch.forward(request, response);
    }
}
