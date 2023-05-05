package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.GameScreen;

import java.util.List;

public record TilePosition(int x, int y) {

    static double hexagonDimension = 200d;

    /** Finner en korresponderende Tile utifra ett gitt verdens koordinat.
     * @param worldCoordinate
     * @return
     */
    public static TilePosition findHexTile(Vector2 worldCoordinate) {
        // Adaptert herfra: https://gamedevelopment.tutsplus.com/tutorials/creating-hexagonal-minesweeper--cms-28655
        double hexTileX = Math.floor(worldCoordinate.x / hexagonDimension);
        double hexTileY = Math.floor(worldCoordinate.y / (hexagonDimension * (3d/4d)));
        double dX = (worldCoordinate.x) % hexagonDimension;
        double dY = (worldCoordinate.y) % (hexagonDimension * (3d/4d));
        double slope = (hexagonDimension / 4d )/ (hexagonDimension / 2d);
        double caldY = dX * slope;
        double delta = (hexagonDimension/4) - caldY;
        if (hexTileY % 2 == 0) {
            if (Math.abs(delta) > dY) {
                if(delta > 0){
                    hexTileX --;
                    hexTileY --;
                }
                else{
                    hexTileY --;
                }
            }
        } else {
            if (dX > (hexagonDimension / 2d)) {
                if (dY < ((hexagonDimension / 2d) - caldY)) {
                    hexTileY--;
                }
            } else {
                if (dY > caldY) {
                    hexTileX--;
                } else {
                    hexTileY--;
                }
            }
        }
        return new TilePosition((int) hexTileX, (int) hexTileY);
    }

    /**
     * Regner ut koordinatet til midten av en gitt Tile.
     *
     * @param tilePosition En Tile posisjon.
     * @return Midten av den gitte "Tilen"
     */
    public static Vector2 findVectorCoordinate(TilePosition tilePosition) {
        double doubleX = tilePosition.x();
        double doubleY = tilePosition.y();
        double x = 0.0;
        double y = 0.0;
        // En tile har forskjellige "offsets" utifra om den er oddetall eller partall.
        if (tilePosition.y() % 2 == 0) {
            x = hexagonDimension / 2 + hexagonDimension * doubleX;
            y = hexagonDimension / 2 + hexagonDimension / 4 * 3 * doubleY;
        } else {
            x = hexagonDimension + hexagonDimension * doubleX;
            y = ((hexagonDimension / 4) + hexagonDimension) + (hexagonDimension / 4) * 3 * (doubleY - 1);
        }


        float floatX = (float) x;
        float floatY = (float) y;
        return new Vector2(floatX, floatY);
    }

    /** Regner ut avstanden mellom to tiles(altså hvor mange tiles som er i mellom to tiles).
     * Dette er nyttig for å f.eks kunne sjekke om en karakter er nærmt nok til å angripe en annen.
     * OBS: Virker kun for tileoppsettet vi bruker, dvs. "Odd" stagger index og ved "right-down" render order.
     * @param tile1
     * @param tile2
     * @return Avstanden mellom tile1 og tile2.
     */
    public static double hexDistance(TilePosition tile1, TilePosition tile2) {
        int a0 = tile1.x();
        int a1 = tile2.x();

        int b0 = tile1.y();
        int b1 = tile2.y();

        double x0 = a0 - Math.floor(b0/2);
        double y0 = b0;
        double x1 = a1 - Math.floor(b1/2);
        double y1 = b1;

        double dx = x1 - x0;
        double dy = y1 - y0;

        double max1 = Math.max(Math.abs(dx), Math.abs(dy));
        return Math.max(max1, Math.abs(dx+dy));
    }

    /** Setter dimensjonen på sekskantene på kartet, slik at utregninger blir gjort riktig.
     * @param hexagonDimension
     */
    public static void setHexagonDimension(double hexagonDimension) {
        TilePosition.hexagonDimension = hexagonDimension;
    }

    /** Prøver å finne en karakter på et gitt koordinat,
     * @param coordinate En vector2 posisjon.
     * @return et ICharacter objekt, hvis den finnes på tilen som tilhører koordinatet.
     */
    public static ICharacter getCharacterOnCoordinate(Vector2 coordinate, List<ICharacter> characterList) {
        if (coordinate == null) return null;
        TilePosition clickedTile = TilePosition.findHexTile(coordinate);
        for (ICharacter character : characterList) {
            TilePosition characterTile = TilePosition.findHexTile(character.getCurrentPosition());
            if (clickedTile.equals(characterTile)) {
                return character;
            }
        }
        return null;
    }
}


