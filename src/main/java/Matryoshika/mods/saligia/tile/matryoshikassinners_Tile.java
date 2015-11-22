package Matryoshika.mods.saligia.tile;

import java.util.ArrayList;
import java.util.List;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class matryoshikassinners_Tile {

	public static TileEntity DynamicGenerator;
	
	public static List<TileEntity>TileList=new ArrayList<TileEntity>();
	
	public static void registerTiles() {
		
		//TileList.add(DynamicGenerator = new TileGenerator());
		
		for(TileEntity Tile:TileList){
			
		}
		
	}

}
