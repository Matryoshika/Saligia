/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles;

import net.minecraft.tileentity.TileEntity;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class CustomTileClass extends TileEntity{
	
	public static String name;
	
	public static String getName(){
		return Saligia.MODID+":"+name;
	}
	
	public String setName(String name){
		this.name = name;
		return name;
	}
	
}
