package path;

import org.powerbot.script.wrappers.Tile;

public enum Path {
    NORTH_WEST(new Tile(3148, 3501, 0)),
    NORTH_EAST(new Tile(3180, 3501, 0)),
    SOUTH_WEST(new Tile(3149, 3482, 0)),
    SOUTH_EAST(new Tile(3180, 3482, 0));
    
    private final Tile tile;
    
    Path(final Tile tile) {
	this.tile = tile;
    }
    
    public Tile getPath() {
	return tile;
    }
}
