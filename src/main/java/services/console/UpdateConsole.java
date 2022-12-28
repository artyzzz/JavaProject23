package services.console;

import classes.Currency;
import classes.Item;
import classes.Player;
import classes.Progress;
import services.PlayerService;

import java.util.Scanner;

public class UpdateConsole {
    private final Scanner scanner = new Scanner(System.in);

    public void distribution(PlayerService playerService) {
        System.out.print("Введите объект (player, progress, currency, item) - ");
        String object = scanner.nextLine();

        switch (object) {
            case "player" -> updatePlayer(playerService);
            case "progress" -> updateProgress(playerService);
            case "currency" -> updateCurrency(playerService);
            case "item" -> updateItem(playerService);
        }
    }

    public void updatePlayer(PlayerService playerService) {
        Player player = new Player();

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        player.setPlayerId(id);

        System.out.print("Введите новый nickname - ");
        player.setNickname(scanner.nextLine());

        playerService.updatePlayer(player);
    }

    public void updateProgress(PlayerService playerService) {
        Progress progress = new Progress();
        System.out.println("Обновление прогресса:");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        progress.setId(id);

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        progress.setPlayerId(playerId);

        System.out.print("Введите score - ");
        int score = scanner.nextInt();
        scanner.nextLine();
        progress.setScore(score);

        System.out.print("Введите maxScore - ");
        int maxScore = scanner.nextInt();
        scanner.nextLine();
        progress.setMaxScore(maxScore);

        playerService.updateProgress(progress);
    }

    public void updateCurrency(PlayerService playerService) {
        Currency currency = new Currency();
        System.out.println("Обновление валюты:");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        currency.setId(id);

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        currency.setPlayerId(playerId);

        System.out.print("Введите name - ");
        String name = scanner.nextLine();
        currency.setName(name);

        System.out.print("Введите count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        currency.setCount(count);

        playerService.updateCurrency(currency);
    }

    public void updateItem(PlayerService playerService) {
        Item item = new Item();
        System.out.println("Обновление предмета:");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        item.setId(id);

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        item.setPlayerId(playerId);

        System.out.print("Введите count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        item.setCount(count);

        System.out.print("Введите level - ");
        int level = scanner.nextInt();
        scanner.nextLine();
        item.setLevel(level);

        playerService.updateItem(item);
    }
}
