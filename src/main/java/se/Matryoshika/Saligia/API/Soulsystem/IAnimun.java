/**
 * 
 */
package se.Matryoshika.Saligia.API.Soulsystem;

/**
 * This class was created by Matryoshika Oct 31, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface IAnimun {

	/**
	 * Adds a packet of size {@link #inputPacket()} to whatever implements this
	 * Meant for 'constant' usage per tick; Like a generator.  
	 */
	public void input();
	
	/**
	 * Removes a packet of size{@link #outputPacket()} of whatever implements this
	 */
	public void output();
	
	/**
	 * Transfers Animun from 'start', which implements IAnimun, to 'finish' which also implements IAnimun
	 */
	public void transfer(IAnimun start, IAnimun finish);
	
	/**
	 * @param amount : Sets the amount in whatever implements this
	 */
	public void set(int amount);
	
	/**
	 * The max amount of Animun that can be stored in whatever implements this
	 */
	public float maxAmount();
	
	/**
	 * Returns the current amount of Animun stored in whatever implements this
	 */
	public int getCurrentAmount();
	
	/**
	 * How much this can possibly output per tick
	 */
	public int outputPacket();
	
	/**
	 * How much this can possibly input per tick
	 */
	public int inputPacket();
	
	/**
	 * Returns the percentage of the fill-level of whatever implements this
	 */
	public float getFillAmountPercentage();
	
	/**
	 * Returns the tier of this Animun-energy (1-3)
	 * Used to calculate Saligia base storages. 
	 */
	public float getTier();
	
	/**
	 * Returns true if this is an item. Storage capacity is also reduced compared to in-world storage of same tier.
	 */
	public boolean isHandHeld();
	
	/**
	 * Returns true if Animun can be used to repair this item
	 */
	public boolean repairable();
}
