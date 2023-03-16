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
import java.util.Map;

@WebServlet("/viewAllData.html")
public class ViewAllDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        Map<String, Map<String, String[]>> data = model.getAllData();

        request.setAttribute("data", data);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewAllData.jsp");
        dispatch.forward(request, response);
    }
}
