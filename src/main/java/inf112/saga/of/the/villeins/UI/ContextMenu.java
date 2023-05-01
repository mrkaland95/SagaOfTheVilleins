package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;

public class ContextMenu extends Table {
    private TextButton attackButton;
    private TextButton moveButton;
    private TextButton examineButton;
    private Label tileInfoLabel;

    public ContextMenu(Skin skin) {
        super(skin);

        attackButton = new TextButton("Attack", skin);
        moveButton = new TextButton("Move", skin);
        examineButton = new TextButton("Examine", skin);
        tileInfoLabel = new Label("", skin);

        // Add buttons and label to the table
        add(attackButton).pad(5);
        add(moveButton).pad(5);
        add(examineButton).pad(5);
        row();
        add(tileInfoLabel).colspan(3).pad(5).align(Align.center);

        // Set the table's layout properties
        pack();
        setTouchable(Touchable.enabled);
    }

    public TextButton getAttackButton() {
        return attackButton;
    }

    public TextButton getMoveButton() {
        return moveButton;
    }

    public TextButton getExamineButton() {
        return examineButton;
    }

    public void setTileInfo(String tileInfo) {
        tileInfoLabel.setText(tileInfo);
    }
}
