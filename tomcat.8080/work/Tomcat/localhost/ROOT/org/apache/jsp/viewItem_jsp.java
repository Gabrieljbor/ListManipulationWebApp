/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.45
 * Generated at: 2023-03-29 20:18:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import item.Item;

public final class viewItem_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("item.Item");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');

    Item item = (Item) request.getAttribute("item");
    String[] listNames = (String[]) request.getAttribute("listNames");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<style>\n");
      out.write("    .btn-space {\n");
      out.write("        margin-top: 5px;\n");
      out.write("    }\n");
      out.write("    form {\n");
      out.write("        display: inline;\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "meta.jsp", out, false);
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    <section class=\"bg-white\">\n");
      out.write("        <h1 class=\"text-center\">");
      out.print(item.getName());
      out.write("</h1>\n");
      out.write("        <br>\n");
      out.write("        <div class=\"container\" >\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-6\">\n");
      out.write("                    <div>\n");
      out.write("                        <label>Item Name:</label>\n");
      out.write("                        <form action=\"changeItemName.html\" method=\"get\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name =\"newItemName\" pattern=\"[a-zA-Z0-9]+\" placeholder=\"");
      out.print(item.getName());
      out.write("\" required>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-primary btn-space\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">Update</button>\n");
      out.write("                        </form>\n");
      out.write("                        <form action=\"deleteItem.html\" method=\"get\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-danger btn-space\" name=\"itemToDelete\" value=\"");
      out.print(item.getName());
      out.write("\">Delete</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                    <br>\n");
      out.write("                    <div>\n");
      out.write("                        <label>Text:</label>\n");
      out.write("                        <form action=\"setItemText.html\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <textarea class=\"form-control\" name =\"itemText\" rows=\"6\" required>");
      out.print(item.getText());
      out.write("</textarea>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-primary btn-space\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">Submit</button>\n");
      out.write("                        </form>\n");
      out.write("                        <form action=\"setItemText.html\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-danger btn-space\" name=\"itemText\" value=\"\">Clear</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                    <br>\n");
      out.write("                    <div>\n");
      out.write("                        <label>Link to another List:</label>\n");
      out.write("                        <a href=\"viewList.html?listName=");
      out.print(item.getListLink());
      out.write("\" style=\"padding-left: 5px\">");
      out.print(item.getListLink());
      out.write("</a>\n");
      out.write("                        <form action=\"setItemLink.html\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">\n");
      out.write("                            <select name=\"itemLinkedList\" class=\"form-control\"  required>\n");
      out.write("                                <option selected disabled value=\"\">Other lists</option>\n");
      out.write("                                ");

                                    for (String currentListName : listNames){
                                        if (!currentListName.equals(item.getList())){
                                
      out.write("\n");
      out.write("                                <option value=\"");
      out.print(currentListName);
      out.write('"');
      out.write('>');
      out.print(currentListName);
      out.write("</option>\n");
      out.write("                                ");

                                        }
                                    }
                                
      out.write(">\n");
      out.write("                            </select>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-primary btn-space\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">Submit</button>\n");
      out.write("                        </form>\n");
      out.write("                        <form action=\"setItemLink.html\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-danger btn-space\" name=\"itemLinkedList\" value=\"\">Reset</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                    <br>\n");
      out.write("                <div class=\"col-6\">\n");
      out.write("                    <div>\n");
      out.write("                        <label>URL:</label>\n");
      out.write("                        <a href=\"");
      out.print(item.getUrl());
      out.write("\" target=\"_blank\" style=\"padding-left: 5px\">");
      out.print(item.getUrl());
      out.write("</a>\n");
      out.write("                        <form action=\"setItemURL.html\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"url\" class=\"form-control\" name =\"itemURL\" placeholder=\"https://\" required>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-primary btn-space\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">Submit</button>\n");
      out.write("                        </form>\n");
      out.write("                        <form action=\"setItemURL.html\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-danger btn-space\" name=\"itemURL\" value=\"\">Clear</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                    <br>\n");
      out.write("                    <div>\n");
      out.write("                        <label>Image:</label>\n");
      out.write("                        <br>\n");
      out.write("                        ");
 if (!item.getImage().equals("")) {
      out.write("\n");
      out.write("                        <form action=\"viewImage.html\" method=\"post\" target=\"_blank\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <button type=\"submit\" class=\"btn\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\"><img src=\"");
      out.print(item.getImage());
      out.write("\" style=\"max-width: 100%; max-height: 250px;\" class=\"img-thumbnail\"/></button>\n");
      out.write("                        </form>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                        <form action=\"setItemImage.html\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <input type=\"file\" accept=\"image/jpeg\" class=\"form-control\" name=\"image\" required>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-primary btn-space\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">Submit</button>\n");
      out.write("                        </form>\n");
      out.write("                        <form action=\"deleteItemImage.html\" method=\"get\" enctype=\"multipart/form-data\">\n");
      out.write("                            <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-outline-danger btn-space\" name=\"itemName\" value=\"");
      out.print(item.getName());
      out.write("\">Clear</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <br><br><br>\n");
      out.write("            <div class=\"row mb-5\" style=\"left: 0; bottom: 0; width: 100%; text-align: center;\">\n");
      out.write("                <form action=\"viewList.html\" method=\"get\">\n");
      out.write("                    <input type=\"hidden\" name=\"listName\" value=\"");
      out.print(item.getList());
      out.write("\">\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-warning\">Go Back To List: ");
      out.print(item.getList());
      out.write("</button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </section>\n");
      out.write("\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
