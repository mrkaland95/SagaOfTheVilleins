package inf112.saga.of.the.villeins.UI;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EndTurnButton extends Button {
    private ShapeRenderer renderer;
    private Color color;

    public EndTurnButton(Color color, ShapeRenderer renderer) {
        super(new ButtonStyle());
        this.color = color;
        this.renderer = renderer;

        // Add a click listener to handle the "End Turn" action
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Call the method to handle ending the turn
                endTurn();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.setAutoShapeType(true);
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());

        // Definerer formen på sekskanten.
        float[] vertices = new float[] {
                getX() + getWidth() / 2, getY(),
                getX() + getWidth(), getY() + getHeight() / 4,
                getX() + getWidth(), getY() + getHeight() * 3 / 4,
                getX() + getWidth() / 2, getY() + getHeight(),
                getX(), getY() + getHeight() * 3 / 4,
                getX(), getY() + getHeight() / 4
        };


        // Draw the filled hexagon
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1, 0, 0, 1);
        renderer.polygon(vertices);
        renderer.end();




//        // Draw the hexagon outline
//        renderer.begin(ShapeRenderer.ShapeType.Line);
//        renderer.setColor(Color.GREEN);
//        renderer.polygon(vertices);
//        renderer.end();
    
        batch.begin();
    
//        if (text != null) {
//            font.draw(batch, text, getX() + (getWidth() / 2) - (layout.width / 2), getY() + (getHeight() / 2) + (layout.height / 2));
    }


    public void endTurn() {
        // Add your logic for ending the turn here
    }
}

