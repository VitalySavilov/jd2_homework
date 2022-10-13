package my.task10;

import my.models.task10.User;

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
        if (isFormDataValid(req.getParameter("uname"),
                req.getParameter("tnumber"), req.getParameter("emale"))) {
            User user = getInstance(req.getParameter("uname"),
                    req.getParameter("tnumber"), req.getParameter("emale"));
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/jsp/task10/user_info.jsp")
                    .forward(req, resp);
        } else {
            resp.sendError(501);
        }
    }

    static User getInstance(String name, String telNumber, String emale) {
        User user = new User();
        user.setName(name);
        user.setTelNumber(telNumber);
        user.setEmale(emale);
        return user;
    }

    static boolean isFormDataValid (String name, String telNumber, String emale) {
        return (!name.isEmpty() && (!telNumber.isEmpty() || !emale.isEmpty()))
                && name.length() <= 20 && telNumber.matches("^((\\+)?\\d{7,12})")
                && emale.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
}
