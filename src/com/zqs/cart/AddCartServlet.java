package com.zqs.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cart.add(id);
        response.getWriter().println("<h1>商品添加成功</h1>" + "<br><br>");
        String viewCart = response.encodeURL(request.getContextPath() + "/viewCart");
        String index = response.encodeURL(request.getContextPath() + "/index");
        response.getWriter().println("<a href='" + viewCart + "'>查看购物车</a><br>");
        response.getWriter().println("<a href='" + index + "'>返回首页</a><br>");
    }
}
