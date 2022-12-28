package services.console;

import services.MapperService;
import services.PlayerService;

import java.util.Scanner;

public class PrintConsole {
    private final MapperService mapperService = new MapperService();
    private final Scanner scanner = new Scanner(System.in);

    public void distribution(PlayerService playerService) {
        System.out.print("Enter playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(mapperService.printObject(playerService.getPlayer(playerId)));
    }
}
