package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class FileJsoner <T>{
    File file;

    public FileJsoner(String filename) {
        file = new File(System.getProperty("user.dir"), filename);
    }

    public void ecrire(T object) {
        try {
            new ObjectMapper().writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T lire(TypeReference<T> type) {
        try {
            return new ObjectMapper().readValue(file, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
