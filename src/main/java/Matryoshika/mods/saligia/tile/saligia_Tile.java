package Matryoshika.mods.saligia.tile;

import java.util.ArrayList;
import java.util.List;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class saligia_Tile {

	public static TileEntity DynamicGenerator;
	public static TileEntity RitualCOTH;
	
	public static List<TileEntity>TileList=new ArrayList<TileEntity>();
	
	public static void registerTiles() {
		
		//TileList.add(DynamicGenerator = new TileGenerator());
		TileList.add(RitualCOTH = new TileRitualCOTH());
		
		for(TileEntity Tile:TileList){
			//GameRegistry.registerTileEntity(TileRitualCOTH.class, "tileRitualCOTH");
		}
		
		
	}
}
