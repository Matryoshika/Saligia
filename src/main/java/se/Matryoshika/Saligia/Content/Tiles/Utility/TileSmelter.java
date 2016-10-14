/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 14, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileSmelter  extends CustomTileClass implements ITickable{

	public static int TIMER = 0;
	public static final int LIMIT = 2 * 20;
	private final int RADII = 3;

	@Override
	public double[] colourScheme() {
		return new double[]{200, 67, 0};
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
			if(worldObj.getBlockState(pos).getBlock() instanceof BlockOre)
				smeltOre(pos);
			
			if(worldObj.getBlockState(pos).getBlock() instanceof BlockFurnace)
				speedFurnace(pos);
		}
	}
	
	private void smeltOre(BlockPos pos){
		ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(worldObj.getBlockState(pos).getBlock()));
		if(itemstack != null && !(worldObj.isRemote)){
			
			worldObj.setBlockToAir(pos);
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, getPos().getX()+0.5, getPos().getY()+1.5, getPos().getZ()+0.5, itemstack.copy()));
			
		}
		
	}
	
	private void speedFurnace(BlockPos pos){
		
	}
	
}
