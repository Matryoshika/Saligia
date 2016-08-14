/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface ITileMasterRitual{
	
	/**
	 * An object[][] with {x,y,z,Block}
	 */
	static HashMap multiblocks = new HashMap();
	

	/**
	 * @param coordArray : A List of {x,y,z,Block} objects.
	 * Execution: multiblocks.put(getName(), coordArray);
	 */
	public void setRitualMultiblocks(Object[][] coordArray);
	
	/**
	 * @return A list of Object[][] representing {x,y,z,Block}.
	 * Used by anything that implement IRitualChecker to verify
	 * if the block at the relative position equals the 4th 
	 * object.
	 * Execution: String ritualName = getName();
	 * return (List<Object[][]>) multiblocks.get(ritualName);
	 */
	public Object[][] getRitualMultiblocks();
}
