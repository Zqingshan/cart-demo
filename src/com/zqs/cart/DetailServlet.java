package com.zqs.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取id
        String id = request.getParameter("id");
        // 添加历史足迹
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        for (Product product : products) {
            if (id.equals(product.getId())) {
                response.getWriter().println(product + "<br><br>");
            }
        }
        // 重写URL,防止浏览器关闭cookie之后数据无法共享
        String addCart = response.encodeURL(request.getContextPath() + "/addCart?id=" + id);
        String viewCart = response.encodeURL(request.getContextPath() + "/viewCart");
        String viewHistory = response.encodeURL(request.getContextPath() + "/viewHistory");
        String index = response.encodeURL(request.getContextPath() + "/index");
        response.getWriter().println("<a href='" + addCart + "'>加入购物车</a><br>");
        response.getWriter().println("<a href='" + viewCart + "'>查看购物车</a><br>");
        response.getWriter().println("<a href='" + viewHistory + "'>查看历史足迹</a><br>");
        response.getWriter().println("<a href='" + index + "'>返回首页</a><br>");
    }
}
