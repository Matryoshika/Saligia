package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBloodStone extends Item {
	
	public ItemBloodStone(ToolMaterial soul){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("ItemBloodStone");
		this.setTextureName(saligia.MODID+":BloodStone");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
		//System.out.println("You need to heal, boss");
		
		EntityPlayer player = ((EntityPlayer)entity);
		
		if(player.getHealth() < player.getMaxHealth() && player.getHealth() > 0.01){
			
			//Keep it at max modifier=2.5. Its WAY to OP otherwise
			player.heal((float) 0.025);
		}
		
		if(player.isBurning()){
			if(player.isSneaking()){
				player.extinguish();
			}
		}
		
		/*
		for(double healthRegen = 0.025;healthRegen < 5; healthRegen += Math.sqrt(1+healthRegen)/5 ){
			System.out.println(healthRegen);
		}
		*/
		
	}
	
	
}