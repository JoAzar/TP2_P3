package Interfaz;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.*;

public class jMapTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mapa con JMapViewer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JMapViewer());
        frame.setVisible(true);
    }
}
