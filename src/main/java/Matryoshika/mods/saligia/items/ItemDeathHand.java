package Matryoshika.mods.saligia.items;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.blocks.altars.PaganAltar;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemDeathHand extends ItemSword {
	
	int bloodAmount = 1;
	int soulFragment = 0;
	
	public ItemDeathHand(ToolMaterial sacrifice) {
		super(sacrifice);
		this.maxStackSize = 1;
		
		this.setUnlocalizedName("ItemDeathHand");
		this.setTextureName(saligia.MODID+":sickle");
	}
	
	@Override
	public void addInformation(ItemStack stack1, EntityPlayer player, List list, boolean par){
		list.add(StatCollector.translateToLocal("lore.deathhand.desc1"));
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase base1, EntityLivingBase base2){
		if (base2 == null || base1 == null || base2.worldObj.isRemote){
			return false;
		}
		if (base1 instanceof EntityPlayer || base1 instanceof IBossDisplayData){
            return false;
        }
		
		World world = base1.worldObj;
		if (base1.isDead || base1.getHealth() < 0.5f){
            return false;
        }
		
		if (base1 instanceof EntityVillager) soulFragment = 1;
		else if (base1 instanceof EntityMob && !(base1 instanceof EntityGhast || base1 instanceof EntityCreeper)) soulFragment = 2;
		else if (base1 instanceof EntityAnimal) soulFragment = 3;
		else if (base1 instanceof EntityGhast || base1 instanceof EntityCreeper) soulFragment = 4;
		
		if (findPaganAltar(base1.worldObj, base1, soulFragment)){
            double posX = base1.posX;
            double posY = base1.posY;
            double posZ = base1.posZ;
            float f = 1.0F;
            float f1 = (f * 0.7F) + 0.3F;
            float f2 = (f * (f * 0.6F)) - 0.6F;
            float f3 = (f * (f * 0.4F)) - 0.7F;
            Random rand = new Random();
            int randomNum = rand.nextInt(100) + 1;
            
            base1.setHealth(-1);
            if(randomNum <= 5){
            	base1.worldObj.addWeatherEffect(new EntityLightningBolt(base1.worldObj, posX, posY, posZ));
            }
            base1.worldObj.playSoundAtEntity(base1, "fireworks.largeBlast_far", 3F, 0.1F);
            base1.onDeath(DamageSource.generic);
            
            if(soulFragment == 1){
            	ItemStack soul = new ItemStack(saligia_Items.VillagerSoul,1,0);
            	Entity item = new EntityItem(base1.worldObj, posX, posY, posZ, soul);
            	base1.worldObj.spawnEntityInWorld(item);
            }
            if(soulFragment == 2){
            	ItemStack soul = new ItemStack(saligia_Items.ZombieSoul,1,0);
            	Entity item = new EntityItem(base1.worldObj, posX, posY, posZ, soul);
            	base1.worldObj.spawnEntityInWorld(item);
            }
            if(soulFragment == 3){
            	ItemStack soul = new ItemStack(saligia_Items.AnimalSoul,1,0);
            	Entity item = new EntityItem(base1.worldObj, posX, posY, posZ, soul);
            	base1.worldObj.spawnEntityInWorld(item);
            }
            if(soulFragment == 4){
            	ItemStack soul = new ItemStack(saligia_Items.BuffMobSoul,1,0);
            	Entity item = new EntityItem(base1.worldObj, posX, posY, posZ, soul);
            	base1.worldObj.spawnEntityInWorld(item);
            }
        }
		return false;
		
	}
	 public boolean findPaganAltar(World world, EntityLivingBase sacrifice, int amount)
	    {
	        int posX = (int) Math.round(sacrifice.posX - 0.5f);
	        int posY = (int) sacrifice.posY;
	        int posZ = (int) Math.round(sacrifice.posZ - 0.5f);
	        PaganAltar altarEntity = this.getAltar(world, posX, posY, posZ);

	        if (altarEntity == null)
	        {
	            return false;
	        }
	        return true;
	    }
	 public PaganAltar getAltar(World world, int x, int y, int z){
	        Block block;

	        for (int i = -2; i <= 2; i++){
	            for (int j = -2; j <= 2; j++){
	                for (int k = -2; k <= 1; k++){
	                    block = world.getBlock(i + x, k + y, j + z);

	                    if ((block instanceof PaganAltar)){
	                        return (PaganAltar) block;
	                    }
	                }
	            }
	        }

	        return null;
	 }
}
