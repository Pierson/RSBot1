package data;

public enum Bone {
    NONE(-1),
    NORMAL(526),
    BURNT(528),
    BAT(530),
    BIG(532),
    BABY_DRAG(534),
    DRAGON(536),
    WOLF(2859),
    ZOGRE(4812),
    JOGRE(3125),
    FAYRG(4831),
    OURG(4834),
    DAGANNOTH(6739),
    FROST(18832);
    
    private final int boneId;
    
    Bone(final int boneId) {
	this.boneId = boneId;
    }
    
    public int getBone() {
	return boneId;
    }
}
