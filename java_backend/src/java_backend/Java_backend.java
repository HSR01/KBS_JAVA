package java_backend;

import GUI_helpers.AvailabilityCheckers;
import GUI_helpers.RunGUI;

public class Java_backend {
    public static void main(String[] args) {
        Runnable gui = new RunGUI();
        Runnable connectionChecker = new AvailabilityCheckers(120);
    }  
}
