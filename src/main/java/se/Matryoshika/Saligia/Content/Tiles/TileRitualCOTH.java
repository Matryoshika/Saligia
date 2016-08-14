package se.Matryoshika.Saligia.Content.Tiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Rituals.IMultiblockChecker;
import se.Matryoshika.Saligia.API.Rituals.IRitualBlock;
import se.Matryoshika.Saligia.API.Rituals.RitualRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;

/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileRitualCOTH extends TileEntity implements ITickable, IMultiblockChecker{
	
	private static final double RANGE = 2F;
	
	public TileRitualCOTH(){
		setName("TileRitualCOTH");
	}

	
	@Override
	public void update(){
		System.out.println("Updating");
		
		if(!worldObj.isRemote);{
			
			Class<EntityItem> items = EntityItem.class;
			List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, new AxisAlignedBB(new BlockPos(this.pos.getX()-RANGE, this.pos.getY()-RANGE, this.pos.getZ()-RANGE), new BlockPos(this.pos.getX()+RANGE, this.pos.getY()+RANGE, this.pos.getZ()+RANGE)));
			if(worldObj.isAirBlock(this.pos.up())){
				for(EntityItem item : inbox){
					if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() == Items.WRITABLE_BOOK){
						if(item.ticksExisted == 5){
							System.out.println("Found it!");
							
						}
						if(item.ticksExisted >= 320){
							System.out.println("Spawning new Entity");
							item.setDead();
							ItemStack stack = new ItemStack(ContentRegistry.dustIngotItem);
							Entity entity = new EntityItem(worldObj, this.pos.getX()+0.5, this.pos.getY()+1.2, this.pos.getZ()+0.5, stack);
							worldObj.spawnEntityInWorld(entity);
							entity.motionX = 0;
							entity.motionY = 0;
							entity.motionZ = 0;
						}
					}
				}
			}
		}
	}

	public static String name;
	
	public static String getName(){
		return Saligia.MODID+":"+name;
	}
	
	public String setName(String name){
		this.name = name;
		return name;
	}


	@Override
	public boolean isMultiblockIntact(World world, Object[][] multiblock, BlockPos pos) {
		for(Object[] object : multiblock){
			int x = pos.getX() + (Integer)object[0];
			int y = pos.getY() + (Integer)object[1];
			int z = pos.getZ() + (Integer)object[2];
			Block block =        (Block)  object[3];
			
			Block blockAtPos = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			if(blockAtPos != block){
				return false;
			}
		}
		return true;
	}
}
