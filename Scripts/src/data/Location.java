package data;

import org.powerbot.script.wrappers.Tile;

public enum Location {
    NONE(new Tile(0, 0, 0), -1, new Tile(0, 0, 0)),
    AL_KHARID(new Tile(3275, 3190, 0), 76293, new Tile(3270, 3166, 0)),
    FALADOR(new Tile(2973, 3369, 0), 11666, new Tile(3013, 3356, 0)),
    EDGEVILLE(new Tile(3108, 3501, 0), 26814, new Tile(3096, 3497, 0));
    
    private final Tile location;
    private final int furnace;
    private Tile bankTile;
    
    Location(final Tile location, final int furnace, final Tile bankTile) {
	this.location = location;
	this.furnace = furnace;
	this.bankTile = bankTile;
    }
    
    public Tile getTile() {
	return location;
    }
    
    public int getFurnace() {
	return furnace;
    }
    
    public Tile getBankTile() {
	return bankTile;
    }

}
