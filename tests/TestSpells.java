import java.io.IOException;
import java.util.ArrayList;

public class TestSpells {

    public static void main(String[] args) throws IOException {
        ArrayList<Spell> spells;

        FileInput fileInput = new FileInput();
        fileInput.initSpells();
        spells = fileInput.getSpells();
        for (Spell s: spells) {
            System.out.printf("%s\n", s.toString());
        }
    }
}
