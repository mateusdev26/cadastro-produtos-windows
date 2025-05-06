package util;

import domain.Produto;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ProdutoUtil {
    private static final Path FILE = Paths.get("produtos.txt");

    private static void createFileifNotExists() {

        if (FILE.toFile().exists()) return;
        try {
            Files.createFile(FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private static void deleteAndCreate(){
        try {
            Files.delete(FILE);
            Files.createFile(FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public static void ser(Map<String, List<Produto>> map) {
        createFileifNotExists();
        deleteAndCreate();
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(FILE,StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Map<String, List<Produto>> unser() {
        if (!Files.exists(FILE)) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(FILE))) {
            return (Map<String, List<Produto>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler arquivo de produtos: " + e.getMessage());
            return new HashMap<>();
        }
    }


}
