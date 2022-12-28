package services;

import classes.Currency;
import classes.Item;
import classes.Player;
import classes.Progress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerService {
    private final DatabaseService databaseService = new DatabaseService();
    private final Map<Integer, Player> playerList = new HashMap<>();

    public PlayerService() {
        List<Player> players = databaseService.getPlayersFromDataBase();
        for (Player player : players) {
            playerList.put(player.getPlayerId(), player);
        }
    }

    public Player getPlayer(int playerId) {
        return playerList.get(playerId);
    }

    public Progress getProgress(int playerId, int id) {
        int index = findProgress(playerList.get(playerId).getProgresses(), id);
        if (index != -1) {
            return playerList.get(playerId).getProgresses().get(index);
        }
        return null;
    }

    public Currency getCurrency(int playerId, int id) {
        int index = findCurrency(playerList.get(playerId).getCurrencies(), id);
        if (index != -1) {
            return playerList.get(playerId).getCurrencies().get(index);
        }
        return null;
    }

    public Item getItem(int playerId, int id) {
        int index = findItem(playerList.get(playerId).getItems(), id);
        if (index != -1) {
            return playerList.get(playerId).getItems().get(index);
        }
        return null;
    }

    public void createPlayer(Player player) {
        if (playerList.get(player.getPlayerId()) != null) {
            System.out.println("Такой игрок уже существует!");
            return;
        }

        playerList.put(player.getPlayerId(), player);
        databaseService.insert("players", "(id, nickname)", "(" + player.getPlayerId() + ", '" + player.getNickname() + "')");
    }

    public void createProgress(Progress progress) {
        if (playerList.get(progress.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        if (findProgress(playerList.get(progress.getPlayerId()).getProgresses(), progress.getId()) != -1) {
            System.out.println("Прогресс уже существует!");
            return;
        }

        playerList.get(progress.getPlayerId()).addProgress(progress);
        databaseService.insert("progresses", "(id, fk_players, resource_id, score, max_score)",
                "(" + progress.getId() + ","
                        + progress.getPlayerId() + ","
                        + progress.getResourceId() + ","
                        + progress.getScore() + ","
                        + progress.getMaxScore() + ")");
    }

    public void createCurrency(Currency currency) {
        if (playerList.get(currency.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        if (findCurrency(playerList.get(currency.getPlayerId()).getCurrencies(), currency.getId()) != -1) {
            System.out.println("Валюта уже существует!");
            return;
        }

        playerList.get(currency.getPlayerId()).addCurrency(currency);
        databaseService.insert("currencies", "(id, fk_players, resource_id, name, count)",
                "(" + currency.getId() + ","
                        + currency.getPlayerId() + ","
                        + currency.getResourceId() + ",'"
                        + currency.getName() + "',"
                        + currency.getCount() + ")");
    }

    public void createItem(Item item) {
        if (playerList.get(item.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        if (findItem(playerList.get(item.getPlayerId()).getItems(), item.getId()) != -1) {
            System.out.println("Предмет уже существует!");
            return;
        }

        playerList.get(item.getPlayerId()).addItem(item);
        databaseService.insert("items", "(id, fk_players, resource_id, count, level)",
                "(" + item.getId() + ","
                        + item.getPlayerId() + ","
                        + item.getResourceId() + ","
                        + item.getCount() + ","
                        + item.getLevel() + ")");
    }

    public void updatePlayer(Player player) {
        if (playerList.get(player.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        playerList.get(player.getPlayerId()).setNickname(player.getNickname());
        databaseService.update("players", "nickname='" + player.getNickname() + "'", player.getPlayerId());
    }

    public void updateProgress(Progress progress) {
        if (playerList.get(progress.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        int index = findProgress(playerList.get(progress.getPlayerId()).getProgresses(), progress.getId());
        if (index == -1) {
            System.out.println("Прогресса не существует!");
            return;
        }

        playerList.get(progress.getPlayerId()).getProgresses().get(index).setScore(progress.getScore());
        playerList.get(progress.getPlayerId()).getProgresses().get(index).setMaxScore(progress.getMaxScore());
        databaseService.update("progresses", "score=" + progress.getScore() + ", max_score=" + progress.getMaxScore(), progress.getId());
    }

    public void updateCurrency(Currency currency) {
        if (playerList.get(currency.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        int index = findCurrency(playerList.get(currency.getPlayerId()).getCurrencies(), currency.getId());
        if (index == -1) {
            System.out.println("Валюты не существует!");
            return;
        }

        playerList.get(currency.getPlayerId()).getCurrencies().get(index).setName(currency.getName());
        playerList.get(currency.getPlayerId()).getCurrencies().get(index).setCount(currency.getCount());
        databaseService.update("progresses", "name='" + currency.getName() + "', count=" + currency.getCount(), currency.getId());
    }

    public void updateItem(Item item) {
        if (playerList.get(item.getPlayerId()) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        int index = findItem(playerList.get(item.getPlayerId()).getItems(), item.getId());
        if (index == -1) {
            System.out.println("Предмета не существует!");
            return;
        }

        playerList.get(item.getPlayerId()).getItems().get(index).setCount(item.getCount());
        playerList.get(item.getPlayerId()).getItems().get(index).setLevel(item.getLevel());
        databaseService.update("items", "count=" + item.getCount() + ", level=" + item.getLevel(), item.getId());
    }

    public void deletePlayer(int playerId) {
        if (playerList.get(playerId) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        playerList.remove(playerId);
        databaseService.delete("players", playerId);
    }

    public void deleteProgress(int playerId, int id) {
        if (playerList.get(playerId) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        playerList.get(playerId).getProgresses().remove(findProgress(playerList.get(playerId).getProgresses(), id));
        databaseService.delete("progresses", id);
    }

    public void deleteCurrency(int playerId, int id) {
        if (playerList.get(playerId) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        playerList.get(playerId).getCurrencies().remove(findCurrency(playerList.get(playerId).getCurrencies(), id));
        databaseService.delete("currencies", id);
    }

    public void deleteItem(int playerId, int id) {
        if (playerList.get(playerId) == null) {
            System.out.println("Такого игрока не существует!");
            return;
        }

        playerList.get(playerId).getItems().remove(findItem(playerList.get(playerId).getItems(), id));
        databaseService.delete("items", id);
    }

    public int findProgress(List<Progress> progresses, int id) {
        for (Progress progress : progresses) {
            if (progress.getId() == id) {
                return progresses.indexOf(progress);
            }
        }
        return -1;
    }

    public int findCurrency(List<Currency> currencies, int id) {
        for (Currency currency : currencies) {
            if (currency.getId() == id) {
                return currencies.indexOf(currency);
            }
        }
        return -1;
    }

    public int findItem(List<Item> items, int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return items.indexOf(item);
            }
        }
        return -1;
    }
}
