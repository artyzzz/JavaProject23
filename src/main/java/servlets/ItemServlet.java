package servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import services.MapperService;
import services.PlayerService;
import classes.Item;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    private final PlayerService playerService;
    private final MapperService mapperService = new MapperService();

    public ItemServlet(PlayerService playerService) {
        this.playerService = playerService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");

        if (id == null || playerId == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(mapperService.printObject(playerService.getItem(Integer.parseInt(playerId), Integer.parseInt(id)))
                    .getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var sourceId = req.getParameter("sourceId");
        final var count = req.getParameter("count");
        final var level = req.getParameter("level");


        if (id == null || playerId == null || sourceId == null || count == null || level == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createItem(new Item(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(sourceId), Integer.parseInt(count), Integer.parseInt(level)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var count = req.getParameter("count");
        final var level = req.getParameter("level");


        if (id == null || playerId == null || count == null || level == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updateItem(new Item(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(count), Integer.parseInt(level)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");


        if (id == null || playerId == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deleteItem(Integer.parseInt(playerId), Integer.parseInt(id));
    }
}
