package services;

import classes.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MapperService {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Player> getPlayersFromJsonFile(File filename) {
        try {
            return List.of(mapper.readValue(filename, Player[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String printObject(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                         .writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
