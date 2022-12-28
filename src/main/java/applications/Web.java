package applications;

import org.eclipse.jetty.server.Server;
import services.PlayerService;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.CurrencyServlet;
import servlets.ItemServlet;
import servlets.PlayerServlet;
import servlets.ProgressServlet;

public class Web {
    public static void main(String[] args) throws Exception {
        PlayerService playerService = new PlayerService();

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(new ServletHolder(new PlayerServlet(playerService)), "/player");
        handler.addServletWithMapping(new ServletHolder(new ProgressServlet(playerService)), "/progress");
        handler.addServletWithMapping(new ServletHolder(new CurrencyServlet(playerService)), "/currency");
        handler.addServletWithMapping(new ServletHolder(new ItemServlet(playerService)), "/item");

        server.setHandler(handler);

        server.start();
    }
}
