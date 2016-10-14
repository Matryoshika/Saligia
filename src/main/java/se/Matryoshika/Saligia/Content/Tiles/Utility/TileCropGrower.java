/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import org.apache.commons.lang3.reflect.MethodUtils;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;
import se.Matryoshika.Saligia.Utils.Print;

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
			if(worldObj.getBlockState(pos).getBlock() instanceof BlockCrops){
				IBlockState state = worldObj.getBlockState(pos);
				BlockCrops crops = (BlockCrops) state.getBlock();
				if(crops.canGrow(worldObj, pos, state, false) )
					crops.grow(worldObj, pos, worldObj.getBlockState(pos));
				else if(crops.isMaxAge(state)){
					List<ItemStack> drops = crops.getDrops(worldObj, pos, state, 0);
					for(ItemStack stack : drops){
						if(!(stack.getItem() instanceof ItemSeeds))
							worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, stack));
					}
					worldObj.setBlockState(pos, crops.withAge(1), 2);
				}
			}
		}
		
	}

}
