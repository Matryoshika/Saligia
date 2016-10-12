package se.Matryoshika.Saligia.Content.Tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Rituals.IMultiblockChecker;
import se.Matryoshika.Saligia.API.Rituals.RitualRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileRitualCOTH extends TileEntity implements ITickable, IMultiblockChecker{
	
	private static final double RANGE = 2F;
	public int counter = 0;
	private boolean canWork = false;
	public static List<Object[]> GHASTS = new ArrayList<Object[]>();
	int soundPass = 5;
	int blockToChoose = 0;
	
	public TileRitualCOTH(){
		setName("TileRitualCOTH");
		for(int i = 16; i < 24; i++){
			Object[][] multiblock = RitualRegistry.getMultiblock(RitualRegistry.getNameFromTile(this.getClass()));
			GHASTS.add(multiblock[i]);
		}
	}

	
	@Override
	public void update(){
		
		counter++;
		if(counter >= 60){
			counter = 0;
			canWork = isMultiblockIntact(worldObj, RitualRegistry.getMultiblock(RitualRegistry.getNameFromTile(this.getClass())), this.pos);
		}
		
		if(!worldObj.isRemote && canWork){
			//System.out.println(soundPass);
			Class<EntityItem> items = EntityItem.class;
			List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(items, new AxisAlignedBB(new BlockPos(this.pos.getX()-RANGE, this.pos.getY()-RANGE, this.pos.getZ()-RANGE), new BlockPos(this.pos.getX()+RANGE, this.pos.getY()+RANGE, this.pos.getZ()+RANGE)));
			if(worldObj.isAirBlock(this.pos.up())){
				for(EntityItem item : inbox){
					if(!item.isDead && item.getEntityItem() != null && item.getEntityItem().getItem() == Items.WRITABLE_BOOK){
						if(item.ticksExisted == soundPass){
							Object[] coords = GHASTS.get(blockToChoose);
							int x = (int) coords[0];
							int y = (int) coords[1];
							int z = (int) coords[2];
							
							playSound(worldObj,this.pos, new BlockPos(x, y, z));
							soundPass += 20;
							blockToChoose += 1;
						}
						if(item.ticksExisted >= 155){
							soundPass = 5;
							blockToChoose = 0;
							worldObj.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.enderdragon.growl")), SoundCategory.HOSTILE, 0.5F, 1F);
							item.setDead();
							ItemStack stack = new ItemStack(ContentRegistry.DUST_INGOT);
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
	
	public static void playSound(World world, BlockPos origin, BlockPos pos){
		world.playSound(null, origin.getX() + 0.5 + pos.getX(), origin.getY() + 1.5 + pos.getY(), origin.getZ() + 0.5 + pos.getZ(), SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.ghast.warn")), SoundCategory.HOSTILE, 0.5F, 1F);
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
