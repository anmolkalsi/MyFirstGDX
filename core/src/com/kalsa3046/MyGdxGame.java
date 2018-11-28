package com.kalsa3046;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeBatch;
    private Paddle player1;
    private Paddle player2;
    private Ball ball;
    private OrthographicCamera cam;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        cam = new OrthographicCamera(800, 600);
        cam.position.x = (400);
        cam.position.y = (300);
        cam.update();

        player1 = new Paddle(10, 250, 25, 100, 2);
        player2 = new Paddle(765, 250, 25, 100, 2);
        ball = new Ball(390, 290, 20, 20, 2);

//        how to load in a texture:
//        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.moveUp();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1.moveDown();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player2.moveUp();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2.moveDown();
        }

        ball.move();
        if (ball.getTop() > 600 || ball.getBottom() > 800) {
            ball.bounceY();
        }

        ball.move();
        if (ball.getRight() > 600 || ball.getLeft() > 800) {
            ball.bounceY();
        }

        shapeBatch.setProjectionMatrix(cam.combined);
        shapeBatch.begin(ShapeType.Line);
        player1.draw(shapeBatch);
        player2.draw(shapeBatch);
        ball.draw(shapeBatch);
        shapeBatch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
