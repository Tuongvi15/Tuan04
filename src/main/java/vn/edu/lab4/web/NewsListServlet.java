package vn.edu.lab4.web;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.lab4.repo.NewsDAO;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns={"/news"})
public class NewsListServlet extends HttpServlet {
    @Resource(name="jdbc/newsdb")
    private DataSource ds;
    private NewsDAO dao;

    @Override public void init(){ dao = new NewsDAO(ds); }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String madmStr = req.getParameter("madm");
        req.setAttribute("categories", dao.findAllCategories());
        if(madmStr != null){
            int madm = Integer.parseInt(madmStr);
            req.setAttribute("news", dao.findByCategory(madm));
            req.setAttribute("madm", madm);
        }
        req.getRequestDispatcher("/views/news/list.jsp").forward(req, resp);
    }
}
