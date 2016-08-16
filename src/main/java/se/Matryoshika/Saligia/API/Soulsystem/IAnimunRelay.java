package se.Matryoshika.Saligia.API.Soulsystem;

/**
 * This interface was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface IAnimunRelay {
	
	/**
	 * Wether or not if this tile can link to tiles between SOURCE & DEST
	 */
	public boolean canChainIO(boolean bool);
	
	/**
	 * Wether or not if this tile can shoot straight ahead and/or to any sides
	 */
	public boolean canShootDegrees(boolean ninety, boolean oneEighty, boolean twoSeventy);
	
	
	/**
	 * How many ticks it takes to generate one batch of Animun
	 */
	public int ticksBetweenTransfer(int ticks);

}
