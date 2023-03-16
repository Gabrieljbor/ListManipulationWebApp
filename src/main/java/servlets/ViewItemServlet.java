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

@WebServlet("/viewItem.html")
public class ViewItemServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemName = request.getParameter("itemName");

        String[] listNames = model.getListNames();
        String itemText = model.getItemText(listName, itemName);
        String itemListLink = model.getItemListLink(listName, itemName);
        String itemURL = model.getItemURL(listName, itemName);
        String[] itemImage = model.getItemImage(listName, itemName);
        String itemImageName = itemImage[0];
        String itemImagePath = itemImage[1];

        request.setAttribute("listName", listName);
        request.setAttribute("itemName", itemName);
        request.setAttribute("itemListLink", itemListLink);
        request.setAttribute("listNames", listNames);
        request.setAttribute("itemText", itemText);
        request.setAttribute("itemURL", itemURL);
        request.setAttribute("itemImageName", itemImageName);
        request.setAttribute("itemImagePath", itemImagePath);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewItem.jsp");
        dispatch.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
