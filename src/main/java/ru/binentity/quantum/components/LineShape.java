package ru.binentity.quantum.components;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class LineShape implements Drawable {

    private final Vector2f start;
    private final Vector2f end;
    private final Vertex[] linePresentation;

    public LineShape(Vector2f start, Vector2f end) {
        this.start = start;
        this.end = end;
        linePresentation = new Vertex[2];
        setPosition();
    }

    public void setPosition(Vector2f start, Vector2f end) {
        linePresentation[0] = new Vertex(start, Color.GREEN);
        linePresentation[1] = new Vertex(end, Color.GREEN);
    }

    private void setPosition() {
        linePresentation[0] = new Vertex(start, Color.GREEN);
        linePresentation[1] = new Vertex(end, Color.GREEN);
    }

    @Override
    public void draw(RenderTarget rt, RenderStates rs) {
        rt.draw(linePresentation, PrimitiveType.LINES);
    }

    public void update(Vector2f start, Vector2f end) {
        start = new Vector2f(start.x + 25, start.y + 25);
        end = new Vector2f(end.x + 25, end.y + 25);
        setPosition(start, end);
    }
}
