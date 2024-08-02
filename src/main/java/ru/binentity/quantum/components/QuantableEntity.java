package ru.binentity.quantum.components;

import org.jsfml.graphics.CircleShape;
import org.jsfml.system.Vector2f;

public abstract class QuantableEntity {

    protected Vector2f    position;
    protected Vector2f    speed;

    protected float       mass;
    protected CircleShape circle;

    public QuantableEntity(Vector2f position, Vector2f speed, float mass) {
        this.position   = position;
        this.speed      = speed;
        this.mass       = mass;
        this.circle     = new CircleShape(5);
        circle.setPosition(position);
    }

    public float distanceTo(PlanetEntity p) {
        return (float) Math.sqrt(Math.pow(position.x - p.getPosition().x, 2) +
                Math.pow(position.y - p.getPosition().y, 2));
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getMass() {
        return mass;
    }
}
