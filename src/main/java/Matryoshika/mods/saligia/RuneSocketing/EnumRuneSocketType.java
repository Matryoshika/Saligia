package Matryoshika.mods.saligia.RuneSocketing;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnumRuneSocketType{
    all,
    itemInst,
    armor,
    armor_feet,
    armor_legs,
    armor_torso,
    armor_head,
    weapon,
    digger,
    fishing_rod,
    breakable,
    bow,
    tool;

    private static final String __OBFID = "CL_00000106";

    /**
     * Return true if the item passed can carry a Rune-Effect of this type.
     */
    public boolean canSocketItem(Item item){
        if (this == all){
            return true;
        }
        else if(this == itemInst && !(item instanceof ItemTool) && !(item instanceof ItemArmor) && !(item instanceof ItemSword) && !(item instanceof ItemBow)){
        	return true;
        }
        else if(this == tool){
        	if(item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemBow){
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        else if (this == breakable && item.isDamageable()){
            return true;
        }
        else if (item instanceof ItemArmor){
            if (this == armor){
                return true;
            }
            else{
                ItemArmor itemarmor = (ItemArmor)item;
                return itemarmor.armorType == 0 ? this == armor_head : (itemarmor.armorType == 2 ? this == armor_legs : (itemarmor.armorType == 1 ? this == armor_torso : (itemarmor.armorType == 3 ? this == armor_feet : false)));
            }
        }
        else{
            return item instanceof ItemSword ? this == weapon : (item instanceof ItemTool ? this == digger : (item instanceof ItemBow ? this == bow : (item instanceof ItemFishingRod ? this == fishing_rod : false)));
        }
    }
}
