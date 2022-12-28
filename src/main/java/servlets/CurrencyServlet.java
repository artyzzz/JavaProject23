package servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import services.MapperService;
import services.PlayerService;
import classes.Currency;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {
    private final PlayerService playerService;
    private final MapperService mapperService = new MapperService();

    public CurrencyServlet(PlayerService playerService) {
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
            outputStream.write(mapperService.printObject(playerService.getCurrency(Integer.parseInt(playerId), Integer.parseInt(id)))
                    .getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var sourceId = req.getParameter("sourceId");
        final var name = req.getParameter("name");
        final var count = req.getParameter("count");


        if (id == null || playerId == null || sourceId == null || name == null || count == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createCurrency(new Currency(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(sourceId), name, Integer.parseInt(count)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var name = req.getParameter("name");
        final var count = req.getParameter("count");


        if (id == null || playerId == null || name == null || count == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updateCurrency(new Currency(Integer.parseInt(id), Integer.parseInt(playerId),
                name, Integer.parseInt(count)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");

        if (id == null || playerId == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deleteCurrency(Integer.parseInt(playerId), Integer.parseInt(id));
    }
}
