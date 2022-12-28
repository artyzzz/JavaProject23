package servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import services.MapperService;
import services.PlayerService;
import classes.Player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {
    private final PlayerService playerService;
    private final MapperService mapperService = new MapperService();

    public PlayerServlet(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var id = req.getParameter("id");

        if (id == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(mapperService.printObject(playerService.getPlayer(Integer.parseInt(id))).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        final var id = req.getParameter("id");
        final var nick = req.getParameter("nickname");

        if ((id == null) || (nick == null)) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updatePlayer(new Player(Integer.parseInt(id), nick));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final var id = req.getParameter("id");
        final var nick = req.getParameter("nickname");

        if ((id == null) || (nick == null)) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createPlayer(new Player(Integer.parseInt(id), nick));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var id = req.getParameter("id");

        if ((id == null)) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deletePlayer(Integer.parseInt(id));
    }
}
