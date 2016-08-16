package se.Matryoshika.Saligia.API.Soulsystem;

public interface IAnimunShooter {
	
	/**
	 * How fast this tile's standard output/t is
	 */
	public int outputMaxSpeed(int maxOutput);
	
	/**
	 * Used when this contains less than {@link #outputMaxSpeed(int)} but Animun can still be taken
	 */
	public int outputMinSpeed(int minOutput);

}
