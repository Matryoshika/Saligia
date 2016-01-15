package Matryoshika.mods.saligia.items.relics;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBloodStone extends Item {
	
	public boolean hasAcedia = false;
	public boolean hasAvaritia = false;
	public boolean hasGula = false;
	public boolean hasInvidia = false;
	public boolean hasIra = false;
	public boolean hasLuxuria = false;
	public boolean hasSuperbia = false;
	public boolean isActivated = true;
	public int amount = 8;
	
	public ItemBloodStone(ToolMaterial soul){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("ItemBloodStone");
		this.setTextureName(saligia.MODID+":BloodStone");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
		
		EntityPlayer player = ((EntityPlayer)entity);
		
		if(player.getHealth() < player.getMaxHealth() && player.getHealth() > 0.01){
			
			//Keep it at max modifier=2.5. Its WAY to OP otherwise
			if(amount == 1){
				player.heal((float) 0.025);
			}
			if(amount == 2){
				player.heal((float) 0.025*2);
			}
			if(amount == 3){
				player.heal((float) 0.025*3);
			}
			if(amount == 4){
				player.heal((float) 0.025*5);
			}
			if(amount == 5){
				player.heal((float) 0.025*8);
			}
			if(amount == 6){
				player.heal((float) 0.025*13);
			}
			if(amount == 7){
				player.heal((float) 0.025*21);
			}
			if(amount == 8){
				player.heal((float) 0.025*34);
			}
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
	
	public void onDrop (World world, EntityPlayer player, ItemStack stack, Minecraft mc){
		
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float xFloat, float yFloat, float zFloat){
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.AcediaBlock)) && hasAcedia == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.AcediaBlock));
			amount += 1;
			hasAcedia = true;
		}
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.AvaritiaBlock)) && hasAvaritia == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.AvaritiaBlock));
			amount += 1;
			hasAvaritia = true;
		}
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.GulaBlock)) && hasGula == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.GulaBlock));
			amount += 1;
			hasGula = true;
		}
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.InvidiaBlock)) && hasInvidia == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.InvidiaBlock));
			amount += 1;
			hasInvidia = true;
		}
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.IraBlock)) && hasIra == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.IraBlock));
			amount += 1;
			hasIra = true;
		}
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.LuxuriaBlock)) && hasLuxuria == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.LuxuriaBlock));
			amount += 1;
			hasLuxuria = true;
		}
		if(player.inventory.hasItem(Item.getItemFromBlock(saligia_Blocks.SuperbiaBlock)) && hasSuperbia == false){
			player.inventory.consumeInventoryItem(Item.getItemFromBlock(saligia_Blocks.SuperbiaBlock));
			amount += 1;
			hasSuperbia = true;
		}
		
		
		return true;
	}
	
	
}
