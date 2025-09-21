package vn.edu.lab4.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import vn.edu.lab4.model.Account;
import vn.edu.lab4.repo.AccountRepository;
import java.io.IOException;
import java.util.Set;

@WebServlet(urlPatterns = {"/account", "/account/create"})
public class AccountController extends HttpServlet {
    private final AccountRepository repo = new AccountRepository();
    private Validator validator;

    @Override public void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("accounts", repo.findAll());
        req.getRequestDispatcher("/views/account/index.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fullName = req.getParameter("fullName");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        Account acc = new Account(0, fullName, email, password);
        Set<ConstraintViolation<Account>> violations = validator.validate(acc);
        if(!violations.isEmpty()){
            req.setAttribute("errors", violations);
            req.setAttribute("form", acc);
            req.setAttribute("accounts", repo.findAll());
            req.getRequestDispatcher("/views/account/index.jsp").forward(req, resp);
            return;
        }
        repo.save(acc);
        resp.sendRedirect(req.getContextPath() + "/account");
    }
}
