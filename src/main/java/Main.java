import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Formatter formatter = new Formatter(PrettyFormatRegistry.defaultInstance);
//        JFrame jFrame = new JFrame();
//        jFrame.add(new JButton("Click me!"));
//        jFrame.add(new JLabel("Cool"));

        FileWriter out = new FileWriter("test.out.txt");
        formatter.format(new JButton(), out);
    }
}
