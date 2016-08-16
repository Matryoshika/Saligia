package se.Matryoshika.Saligia.API.Soulsystem;

/**
 * This interface was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
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
