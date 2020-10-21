package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.CookiesRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ServletDB";

    private UsersRepositoryJdbcImpl usersRepository;
    private CookiesRepository cookiesRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
            cookiesRepository = new CookiesRepository(connection);
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getParameter("login"));
        Optional<User> user = usersRepository.findByLogin(req.getParameter("login"));
        if (user.isPresent() && user.get().getPass().equals(req.getParameter("pass"))) {
            UUID uuid = UUID.randomUUID();
            cookiesRepository.save(uuid, user.get().getId());
            Cookie cookie = new Cookie("Auth", uuid.toString());
            cookie.setMaxAge(60 * 60 * 24);
            resp.addCookie(cookie);

        } else {
            resp.sendRedirect("/signIn");
        }
    }

}
