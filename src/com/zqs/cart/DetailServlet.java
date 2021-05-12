package com.zqs.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取id
        String id = request.getParameter("id");
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");

        // 添加历史足迹
        HttpSession session = request.getSession();
        List<String> histories = (List<String>) session.getAttribute("histories");

        String date = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());

        for (Product product : products) {
            if (id.equals(product.getId())) {
                response.getWriter().println(product + "<br><br>");
                if (histories == null) {
                    histories = new ArrayList<>();
                    session.setAttribute("histories", histories);
                }
                histories.add(id);
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
