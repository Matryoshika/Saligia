package Matryoshika.mods.saligia.items.relics;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import Matryoshika.mods.saligia.saligia;

public class ItemWitheringShield extends Item {
	
	public ItemWitheringShield(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemWitheringShield");
		this.setTextureName(saligia.MODID+":antiWither");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
		
		EntityPlayer player = (EntityPlayer) entity;
		if(player.isPotionActive(Potion.wither) == true){
			player.removePotionEffect(Potion.wither.id);
		}

		
		
		
	}

	
}