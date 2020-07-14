package business;

import javax.swing.*;
import java.util.ArrayList;
import presentation.Observer;

public class Observable {

    private ArrayList<Observer> obs;

    public Observable() {
        obs = new ArrayList<>();
    }

    public void createNewObserver(Observer observer, String s) {
        JFrame frame = new JFrame("Observer frame");
        JOptionPane.showMessageDialog(frame, "Info: added a new observer for " + s);
        obs.add(observer);
    }

    public void announceObserver() {
        for (Observer o : obs) {
            o.monitor();
        }
    }
}