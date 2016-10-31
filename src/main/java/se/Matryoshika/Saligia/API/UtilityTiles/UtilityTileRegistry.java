/**
 * 
 */
package se.Matryoshika.Saligia.API.UtilityTiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
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
	 * Stores each Entity.class with it's name as a reference.
	 */
	private static HashMap tiles = new HashMap();
	private static HashMap reverseTiles = new HashMap();
	
	/**
	 * Stores each Block with it's name as a reference.
	 */
	private static HashMap blocks = new HashMap();
	
	public static void addUtility(String name, Class tile, UtilityBlock block){
		utilityNames.add(name);
		tiles.put(name, tile);
		reverseTiles.put(tile, name);
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
		return (String) reverseTiles.get(tile);
	}
	
	public static TileEntity instantiateUtil(Class tile){
		TileEntity wanted = null;

		try {
			wanted = (TileEntity) tile.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return wanted;
	}

}
