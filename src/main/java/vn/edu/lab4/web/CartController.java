package vn.edu.lab4.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.lab4.model.*;
import vn.edu.lab4.repo.ProductRepository;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(urlPatterns={"/shop","/cart","/cart/add","/cart/update","/cart/remove"})
public class CartController extends HttpServlet {
    private final ProductRepository productRepo = new ProductRepository();

    @SuppressWarnings("unchecked")
    private Map<Integer, CartItem> getCart(HttpSession session){
        Object obj = session.getAttribute("cart");
        if(obj == null){
            Map<Integer, CartItem> map = new LinkedHashMap<>();
            session.setAttribute("cart", map);
            return map;
        }
        return (Map<Integer, CartItem>) obj;
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();
        if("/shop".equals(path)){
            req.setAttribute("products", productRepo.findAll());
            req.getRequestDispatcher("/views/shop/index.jsp").forward(req, resp);
        } else if("/cart".equals(path)){
            req.getRequestDispatcher("/views/shop/cart.jsp").forward(req, resp);
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        Map<Integer, CartItem> cart = getCart(session);

        if("/cart/add".equals(path)){
            int id = Integer.parseInt(req.getParameter("id"));
            int qty = Integer.parseInt(req.getParameter("qty"));
            Product p = productRepo.findById(id);
            if(p != null){
                cart.compute(id, (k,v) -> v == null ? new CartItem(p, qty) : new CartItem(p, v.getQty() + qty));
            }
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        if("/cart/update".equals(path)){
            int id = Integer.parseInt(req.getParameter("id"));
            int qty = Integer.parseInt(req.getParameter("qty"));
            CartItem it = cart.get(id);
            if(it != null){
                if(qty <= 0) cart.remove(id);
                else it.setQty(qty);
            }
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        if("/cart/remove".equals(path)){
            int id = Integer.parseInt(req.getParameter("id"));
            cart.remove(id);
            resp.sendRedirect(req.getContextPath() + "/cart");
        }
    }
}
