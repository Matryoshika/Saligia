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
import net.minecraft.tileentity.TileEntity;
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
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulObelisk;

public class ItemSoulCrucible extends Item{
	
	public static int capacity = Integer.decode("0x64");
	public int currentAmount = 0;
	
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	    itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setString("souls", "Amount of Soul Fragments: ");
	    itemStack.stackTagCompound.setInteger("amount", currentAmount);
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
		 if (stack.stackTagCompound != null && stack.stackTagCompound.getInteger("amount") >= Integer.decode("0x64")){
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
		
		if(bowl.stackTagCompound.getInteger("amount") >= Integer.decode("0x64") && world.isRemote == false){
			chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("This Crucible has been filled.").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_RED));
			player.addChatMessage(chatComponent);
			bowl.setItemDamage(bowl.getItemDamage()-1);
		}
		
		if(bowl.stackTagCompound.getInteger("amount") <= Integer.decode("0x64")){
			if(player.inventory.hasItem(saligia_Items.VillagerSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.VillagerSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+Integer.decode("0x6"));
				return bowl;
			}
			if(player.inventory.hasItem(saligia_Items.AnimalSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.AnimalSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+Integer.decode("0x2"));
				return bowl;
			}
			if(player.inventory.hasItem(saligia_Items.BuffMobSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.BuffMobSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+Integer.decode("0x4"));
				return bowl;
			}
			if(player.inventory.hasItem(saligia_Items.ZombieSoul)){
				player.inventory.consumeInventoryItem(saligia_Items.ZombieSoul);
				int amount = bowl.stackTagCompound.getInteger("amount");
				bowl.stackTagCompound.setInteger("amount", amount+Integer.decode("0x1"));
				return bowl;
				
			}
		}
		
		
			if(world.isRemote != false){
					chatComponent = (ChatComponentTranslation) new ChatComponentTranslation("Currently holds: " + bowl.stackTagCompound.getInteger("amount") + " Soul-fragments").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_RED));
					player.addChatComponentMessage(chatComponent);
				}

		return bowl;
		
		
	}
	@Override
	public boolean hasEffect(ItemStack stack){
		if(stack.hasTagCompound() == true && stack.stackTagCompound.getInteger("amount") >= Integer.decode("0x64")){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float xFloat, float yFloat, float zFloat){
		
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			}
		if (!stack.getTagCompound().hasKey("amount")) {
			stack.getTagCompound().setInteger("amount", 0);
			}
		
		Block target = world.getBlock(x, y, z);
		if(target instanceof Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulBrazier || target instanceof Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulObelisk || target instanceof Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulNexus){
			TileEntity tile = world.getTileEntity(x, y, z);
			if(!(tile instanceof TileSoulBrazier) && !(tile instanceof TileSoulObelisk)  && !(tile instanceof TileSoulNexus)) return false;
			if(tile instanceof TileSoulBrazier){
				TileSoulBrazier tb = (TileSoulBrazier) tile;
				//Adding energy to brazier from crucible
				if(player.isSneaking() && stack.stackTagCompound.getInteger("amount") > 0 && tb.amount < Integer.decode("0x29A")){
					for(int transferAmount = 0; transferAmount <= 1 && currentAmount < 100 ; transferAmount++){
						stack.stackTagCompound.setInteger("amount", stack.stackTagCompound.getInteger("amount") - 1);
						tb.amount +=1;
					}
				}
				//Adding energy to crucible from brazier
				if(!player.isSneaking() && stack.stackTagCompound.getInteger("amount") < Integer.decode("0x64") && tb.amount > 0){
					for(int transferAmount = 0; transferAmount <= 1 && tb.amount > 0; transferAmount++){
						tb.amount -=1;
						stack.stackTagCompound.setInteger("amount", stack.stackTagCompound.getInteger("amount") + 1);
					}
				}
			}
			if(tile instanceof TileSoulObelisk){
				TileSoulObelisk to = (TileSoulObelisk) tile;
				//Adding energy to obelisk from crucible
				if(player.isSneaking() && stack.stackTagCompound.getInteger("amount") > 0 && to.amount < Integer.decode("0x6C4A4")){
					for(int transferAmount = 0; transferAmount <= 1 && currentAmount < 100 ; transferAmount++){
						stack.stackTagCompound.setInteger("amount", stack.stackTagCompound.getInteger("amount") - 1);
						to.amount +=1;
					}
				}
				//Adding energy to crucible from obelisk
				if(!player.isSneaking() && stack.stackTagCompound.getInteger("amount") < Integer.decode("0x64") && to.amount > 0){
					for(int transferAmount = 0; transferAmount == 1 && to.amount > 0; transferAmount++){
						to.amount -=1;
						stack.stackTagCompound.setInteger("amount", stack.stackTagCompound.getInteger("amount") + 1);
					}
				}
			}
			if(tile instanceof TileSoulNexus){
				TileSoulNexus tn = (TileSoulNexus) tile;
				//Adding energy to nexus from crucible
				if(player.isSneaking() && stack.stackTagCompound.getInteger("amount") > 0 && tn.amount < Integer.decode("0x6C4A4")){
					for(int transferAmount = 0; transferAmount <= 1 && currentAmount < 100 ; transferAmount++){
						stack.stackTagCompound.setInteger("amount", stack.stackTagCompound.getInteger("amount") - 1);
						tn.amount +=1;
					}
				}
				//Adding energy to crucible from nexus
				if(!player.isSneaking() && stack.stackTagCompound.getInteger("amount") < Integer.decode("0x64") && tn.amount > 0){
					for(int transferAmount = 0; transferAmount == 1 && tn.amount > 0; transferAmount++){
						tn.amount -=1;
						stack.stackTagCompound.setInteger("amount", stack.stackTagCompound.getInteger("amount") + 1);
					}
				}
			}	
		}	
		return true;
	}
}

