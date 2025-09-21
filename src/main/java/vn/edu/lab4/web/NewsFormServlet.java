package vn.edu.lab4.web;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import vn.edu.lab4.model.TinTuc;
import vn.edu.lab4.repo.NewsDAO;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns={"/news/form","/news/create"})
public class NewsFormServlet extends HttpServlet {
    @Resource(name="jdbc/newsdb")
    private DataSource ds;
    private NewsDAO dao;
    private Validator validator;

    @Override public void init(){
        dao = new NewsDAO(ds);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("categories", dao.findAllCategories());
        req.getRequestDispatcher("/views/news/form.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        TinTuc t = new TinTuc();
        t.setTieuDe(req.getParameter("tieuDe"));
        t.setNoiDungTT(req.getParameter("noiDungTT"));
        t.setLienKet(req.getParameter("lienKet"));
        t.setMadm(Integer.parseInt(req.getParameter("madm")));

        Set<ConstraintViolation<TinTuc>> violations = validator.validate(t);
        if(!violations.isEmpty()){
            req.setAttribute("errors", violations);
            req.setAttribute("tin", t);
            req.setAttribute("categories", dao.findAllCategories());
            req.getRequestDispatcher("/views/news/form.jsp").forward(req, resp);
            return;
        }
        try{
            dao.insert(t);
            resp.sendRedirect(req.getContextPath()+"/news?madm="+t.getMadm());
        } catch(Exception e){
            throw new ServletException(e);
        }
    }
}
