/**
 * 
 */
package se.Matryoshika.Saligia.API.Content;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Utils.ConfigHandler;

/**
 * This class was created by Matryoshika Aug 11, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemRegistryInjector {
	
	public static List<Item> itemList = ContentRegistry.itemList;
	
	public void addToList(Item item){
		itemList.add(item);
	}
	
	public static void registerSaligiaItem(){
		for(Item item : itemList){
			if(!((Boolean) ConfigHandler.isItemEnabledMap.get(item.getRegistryName().toString()))){
				continue;
			}
			GameRegistry.register(item);
			//System.out.println("Registered : " + item.getUnlocalizedName());
		}
	}
}
