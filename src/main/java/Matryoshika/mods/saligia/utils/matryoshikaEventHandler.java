package Matryoshika.mods.saligia.utils;

import java.util.Random;

import Matryoshika.mods.saligia.entities.EntityAcedia;
import Matryoshika.mods.saligia.entities.EntityAvaritia;
import Matryoshika.mods.saligia.entities.EntityGula;
import Matryoshika.mods.saligia.entities.EntityInvidia;
import Matryoshika.mods.saligia.entities.EntityIra;
import Matryoshika.mods.saligia.entities.EntityLuxuria;
import Matryoshika.mods.saligia.entities.EntitySuperbia;
import Matryoshika.mods.saligia.entities.saligia_Entities;
import Matryoshika.mods.saligia.items.saligia_Items;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class matryoshikaEventHandler {
	
	ChatComponentTranslation chatComponent;
	
	
	@SubscribeEvent
	public void allowSinWeapon (AttackEntityEvent attacker){
		
		Entity target = attacker.target;

			if((target instanceof EntityAcedia || 
					target instanceof EntityAvaritia || 
					target instanceof EntityGula || 
					target instanceof EntityInvidia || 
					target instanceof EntityIra || 
					target instanceof EntityLuxuria || 
					target instanceof EntitySuperbia)){
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
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("You believe you can hurt Me?").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 2){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Die you fool!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 3){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("You are so disrespectful!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 4){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Kneel to me, peasant!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
			}
			if(randomNum == 5){
				chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("You are more idiotic than you look.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
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
}