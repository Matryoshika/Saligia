package Matryoshika.mods.saligia.utils;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.entities.EntitySaligiaBoss;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.entities.EntityGula;
import Matryoshika.mods.saligia.entities.EntityIra;
import Matryoshika.mods.saligia.entities.EntitySuperbia;
import Matryoshika.mods.saligia.entities.saligia_Entities;
import Matryoshika.mods.saligia.items.saligia_Items;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class matryoshikaEventHandler {
	
	ChatComponentTranslation chatComponent;
	
	
	@SubscribeEvent
	public void allowSinWeapon (AttackEntityEvent attacker){
		
		Entity target = attacker.target;

			if((target instanceof EntitySaligiaBoss)){
				if(attacker.entityPlayer.getCurrentEquippedItem() != null){
					
					ItemStack hand = attacker.entityPlayer.getCurrentEquippedItem();
					if (attacker.entityPlayer.getCurrentEquippedItem().getItem() == saligia_Items.SinDagger){
						attacker.setCanceled(false);
					}
					else{
						attacker.setCanceled(true);
					}
				}
				else{
					attacker.setCanceled(true);
				}
			}
			else
			{
				return;
			}
	}
	@SubscribeEvent
	public void  killProjectiles (LivingHurtEvent event){
		Entity victim = event.entityLiving;
		Random rand = new Random();
        int randomNum = rand.nextInt(5) + 1;
		if(victim instanceof EntitySuperbia){
			if(randomNum == 1){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("saligia.superbia.trash1").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 2){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("saligia.superbia.trash2").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 3){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("saligia.superbia.trash3").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 4){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("saligia.superbia.trash4").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 5){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("saligia.superbia.trash5").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			((EntityPlayer) event.source.getEntity()).addChatComponentMessage(chatComponent);
			if(event.source.isProjectile()){
				event.setCanceled(true);
			}
			float shouldHurt = event.ammount;
			event.ammount = shouldHurt * 0.2F;
		}
	}
	@SubscribeEvent
	public void regenOnHit (LivingAttackEvent event){
		if(event.source.getSourceOfDamage() instanceof EntityIra && event.entity instanceof EntityPlayer){
			EntityIra ira = (EntityIra) event.source.getSourceOfDamage();
			float currentHealth = ira.getHealth();
			float maxHealth = ira.getMaxHealth();
			if(currentHealth < maxHealth){
				ira.setHealth(currentHealth*1.1F);
			}
			else{
				return;
			}
		}
	}
	@SubscribeEvent
	public void armorBuff (LivingHurtEvent event){
		Entity victim = event.entityLiving;
		if(victim instanceof EntityGula){
			boolean invulnerable;
			if(((EntityLivingBase) victim).getHealth() <= (((EntityLivingBase) victim).getMaxHealth() * 0.2)){
				invulnerable = true;
			}
			if(((EntityLivingBase) victim).getHealth() >= (((EntityLivingBase) victim).getMaxHealth() * 0.99)){
				invulnerable = false;
			}
			else{
				invulnerable = false;
			}
			if(invulnerable == true){
				float shouldHurt = event.ammount;
				event.ammount = shouldHurt * 0.2F;
				
			}
		}
	}
	
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event){
	    if (event.itemStack.getItem() == Item.getItemFromBlock(saligia_Blocks.SinRose)) {
	    	String roseName = new String(StatCollector.translateToLocal("tile.sinRose.name"));
	        String rose1 = new String(StatCollector.translateToLocal("saligia.sinrose.tooltip1"));
	        String rose2 = new String(StatCollector.translateToLocal("saligia.sinrose.tooltip2"));

	        event.toolTip.clear();
	        event.toolTip.add(roseName);
	        event.toolTip.add(EnumChatFormatting.GRAY + rose1);
	        event.toolTip.add(EnumChatFormatting.GRAY + rose2);
	    }
	}
	@SubscribeEvent
	public void boneMealing(BonemealEvent event){
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;
		Block block = event.block;
		Random rand = new Random();
		int i = rand.nextInt(2);
		
		if (block == Blocks.grass && event.world.getBlockMetadata(x, y, z) == 0){
			int flowerHeight = y + 1;
		    for (int i2 = 0; i2 < 5; ++i2){
		    	x += event.world.rand.nextInt(3) - 1;
		    	flowerHeight += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
		    	z += event.world.rand.nextInt(3) - 1;
			    }
			    if (event.world.getBlock(x, flowerHeight, z).isAir(world, x, flowerHeight, z)){
			    	if (saligia_Blocks.SinRose.canReplace(world, x, flowerHeight, z, 0, new ItemStack(saligia_Blocks.SinRose, 1, i))){
			    		if (!event.world.isRemote){
			    			event.world.setBlock(x, flowerHeight, z, saligia_Blocks.SinRose, 0, 2);
			    			}
			    		}
			    	}
			    }
		}
		
}