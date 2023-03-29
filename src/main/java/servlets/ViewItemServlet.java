package servlets;

import item.Item;
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

@WebServlet("/viewItem.html")
public class ViewItemServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Clears cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        //Retrieves the model and gets the necessary parameters
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemName = request.getParameter("itemName");

        Item item = model.getList(listName).getItem(itemName);
        String[] listNames = model.getListNames();

        request.setAttribute("item", item);
        request.setAttribute("listNames", listNames);

        //Dispatches the JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewItem.jsp");
        dispatch.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
