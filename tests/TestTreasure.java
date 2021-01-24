import java.io.IOException;
import java.util.ArrayList;

public class TestTreasure {

    public static void main(String[] args) throws IOException {
        ArrayList<Treasure> treasures;

        FileInput fileInput = new FileInput();
        fileInput.initTreasure();
        treasures = fileInput.getTreasure();
        for (Treasure t: treasures) {
            System.out.printf("%s\n", t.toString());
        }
    }
}
