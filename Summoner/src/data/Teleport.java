package data;

public enum Teleport {   
    //DAEMONHEIM("Daemonheim"),
    TAVERLY("Taverly");

    private final String teleType;
    
    Teleport(final String teleType) {
	this.teleType = teleType;
    }
    
    public String toString() {
	return teleType;
    }
    
}
