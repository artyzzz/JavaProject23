package applications;

import services.PlayerService;
import services.console.Controller;

public class Console {
    public static void main(String[] args) {
        PlayerService playerService = new PlayerService();
        Controller controller = new Controller();
        System.out.println("Запуск консоли...");
        controller.execute(playerService);
        System.out.println("Выход из консоли.");
    }
}
