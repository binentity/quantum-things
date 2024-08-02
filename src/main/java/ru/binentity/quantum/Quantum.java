package ru.binentity.quantum;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Quantum {

    public static Logger message() {
        return Logger.getLogger("Test info");
    }

    public static void main(String[] args) {
        message().log(Level.INFO, "Starting application...");
        Application app = new Application();
        app.run();
        message().log(Level.INFO, "Closing application...");
    }
}
