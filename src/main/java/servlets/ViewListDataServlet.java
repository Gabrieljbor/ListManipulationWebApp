package servlets;

import alist.AList;
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

@WebServlet("/viewListData.html")
public class ViewListDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Retrieves the model and gets the necessary parameters
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");

        AList list = model.getList(listName);
        request.setAttribute("list", list);

        //Dispatches the JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewListData.jsp");
        dispatch.forward(request, response);
    }
}
