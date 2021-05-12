package com.zqs.cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/index", loadOnStartup = 1)
public class IndexServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        Product apple = new Product("001", "apple", "一天一个苹果,医生远离我");
        Product banana = new Product("002", "banana", "就是香蕉");
        Product lemon = new Product("003", "lemon", "柠檬树上柠檬果,柠檬树下你和我");
        Product watermelon = new Product("004", "watermelon", "老板,这瓜保熟吗?");
        Product Mango = new Product("005", "Mango", "芒果好吃");
        List<Product> products = new ArrayList<>();
        products.add(apple);
        products.add(banana);
        products.add(lemon);
        products.add(watermelon);
        products.add(Mango);
        // 将products存入context域
        getServletContext().setAttribute("products", products);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在首页展示商品
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        for (Product product : products) {
            String detail = response.encodeURL(request.getContextPath() + "/detail?id=" + product.getId());
            response.getWriter().println("<a href='" + detail + "'>" + product.getName() + "</a><br>");
        }

    }
}
