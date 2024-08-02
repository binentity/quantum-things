package ru.binentity.quantum.components;

import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

public class PlanetEntity extends QuantableEntity implements Drawable {

    public PlanetEntity(Vector2f position, Vector2f speed, float mass) {
        super(position, speed, mass);
    }

    @Override
    public void draw(RenderTarget rt, RenderStates rs) {
        rt.draw(circle);
    }
    
    public void update(PlanetEntity planet, Clock clock) {
        //Time time = clock.restart();

        speed = new Vector2f((float) (speed.x + (0.0000005 * planet.getMass() / distanceTo(planet) * distanceTo(planet)) *
                        ( planet.getPosition().x - position.x) / distanceTo(planet) ),

                (float) ( speed.y + (0.0000005 * planet.getMass() / distanceTo(planet) * distanceTo(planet)) *
                                        (planet.getPosition().y - position.y) / distanceTo(planet)));       // * time.asSeconds());

        position = new Vector2f(position.x + speed.x, position.y + speed.y);
        circle.setPosition(position);
    }

}
