package com.zqs.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index = response.encodeURL(request.getContextPath() + "/index");
        String viewHistory = response.encodeURL(request.getContextPath() + "/viewHistory");
        response.getWriter().println("<h1 align='center'>购物车</h1>");
        HttpSession session = request.getSession();
        List<String> cart = (List<String>) session.getAttribute("cart");
        // 如果购物车为空
        if (cart == null) {
            response.getWriter().println("<h1>当前购物车没有商品,先去添加商品吧~~~</h1>");
            response.getWriter().println("<a href='" + index + "'>返回首页</a><br><br>");
        }
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        for (String id : cart) {
            for (Product product : products) {
                if (id.equals(product.getId())) {
                    response.getWriter().println(product + "<br>");
                }
            }
        }
        response.getWriter().println("<br><a href='" + viewHistory + "'>查看历史足迹</a><br>");
        response.getWriter().println("<a href='" + index + "'>返回首页</a><br>");
    }
}
