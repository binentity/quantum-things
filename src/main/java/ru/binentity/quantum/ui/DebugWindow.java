package ru.binentity.quantum.ui;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DebugWindow implements Drawable {

    private RectangleShape UIPanel;
    private RectangleShape UIElement;
    private List<RectangleShape> UIElements;

    private Font UIFont;
    private Text UIText;

    public DebugWindow() {
        UIPanel = new RectangleShape(new Vector2f(500, 500));

        UIFont = new Font();
        UIText = new Text();

        initialize();
        initializeText();
    }

    private void initialize() {
        UIPanel.setSize(new Vector2f(1000, 1000));
        UIPanel.setFillColor(new Color(100, 100, 100, 128));
        UIPanel.setPosition(new Vector2f(100, 100));
    }

    private void initializeText() {
        try {
            UIFont.loadFromFile(Path.of("/home/top/mono.ttf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UIText.setString("Planet system!");
        UIText.setFont(UIFont);
        UIText.setCharacterSize(30);
        UIText.setPosition(100, 100);
    }


    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        renderTarget.draw(UIPanel);
    }
}
