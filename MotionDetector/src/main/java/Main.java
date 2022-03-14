import java.io.FileNotFoundException;

public class Main {

    public static void main(String args[]) {
        try {
            new Camera().run();
        } catch (FileNotFoundException e) {
            System.out.println("Some files were not found");
        }
    }
}
