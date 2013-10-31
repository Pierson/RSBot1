package data;

public enum Bar {
    NONE(-1, -1, -1, -1, -1, -1),
    BRONZE(2349, 436, 438, 14, 14, 14),
    IRON(2351, 440, 440, 28, 28, 28),
    STEEL(2353, 440, 453, 9, 19, 9),
    SILVER(2355, 442, 442, 28, 28, 28),
    GOLD(2357, 444, 444, 28, 28, 28),
    MITHRIL(2359, 447, 453, 5, 23, 5),
    ADAMANT(2361, 449, 453, 4, 24, 4),
    RUNE(2363, 451, 453, 3, 25, 3),
    CANNONBALL(2353, 2353, 4, 28, 27, 1);
    
    private int barId;
    private int ingredient1;
    private int ingredient2;
    private int withdrawAmt;
    private int withdrawAmt2;
    private int maxBar;
    
    Bar(final int barId, final int ingredient1, final int ingredient2, final int withdrawAmt, final int withdrawAmt2, final int maxBar) {
	this.barId = barId;
	this.ingredient1 = ingredient1;
	this.ingredient2 = ingredient2;
	this.withdrawAmt = withdrawAmt;
	this.withdrawAmt2 = withdrawAmt2;
	this.maxBar = maxBar;
    }
    
    public int getBar() {
	return barId;
    }
    
    public int getFirstIngredient() {
	return ingredient1;
    }
    
    public int getSecondIngredient() {
	return ingredient2;
    }
    
    public int getWithdrawAmt() {
	return withdrawAmt;
    }
    
   public int getSecondWithdrawAmt() {
       return withdrawAmt2;
   }
   
   public int getMaxBar() {
       return maxBar;
   }
}
