package Dev_J_120;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {
    
    public static void main(String[] args) {

        try {
             Starter.start();
        } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
