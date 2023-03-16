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

@WebServlet("/viewList.html")
public class ViewListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String[] listItems = model.getListItems(listName);

        request.setAttribute("listName", listName);
        request.setAttribute("listItems", listItems);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewList.jsp");
        dispatch.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
