/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileRitual extends CustomTileClass implements ITileMasterRitual{

	@Override
	public void setRitualMultiblocks(Object[][] coordArray) {
		multiblocks.put(getName(), coordArray);
		
	}

	@Override
	public Object[][] getRitualMultiblocks() {
		String ritualName = getName();
		return (Object[][]) multiblocks.get(ritualName);
	}

}
