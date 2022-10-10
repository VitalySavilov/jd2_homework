package my.servlets;

import my.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "information", urlPatterns = "/information.do")
public class UserInformationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        User user = getInstance(req.getParameter("uname"),
                req.getParameter("tnumber"), req.getParameter("emale"));
        if (user.getName().isEmpty()
                || (user.getTelNumber().isEmpty() && user.getEmale().isEmpty())) {
            resp.sendError(501);
        } else {
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/jsp/user_info.jsp")
                    .forward(req, resp);
        }
    }

    static User getInstance(String name, String telNumber, String emale) {
        User user = new User();
        user.setName(name);
        user.setTelNumber(telNumber);
        user.setEmale(emale);
        return user;
    }
}
