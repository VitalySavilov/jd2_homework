package my.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

@WebServlet(name = "BrowserInfo", urlPatterns = "/br-info")
public class BrowserInformationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static LinkedHashMap<String, String> browserNames = new LinkedHashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        browserNames.put("OPR", "Opera");
        browserNames.put("Edg", "Microsoft Edge");
        browserNames.put("Trident", "Internet Explorer");
        browserNames.put("YaBrowser", "Yandex Browser");
        browserNames.put("CriOS", "Google Chrome");
        browserNames.put("FxiOS", "Mozilla Firefox");
        browserNames.put("Brave", "Brave");
        browserNames.put("Vivaldi", "Vivaldi");
        browserNames.put("SeaMonkey", "SeaMonkey");
        browserNames.put("Chrome", "Google Chrome");
        browserNames.put("Firefox", "Mozilla Firefox");
        browserNames.put("Safari", "Safari");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = resp.getWriter();
        out.println(sayHello(req.getHeader("user-agent")));
    }

    private String sayHello(String info) {
        String browserName = "неизвестного браузера";
        for (String pattern : browserNames.keySet()) {
            if (info != null && info.contains(pattern)) {
                browserName = browserNames.get(pattern);
                break;
            }
        }
        return "Приветствую пользователя " + browserName;
    }
}
