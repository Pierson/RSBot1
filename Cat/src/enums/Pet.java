package enums;

public enum Pet {
    NONE(-1, -1),
    TROLL(23030, 14846),
    KITTEN(1559, 765),
    CLOCKWORK(7771, 3598);
    
    private final int petId;
    private final int groundId;
    
    Pet(final int petId, final int groundId) {
	this.petId = petId;
	this.groundId = groundId;
    }
    
    public int getId() {
	return petId;
    }
    
    public int getGroundId() {
	return groundId;
    }
}
