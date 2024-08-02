package ru.binentity.quantum;

import org.jsfml.graphics.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import ru.binentity.quantum.components.LineShape;
import ru.binentity.quantum.components.PlanetEntity;
import ru.binentity.quantum.ui.DebugWindow;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Application {

    private final String TITLE = "0.01 Model";
    private final int WINDOW_WIDTH = 5000;
    private final int WINDOW_HEIGHT = 3000;

    private final VideoMode mode;
    private final RenderWindow window;
    private final Clock clock;
    private final Font debug;
    private final Text info;
    private final Text positionInfo;

    private final List<PlanetEntity> planets;
    private final List<LineShape> lines;
    public final Clock clocking;
    private Logger logger;

    public Application() {
        mode        = new VideoMode(WINDOW_WIDTH, WINDOW_HEIGHT);
        window      = new RenderWindow(mode, TITLE);
        clock       = new Clock();
        planets     = new ArrayList<>();
        lines       = new ArrayList<>();
        debug       = new Font();
        clocking    = new Clock();

        info         = new Text();
        positionInfo = new Text();

        window.setFramerateLimit(144); // need crossplatform value

        logger = Logger.getLogger("Rendering info");


        planets.add(new PlanetEntity(new Vector2f(1500, 1500), new Vector2f(0.1f, 0.1f), 10));
        planets.add(new PlanetEntity(new Vector2f(1400, 1200), new Vector2f(0.1f, 0.1f), 10));
        planets.add(new PlanetEntity(new Vector2f(2000, 2000), new Vector2f(0, 0), 100));
        planets.add(new PlanetEntity(new Vector2f(2000, 1200), new Vector2f(0.1f, 0.1f), 10));
        planets.add(new PlanetEntity(new Vector2f(1500, 1500), new Vector2f(0.1f, 0.1f), 10));
        planets.add(new PlanetEntity(new Vector2f(1400, 1200), new Vector2f(0.1f, 0.1f), 10));
        planets.add(new PlanetEntity(new Vector2f(1000, 1000), new Vector2f(0.1f, 0.1f), 10));
        planets.add(new PlanetEntity(new Vector2f(900, 1200),  new Vector2f(0.1f, 0.1f), 10));


        planets.add(new PlanetEntity(new Vector2f((float) (WINDOW_WIDTH / 2 - 25), (float) (WINDOW_HEIGHT / 2 - 25)),
                new Vector2f(0, 0), 20000));

        lines.add(new LineShape(new Vector2f(1500 + 25, 1500 + 25), new Vector2f(500 + 25, 500 + 25)));

        try {
            debug.loadFromFile(Path.of("/home/top/mono.ttf"));  //NOTE: IO method

            info.setString("Planet system!");
            info.setFont(debug);
            info.setCharacterSize(24);
            info.setPosition(100, 100);

            positionInfo.setFont(debug);
            positionInfo.setCharacterSize(24);
            positionInfo.setColor(Color.GREEN);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        boolean distanceLine = false;

        while (window.isOpen()) {

            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }

                if (event.type == Event.Type.MOUSE_MOVED) {
                    int x = Mouse.getPosition(window).x;
                    int y = Mouse.getPosition(window).y;
                    positionInfo.setString("x: " + x + " y:" + y);
                    positionInfo.setPosition(new Vector2f(x + 40, y + 40));
                }

                if (event.type == Event.Type.KEY_PRESSED) {
                    if (Keyboard.isKeyPressed(Keyboard.Key.O)) {
                        distanceLine = !distanceLine;
                    }
                }
            }

            if (Keyboard.isKeyPressed(Keyboard.Key.RETURN) ||
                    Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)) {
                window.close();
            }


            window.clear(Color.BLACK);

            for (PlanetEntity planet : planets) {
                for (PlanetEntity samePlanet : planets) {
                    if (planet.equals(samePlanet)) {
                        continue;
                    }

                    if (planet.distanceTo(samePlanet) < 10) {
                        continue;
                    }

                    planet.update(samePlanet, clock);
                    window.draw(planet);

                    if (distanceLine) {
                        for (LineShape shape : lines) {
                            shape.update(planet.getPosition(), samePlanet.getPosition());
                            window.draw(shape);
                        }
                    }

                }
            }

            clock.restart();
            window.draw(new DebugWindow());
            window.draw(positionInfo);
            window.draw(info);
            window.display();
        }

    }
}
