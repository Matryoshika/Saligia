package Matryoshika.mods.saligia.items;

import java.util.List;

import javax.swing.Icon;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.altars.PaganAltar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Matryoshika.mods.saligia.items.ItemDeathHand;

public class ItemSoulCrucible extends Item{
	
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	    itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setString("souls", "Amount of Soul Fragments: ");
	    itemStack.stackTagCompound.setInteger("amount", 0);
	}
	
	public ItemSoulCrucible(ToolMaterial bowl) {
		super();
		this.maxStackSize = 1;
		setHasSubtypes(true);
		setMaxDamage(0);
		
		this.setUnlocalizedName("ItemSoulCrucible");
		ItemStack stack = new ItemStack (this);
		
		this.setTextureName(saligia.MODID+":soulCrucible");
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par){ 
		 if (stack.stackTagCompound != null && stack.stackTagCompound.getInteger("amount") <= 100){
			 String souls = stack.stackTagCompound.getString("souls");
			 int amount = stack.stackTagCompound.getInteger("amount");
			 list.add(EnumChatFormatting.DARK_RED + "Amount of Soul Fragments: " + amount);
			 
		 }
		 if (stack.stackTagCompound != null && stack.stackTagCompound.getInteger("amount") >= 100){
			 list.add(EnumChatFormatting.DARK_RED + "The Soul Crucible has been filled...");
			 this.setTextureName(saligia.MODID+":soulCrucibleFull");
		 }
		
	}
	public ItemStack onItemRightClick(ItemStack bowl, World world, EntityPlayer player){
		
		ChatComponentTranslation chatComponent;
		
		if (!bowl.hasTagCompound()) {
			bowl.setTagCompound(new NBTTagCompound());
			}
		if (!bowl.getTagCompound().hasKey("amount")) {
			bowl.getTagCompound().setInteger("amount", 0);
			}
		
		if(bowl.stackTagCompound.getInteger("amount") >= 100 && world.isRemote == false){
			chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("This Crucible has been filled.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_RED));
			player.addChatMessage(chatComponent);
			bowl.setItemDamage(bowl.getItemDamage()-1);
		}
		
		if(bowl.stackTagCompound.getInteger("amount") <= 100){
			if(player.inventory.hasItem(saligia_Items.VillagerSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.VillagerSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+6);
				return bowl;
			}
			if(player.inventory.hasItem(saligia_Items.AnimalSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.AnimalSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+2);
				return bowl;
			}
			if(player.inventory.hasItem(saligia_Items.BuffMobSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.BuffMobSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+4);
				return bowl;
			}
			if(player.inventory.hasItem(saligia_Items.ZombieSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.ZombieSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+1);
				return bowl;
				
			}
			else{
				if(world.isRemote == false){
					chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Currently holds: " + bowl.stackTagCompound.getInteger("amount") + " Soul-fragments").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_RED));
					player.addChatMessage(chatComponent);
				}
			}
		}

		return bowl;
		
		
	}
	@Override
	public boolean hasEffect(ItemStack stack){
		if(stack.hasTagCompound() == true && stack.stackTagCompound.getInteger("amount") >= 100){
			return true;
		}
		else{
			return false;
		}
	}
}

