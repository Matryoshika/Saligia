package Matryoshika.mods.saligia.API.soulsystem;

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
