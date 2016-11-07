/**
 * 
 */
package se.Matryoshika.Saligia.API.Content;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * This class was created by Matryoshika Oct 31, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class Materials {
	
	public static final ToolMaterial VILE = EnumHelper.addToolMaterial("VILE", 3, 1536, 10.0F, 8.0F, 0);
	public static final ArmorMaterial VILE_ARMOUR = 
			EnumHelper.addArmorMaterial("VILE", "vile", 16, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1);
	public static final ArmorMaterial CORRUPTED_ARMOUR = 
			EnumHelper.addArmorMaterial("CORRUPTED", "corrupted", 24, new int[]{3, 6, 7, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2);
	public static final ArmorMaterial INFERNAL_ARMOUR = 
			EnumHelper.addArmorMaterial("INFERNAL", "infernal", 32, new int[]{4, 7, 8, 4}, 13, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4);
	public static final ArmorMaterial SIN_ARMOUR = 
			EnumHelper.addArmorMaterial("SIN", "sin", -1, new int[]{5, 8, 9, 5}, 32, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 8);

}
