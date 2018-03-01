package com.krzysztofczereczon.spaceinvaders.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.krzysztofczereczon.spaceinvaders.GameInfo;

public class AsteroidMedium extends Sprite{

    private Body body;

    public AsteroidMedium( Vector2 respawnPosition, Transform playerPos, World world) {
        super(new Texture("meteorMedium.png"));
        createBody(world);
        body.setTransform(respawnPosition.x, respawnPosition.y,0);

        float velocityX = playerPos.getPosition().x - respawnPosition.x;
        float velocityY =  playerPos.getPosition().y - respawnPosition.y;

        body.setLinearVelocity(new Vector2(1.25f * velocityX / Math.abs(velocityX), 1.25f * velocityY / Math.abs(velocityY)));
        //body.setAngularVelocity(40);
    }

    private void createBody(World world) {
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);

        body = world.createBody(bodyDef);
        body.setUserData(new BodyDataObject(this, "medium", false));

        CircleShape shape = new CircleShape();
        shape.setRadius(getHeight() / 2 / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.restitution = 1f;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef).setUserData("medium");
        shape.dispose();
    }

    public void update(SpriteBatch batch) {
        if(body != null)
        batch.draw(this, body.getPosition().x - getWidth() / 2 / GameInfo.PPM, body.getPosition().y - getHeight() / 2 / GameInfo.PPM, getWidth() / 2 / GameInfo.PPM, getHeight() / 2 / GameInfo.PPM, getWidth() / GameInfo.PPM, getHeight() / GameInfo.PPM, 1, 1, body.getAngle());
    }
}
