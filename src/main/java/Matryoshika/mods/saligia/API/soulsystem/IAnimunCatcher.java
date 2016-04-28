package Matryoshika.mods.saligia.API.soulsystem;

public interface IAnimunCatcher {
	
	/**
	 * How fast this tile's standard input/t is
	 */
	public int inputMaxSpeed(int maxInput);
	
	/**
	 * Used when this contains less than {@link #inputMaxSpeed(int)} but Animun can still be gained
	 */
	public int inputMinSpeed(int minInput);

}
