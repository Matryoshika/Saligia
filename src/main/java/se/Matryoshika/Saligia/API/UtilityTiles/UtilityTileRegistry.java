/**
 * 
 */
package se.Matryoshika.Saligia.API.UtilityTiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;
import se.Matryoshika.Saligia.Content.Blocks.Utility.UtilityBlock;

/**
 * This class was created by Matryoshika Oct 10, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class UtilityTileRegistry {

	private static List<String> utilityNames = new ArrayList<String>();
	
	/**
	 * Stores each Tile.class with it's name as a reference.
	 */
	private static HashMap tiles = new HashMap();
	private static HashMap reverseTile = new HashMap();
	
	/**
	 * Stores each Block with it's name as a reference.
	 */
	private static HashMap blocks = new HashMap();
	
	public static void addUtility(String name, Class tile, UtilityBlock block){
		utilityNames.add(name);
		tiles.put(name, tile);
		reverseTile.put(tile, name);
		blocks.put(name, block);
	}
	
	public static List<String> getAllNames(){
		return utilityNames;
	}
	
	public static Class getTile(String name){
		return (Class) tiles.get(name);
	}
	
	public static UtilityBlock getBlock(String name){
		return (UtilityBlock) blocks.get(name);
	}
	
	public static String getNameFromTile(Class tile){
		return (String) reverseTile.get(tile);
	}

}
