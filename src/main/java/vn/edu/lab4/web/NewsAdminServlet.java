package vn.edu.lab4.web;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.lab4.repo.NewsDAO;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns={"/news/admin","/news/delete"})
public class NewsAdminServlet extends HttpServlet {
    @Resource(name="jdbc/newsdb")
    private DataSource ds;
    private NewsDAO dao;

    @Override public void init(){ dao = new NewsDAO(ds); }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String madmStr = req.getParameter("madm");
        if(madmStr != null){
            int madm = Integer.parseInt(madmStr);
            req.setAttribute("news", dao.findByCategory(madm));
            req.setAttribute("madm", madm);
        }
        req.setAttribute("categories", dao.findAllCategories());
        req.getRequestDispatcher("/views/news/admin.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int matt = Integer.parseInt(req.getParameter("matt"));
        int madm = Integer.parseInt(req.getParameter("madm"));
        try{
            dao.delete(matt);
            resp.sendRedirect(req.getContextPath()+"/news/admin?madm="+madm);
        }catch(Exception e){ throw new ServletException(e); }
    }
}
