package com.zqs.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewHistory")
public class ViewHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取历史足迹
        String index = response.encodeURL(request.getContextPath() + "/index");
        HttpSession session = request.getSession();
        List<String> histories = (List<String>) session.getAttribute("histories");
        if (histories == null) {
            response.getWriter().println("当前没有历史足迹~~~");
            response.getWriter().println("<br><a href='" + index + "'>返回首页</a><br>");
        }
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        response.getWriter().println("<h1 align='center'>浏览记录</h1>");
        for (String id : histories) {
            for (Product product : products) {
                if (id.equals(product.getId())) {
                    response.getWriter().println(product + "<br>");
                }
            }
        }
        response.getWriter().println("<br><a href='" + index + "'>返回首页</a><br>");
    }
}
