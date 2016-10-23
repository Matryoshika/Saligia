/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileCropGrower extends CustomTileClass implements ITickable{

	public static int TIMER = 0;
	public static final int LIMIT = 3 * 20;
	private final int RADII = 3;

	@Override
	public double[] colourScheme() {
		return new double[]{0, 110, 20};
	}
	
	@Override
	public void update() {
		if(worldObj.isRemote)
			return;
		
		
		TIMER++;
		
		if(TIMER >= LIMIT){
			TIMER = 0;
			activate();
		}
	}
	
	private void activate(){
		
		for(BlockPos pos : BlockPos.getAllInBox(getPos().add(-RADII, -RADII, -RADII), getPos().add(RADII, RADII, RADII))){
			Block block = worldObj.getBlockState(pos).getBlock();
			if(block instanceof BlockCrops){
				blockCrops(pos);
			}
			if(block instanceof BlockReed || block instanceof BlockStem){
				blockStems(pos);
			}
		}
		
	}
	
	private void blockStems(BlockPos pos){
		IBlockState state = worldObj.getBlockState(pos);
		if(state.getBlock() instanceof BlockStem){
			BlockStem stem = (BlockStem) state.getBlock();
			if(stem.canGrow(worldObj, pos, state, false))
				stem.growStem(worldObj, pos, state);
			else if(!stem.canGrow(worldObj, pos, state, false)){
				stem.updateTick(worldObj, pos, state, worldObj.rand);
			}
			Block block = null;
			try {
				block = (Block) FieldUtils.readDeclaredField(stem, "crop", true);
			} catch (IllegalAccessException e) {
				//Remember to add a small warning here
			}
			if(block != null)
				for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL){
	                if (worldObj.getBlockState(pos.offset(enumfacing)).getBlock() == block){
	                    worldObj.setBlockToAir(pos.offset(enumfacing));
	                    spawnEntity(worldObj, pos, new ItemStack(block));
	                }
	            }
		}
		if(state.getBlock() instanceof BlockReed){
			BlockReed reed = (BlockReed) state.getBlock();
			if(!(worldObj.getBlockState(pos.up(2)).getBlock() instanceof BlockReed)){
				reed.updateTick(worldObj, pos, state, worldObj.rand);
			}
			else{
				worldObj.setBlockToAir(pos.up());
				spawnEntity(worldObj, pos, new ItemStack(reed.getItemDropped(state, worldObj.rand, 0)));
			}
		}
	}
	
	private void blockCrops(BlockPos pos){
		IBlockState state = worldObj.getBlockState(pos);
		BlockCrops crops = (BlockCrops) state.getBlock();
		if(crops.canGrow(worldObj, pos, state, false) )
			crops.grow(worldObj, pos, worldObj.getBlockState(pos));
		else if(crops.isMaxAge(state)){
			List<ItemStack> drops = crops.getDrops(worldObj, pos, state, 0);
			for(ItemStack stack : drops){
				if(!(stack.getItem() instanceof ItemSeeds))
					spawnEntity(worldObj, pos, stack);
			}
			worldObj.setBlockState(pos, crops.withAge(1), 2);
		}
	}
	
	public void spawnEntity(World world, BlockPos pos, ItemStack stack){
		EntityItem entity = new EntityItem(world, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, stack);
		entity.motionX = 0;
		entity.motionZ = 0;
		world.spawnEntityInWorld(entity);

	}

}
