package services.console;

import services.PlayerService;

import java.util.Scanner;

public class Controller {
    public void execute(PlayerService playerService) {
        Scanner scanner = new Scanner(System.in);
        CreateConsole createConsole = new CreateConsole();
        PrintConsole printConsole = new PrintConsole();
        UpdateConsole updateConsole = new UpdateConsole();
        DeleteConsole deleteConsole = new DeleteConsole();

        System.out.println("Список команд: \n1) Создать \n2) Вывести \n3) Обновить \n4) Удалить \n5) Выход");
        while (true) {
            System.out.print("Введите номер команды: ");
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> createConsole.distribution(playerService);
                case "2" -> printConsole.distribution(playerService);
                case "3" -> updateConsole.distribution(playerService);
                case "4" -> deleteConsole.distribution(playerService);
                case "5" -> {
                    return;
                }
            }
        }
    }
}
