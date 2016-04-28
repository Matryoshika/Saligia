package Matryoshika.mods.saligia.API.soulsystem;

public interface IAnimunStorage{
	
	/**
	 * How much Animun this tile can hold at once. Should never be higher than the same tier's own proper storage
	 */
	public int ownStorage();
	
	/**
	 * How much Animun it currently stores. Never higher than {@link #ownStorage(int)}
	 */
	public int currentAmount();
	
	/**
	 * Which tier the storage has. Pure Saligia uses 1->3 for storage
	 */
	public int storageTier();
	
	public void addToAmount(int amount);
}
