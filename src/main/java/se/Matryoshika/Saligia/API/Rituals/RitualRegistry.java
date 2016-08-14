/**
 * 
 */
package se.Matryoshika.Saligia.API.Rituals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.tileentity.TileEntity;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class RitualRegistry {
	
	/**
	 * Stores the multiblock structure in an Object[][]
	 * using {x,y,z,Block}
	 */
	private static HashMap multiblocks = new HashMap();

	/**
	 * Stores each Ritual, and is used by the ItemRitualActivator to get access to the getRitualname()
	 * and thus be able to lookup the hashmaps and spawn the proper BlockRitualMaster with correct
	 * tile.
	 */
	private static List<String> ritualNames = new ArrayList<String>();
	
	/**
	 * Stores each Tile with it's name as a reference.
	 */
	private static HashMap tiles = new HashMap();
	private static HashMap reverseTile = new HashMap();
	
	/**
	 * Stores each Block with it's name as a reference.
	 */
	private static HashMap blocks = new HashMap();
	
	public static void addRitual(String name, Object[][] multiblock, TileEntity tile, BlockRitualMaster block){
		multiblocks.put(name, multiblock);
		ritualNames.add(name);
		tiles.put(name, tile);
		reverseTile.put(tile, name);
		blocks.put(name, block);
	}
	
	public static Object[][] getMultiblock(String name){
		return (Object[][]) multiblocks.get(name);
	}
	
	public static List<String> getAllNames(){
		return ritualNames;
	}
	
	public static TileEntity getTile(String name){
		return (TileEntity) tiles.get(name);
	}
	
	public static BlockRitualMaster getBlock(String name){
		return (BlockRitualMaster) blocks.get(name);
	}
	
	public static String getNameFromTile(TileEntity tile){
		return (String) reverseTile.get(tile);
	}
}
