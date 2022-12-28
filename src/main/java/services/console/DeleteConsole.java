package services.console;

import classes.Currency;
import classes.Item;
import classes.Player;
import classes.Progress;
import services.PlayerService;

import java.util.Scanner;

public class DeleteConsole {
    private final Scanner scanner = new Scanner(System.in);

    public void distribution(PlayerService playerService) {
        System.out.print("Введите объект (player, progress, currency, item) - ");
        String object = scanner.nextLine();

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();

        int id = -1;
        if (!object.equals("player")) {
            System.out.print("Введите id - ");
            id = scanner.nextInt();
            scanner.nextLine();
        }

        switch (object) {
            case "player" -> playerService.deletePlayer(playerId);
            case "progress" -> playerService.deleteProgress(playerId, id);
            case "currency" -> playerService.deleteCurrency(playerId, id);
            case "item" -> playerService.deleteItem(playerId, id);
        }


    }
}
