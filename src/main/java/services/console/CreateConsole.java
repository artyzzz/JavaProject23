package services.console;

import classes.Currency;
import classes.Item;
import classes.Player;
import classes.Progress;
import services.PlayerService;

import java.util.Scanner;

public class CreateConsole {
    private final Scanner scanner = new Scanner(System.in);

    public void distribution(PlayerService playerService) {
        System.out.print("Введите объект (player, progress, currency, item) - ");
        String object = scanner.nextLine();

        switch (object) {
            case "player" -> createPlayer(playerService);
            case "progress" -> createProgress(playerService);
            case "currency" -> createCurrency(playerService);
            case "item" -> createItem(playerService);
        }
    }

    public void createPlayer(PlayerService playerService) {
        Player player = new Player();
        System.out.println("Создание нового игрока:");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        player.setPlayerId(id);

        System.out.print("Введите nickname - ");
        player.setNickname(scanner.nextLine());

        playerService.createPlayer(player);
    }

    public void createProgress(PlayerService playerService) {
        Progress progress = new Progress();
        System.out.println("Создание нового прогресса:");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        progress.setId(id);

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        progress.setPlayerId(playerId);

        System.out.print("Введите resourceId - ");
        int resourceId = scanner.nextInt();
        scanner.nextLine();
        progress.setResourceId(resourceId);

        System.out.print("Введите score - ");
        int score = scanner.nextInt();
        scanner.nextLine();
        progress.setScore(score);

        System.out.print("Введите maxScore - ");
        int maxScore = scanner.nextInt();
        scanner.nextLine();
        progress.setMaxScore(maxScore);

        playerService.createProgress(progress);
    }

    public void createCurrency(PlayerService playerService) {
        Currency currency = new Currency();
        System.out.println("Создание новой валюты: ");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        currency.setId(id);

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        currency.setPlayerId(playerId);

        System.out.print("Введите resourceId - ");
        int resourceId = scanner.nextInt();
        scanner.nextLine();
        currency.setResourceId(resourceId);

        System.out.print("Введите name - ");
        String name = scanner.nextLine();
        currency.setName(name);

        System.out.print("Введитеr count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        currency.setCount(count);

        playerService.createCurrency(currency);
    }

    public void createItem(PlayerService playerService) {
        Item item = new Item();
        System.out.println("Создание нового предмета:");

        System.out.print("Введите id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        item.setId(id);

        System.out.print("Введите playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        item.setPlayerId(playerId);

        System.out.print("Введите resourceId - ");
        int resourceId = scanner.nextInt();
        scanner.nextLine();
        item.setResourceId(resourceId);

        System.out.print("Введите count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        item.setCount(count);

        System.out.print("Введите level - ");
        int level = scanner.nextInt();
        scanner.nextLine();
        item.setLevel(level);

        playerService.createItem(item);
    }
}
