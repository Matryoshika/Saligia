package Matryoshika.mods.saligia.blocks.altars;

import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class PaganAltar extends BlockFalling {
	
	int decreasedPerStack;
	int left = 0;
	boolean mats1 = false;
	private static final double RANGE = 2F;
	int splitInEight;
	int leftOver;
	boolean clearCrafting = false;
	
	
	
	public PaganAltar (Block PaganAltar){
		super();
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":PaganAltar");
		setHardness(-1);
		this.setResistance(18000000);
		this.setBlockName("PaganAltar");
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float sideX, float sideY, float sideZ){
		if(player.inventory.getCurrentItem() == null){
			
			Class<EntityItem> items1 = EntityItem.class;
			Class<EntityItem> items2 = EntityItem.class;
			List<EntityItem> inbox = world.getEntitiesWithinAABB(items1, AxisAlignedBB.getBoundingBox(x - RANGE, y - RANGE, z - RANGE, x + RANGE, y + RANGE, z + RANGE));
			if(world.isAirBlock(x, y+1, z) == true){
				for(EntityItem item : inbox){
					if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() == Item.getItemFromBlock(Blocks.netherrack) && !world.isRemote) {
						EntityItem entityitem = new EntityItem(world, (double)x+0.5, (double)y+1.2, (double)z+0.75, new ItemStack(saligia.GhastlyBlock, (decreasedPerStack / 8)));
							world.spawnEntityInWorld(entityitem);
							entityitem.motionX =0; entityitem.motionY =0; entityitem.motionZ =0;
						
							
						mats1 = false;
					}
				}
				for(EntityItem item : inbox){
					if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() != Item.getItemFromBlock(saligia.GhastlyBlock) && !world.isRemote) {
						world.addWeatherEffect(new customLightningBolt(world, x, y+1, z));
						item.setDead();	
						clearCrafting = false;
					}
				}
			}
			
			
			
			return false;
		}
		if(player.inventory.getCurrentItem().getItem() == Items.ghast_tear && mats1 == false){

			splitInEight = player.inventory.getCurrentItem().stackSize % 8;
			System.out.print(splitInEight);

			if(player.inventory.getCurrentItem().stackSize >= 8){
				decreasedPerStack = player.inventory.getCurrentItem().stackSize - splitInEight;
				System.out.print(decreasedPerStack);
				player.inventory.decrStackSize(player.inventory.currentItem, decreasedPerStack);
				EntityItem entity1 = new EntityItem(world, (double)x+0.5, (double)y+1.2, (double)z+0.25, new ItemStack(Items.ghast_tear, 1, 2)); 
				entity1.delayBeforeCanPickup = Integer.MAX_VALUE;
				if(!world.isRemote){
					world.spawnEntityInWorld(entity1); 
					entity1.motionX =0; entity1.motionY =0; entity1.motionZ =0; 
					mats1 = true;
				}
				
			}
			return true;	
		}
		
		if(player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(Blocks.netherrack) && mats1 == true && player.inventory.getCurrentItem().stackSize >= 8){
			player.inventory.decrStackSize(player.inventory.currentItem, (decreasedPerStack/8));
			EntityItem entityitem = new EntityItem(world, (double)x+0.5, (double)y+1.2, (double)z+0.75, new ItemStack(Blocks.netherrack, 1, 2));

			if(!world.isRemote){
				world.spawnEntityInWorld(entityitem);
				entityitem.delayBeforeCanPickup = Integer.MAX_VALUE;
				entityitem.motionX =0; entityitem.motionY =0; entityitem.motionZ =0;
				
			}
			return true;
		}
		
		else{
			return true;
		}   
    }
	
}
