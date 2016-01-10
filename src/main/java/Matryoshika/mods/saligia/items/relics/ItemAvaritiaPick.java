package Matryoshika.mods.saligia.items.relics;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.rendering.GUIHandler.MSGuiHandler;
import Matryoshika.mods.saligia.utils.math;

public class ItemAvaritiaPick extends ItemPickaxe {
	
	public int RANGE = saligia.pickRange;
	
	public ItemAvaritiaPick(ToolMaterial sin) {
		super(sin);
		
		this.setUnlocalizedName("ItemSinpick");
		this.setTextureName(saligia.MODID+":avaritiaPick");
	}
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	    
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
		if(!stack.isItemEnchanted()){
			stack.addEnchantment(Enchantment.fortune, 5);
		}
		
		EntityPlayer player = (EntityPlayer) entity;
		if(player.isSneaking())
		vacuumItems(entity);
		
		Block torch = Blocks.torch;
		if(player.inventory.hasItem(stack.getItem().getItemFromBlock(torch)) || player.capabilities.isCreativeMode){
			//System.out.println(world.getLightBrightness((int)player.posX, (int)player.posY, (int)player.posZ));
			if(world.getLightBrightness((int)player.posX, (int)player.posY, (int)player.posZ) <= 0.2){
				if(world.getBlock((int)player.posX, (int)player.posY, (int)player.posZ) == Blocks.air && world.getBlock((int)player.posX, (int)player.posY-1, (int)player.posZ) != Blocks.air && !world.isRemote){
					player.inventory.consumeInventoryItem(stack.getItem().getItemFromBlock(torch));
					world.setBlock((int)player.posX, (int)player.posY, (int)player.posZ, torch);
				}
				
			}
		}
	}
	
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity){
        
		if ((double)block.getBlockHardness(world, x, y, z) != 0.0D){
			
			MovingObjectPosition mop =  this.getMovingObjectPositionFromPlayer(world, (EntityPlayer) entity, true);
			
			if(mop != null) {
			breakMoreBlocks((EntityPlayer) entity, world, block, stack, x, y, z, mop.sideHit);
			}
        }
        return true;
    }
	
	public void breakMoreBlocks(EntityPlayer player, World world, Block block, ItemStack stack, int x, int y, int z, int side){

		if(side == 0 || side == 1){	
			for(int otherX = x-2; otherX < x+3; otherX++){
        		for(int otherZ = z-2; otherZ < z+3; otherZ++){
					if(world.getBlock(otherX, y, otherZ) != Blocks.air && world.getBlock(otherX, y, otherZ) != Blocks.bedrock && world.getTileEntity(otherX, y, otherZ) == null){
						if(!world.isRemote){
							block = world.getBlock(otherX, y, otherZ);
							Random rnd = new Random();
							
							if(block.getUnlocalizedName().contains("ore")){
								block.quantityDropped(block.getDamageValue(world, otherX, y, otherZ), 5, rnd);
								block.dropBlockAsItem(world, otherX, y, otherZ, 0, 7);
							}
							else{
								block.dropBlockAsItem(world, otherX, y, otherZ, 0, 0);	
							}
							world.setBlockToAir(otherX, y, otherZ);
	        				world.markBlockForUpdate(otherX, y, otherZ);
						}
					}
            	}
        	}				
		}
		if(side == 2 || side == 3){
			for(int otherX = x-2; otherX < x+3; otherX++){
        		for(int otherY = y-1; otherY < y+4; otherY++){
					if(world.getBlock(otherX, otherY, z) != Blocks.air && world.getBlock(otherX, otherY, z) != Blocks.bedrock && world.getTileEntity(otherX, otherY, z) == null){
						if(!world.isRemote){
							block = world.getBlock(otherX, otherY, z);
							Random rnd = new Random();
							
							if(block.getUnlocalizedName().contains("ore")){
								block.quantityDropped(block.getDamageValue(world, otherX, otherY, z), 5, rnd);
								block.dropBlockAsItem(world, otherX, otherY, z, 0, 7);
							}
							else{
								block.dropBlockAsItem(world, otherX, otherY, z, 0, 0);	
							}
							world.setBlockToAir(otherX, otherY, z);
	        				world.markBlockForUpdate(otherX, otherY, z);
						}
					}
            	}
        	}
		}
		if(side == 4 || side == 5){
			for(int otherZ = z-2; otherZ < z+3; otherZ++){
        		for(int otherY = y-1; otherY < y+4; otherY++){
					if(world.getBlock(x, otherY, otherZ) != Blocks.air && world.getBlock(x, otherY, otherZ) != Blocks.bedrock && world.getTileEntity(x, otherY, otherZ) == null){
						if(!world.isRemote){
							block = world.getBlock(x, otherY, otherZ);
							Random rnd = new Random();
							
							if(block.getUnlocalizedName().contains("ore")){
								block.quantityDropped(block.getDamageValue(world, x, otherY, otherZ), 5, rnd);
								block.dropBlockAsItem(world, x, otherY, otherZ, 0, 7);
							}
							else{
								block.dropBlockAsItem(world, x, otherY, otherZ, 0, 0);	
							}	
	        				world.setBlockToAir(x, otherY, otherZ);
	        				world.markBlockForUpdate(x, otherY, otherZ);
						}
					}
            	}
        	}
		}
		return;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void vacuumItems(Entity EntityPlayer){
		AxisAlignedBB box = EntityPlayer.boundingBox.expand(RANGE, RANGE, RANGE);
		Class<EntityItem> items = EntityItem.class;
		
		List<EntityItem> inbox = EntityPlayer.worldObj.getEntitiesWithinAABB(items, box);
		
			for (EntityItem entityItem : inbox) {
				double dx = (EntityPlayer.posX- entityItem.posX);
				double dy = (EntityPlayer.posY - entityItem.posY);
				double dz = (EntityPlayer.posZ - entityItem.posZ);
				double ddt = math.py3d(dx, dy, dz);
				entityItem.motionX += (dx/ddt/ddt)*0.25;
				entityItem.motionY += (dy/ddt/ddt)*0.15;
				entityItem.motionZ += (dz/ddt/ddt)*0.25;
				
				if(!EntityPlayer.worldObj.isRemote){
					if (entityItem.posY > EntityPlayer.posY+4){
						entityItem.motionY = 0;
					}
					if (entityItem.posY < EntityPlayer.posY){
						entityItem.motionY = 0.9;
					}
				}
			}
	}
	
}