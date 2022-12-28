package servlets;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import services.MapperService;
import services.PlayerService;
import classes.Progress;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {
    private final PlayerService playerService;
    private final MapperService mapperService = new MapperService();

    public ProgressServlet(PlayerService playerService) {
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
            outputStream.write(mapperService.printObject(playerService.getProgress(Integer.parseInt(playerId), Integer.parseInt(id)))
                    .getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var sourceId = req.getParameter("sourceId");
        final var score = req.getParameter("score");
        final var maxScore = req.getParameter("maxScore");


        if (id == null || playerId == null || sourceId == null || score == null || maxScore == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createProgress(new Progress(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(sourceId), Integer.parseInt(score), Integer.parseInt(maxScore)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var score = req.getParameter("score");
        final var maxScore = req.getParameter("maxScore");


        if (id == null || playerId == null || score == null || maxScore == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updateProgress(new Progress(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(score), Integer.parseInt(maxScore)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");

        if (id == null || playerId == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deleteProgress(Integer.parseInt(playerId), Integer.parseInt(id));
    }
}
