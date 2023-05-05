package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import inf112.saga.of.the.villeins.Characters.ICharacter;

public class ContextMenu extends Table {
    private TextButton attackButton;
    private TextButton moveButton;
    private Table characterStatsTable;
    private Label tileInfoLabel;
    private Label characterID;
    private Label characterMaxHealthLabel;
    private Label characterCurrentHealthLabel;
    private Label characterStrength;
    private Label characterDefense;

    public ContextMenu(Skin skin) {
        super(skin);

        attackButton = new TextButton("Attack", skin, "default");
        moveButton = new TextButton("Move", skin, "default");
        tileInfoLabel = new Label("", skin);

        createButtonPanel();
        createCharacterStatsPanel();

        pack();
        setTouchable(Touchable.enabled);
    }


    private void createButtonPanel() {
        // Legger til knapper i tabellen.
        add(attackButton).pad(5).row();
        add(moveButton).pad(5).row();
        row();
        add(tileInfoLabel).colspan(3).pad(5).align(Align.center);
    }


    private void createCharacterStatsPanel() {
        Skin skin = super.getSkin();

        characterID = new Label("", skin);
        characterMaxHealthLabel = new Label("", skin);
        characterCurrentHealthLabel = new Label("", skin);
        characterDefense = new Label("", skin);
        characterStrength = new Label("", skin);

        characterStatsTable = new Table(skin);
        characterStatsTable.right();
        characterStatsTable.pad(10);

        // Initsierer tabellen og teksten.
        characterStatsTable.add(new Label("Character Stats", skin)).colspan(2);
        characterStatsTable.row();

        characterStatsTable.add(new Label("Name: ", skin));
        characterStatsTable.add(characterID);
        characterStatsTable.row();

        characterStatsTable.add(new Label("Max Health: ", skin));
        characterStatsTable.add(characterMaxHealthLabel);
        characterStatsTable.row();

        characterStatsTable.add(new Label("Current Health: ", skin));
        characterStatsTable.add(characterCurrentHealthLabel);
        characterStatsTable.row();

        characterStatsTable.add(new Label("Strength: ", skin));
        characterStatsTable.add(characterStrength);
        characterStatsTable.row();

        characterStatsTable.add(new Label("Defense: ", skin));
        characterStatsTable.add(characterDefense);
        characterStatsTable.row();

        add(characterStatsTable);
    }

    public void updateCharacterStats(ICharacter character) {
        if (character == null) {
            characterStatsTable.setVisible(false);
            return;
        } else {
            characterStatsTable.setVisible(true);
        }

        characterID.setText(character.toString());
        characterMaxHealthLabel.setText(character.getMaxHealth());
        characterCurrentHealthLabel.setText(character.getCurrentHealth());
        characterStrength.setText(character.getStrength());
        characterDefense.setText(character.getDefense());
    }


    public TextButton getAttackButton() {
        return attackButton;
    }

    public TextButton getMoveButton() {
        return moveButton;
    }

    public void setTileInfo(String tileInfo) {
        tileInfoLabel.setText(tileInfo);
    }
}
