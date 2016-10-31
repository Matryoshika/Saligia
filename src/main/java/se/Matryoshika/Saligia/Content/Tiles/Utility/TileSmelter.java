/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 14, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileSmelter extends TileUtilityBase{

	public static int TIMER = 0;
	public static final int LIMIT = 1 * 20;
	private final int RADII = 3;
	
	List<BlockPos> furnacePos = new ArrayList<BlockPos>();

	@Override
	public double[] colourScheme() {
		return new double[]{200, 67, 0};
	}
	
	@Override
	public void update() {
		super.update();
		if(worldObj.isRemote)
			return;
		
		
		TIMER++;
		
		if(TIMER >= LIMIT){
			TIMER = 0;
			activate();
		}
		
		if(!furnacePos.isEmpty())
			for(BlockPos pos : furnacePos)
				speedFurnace(pos);
	}
	
	private void activate(){
		
		furnacePos.clear();
		
		for(BlockPos pos : BlockPos.getAllInBox(getPos().add(-RADII, -RADII, -RADII), getPos().add(RADII, RADII, RADII))){
			Block block = worldObj.getBlockState(pos).getBlock();
			if(block instanceof BlockOre || block instanceof BlockRedstoneOre)
				smeltOre(pos);
			
			if(worldObj.getTileEntity(pos) instanceof TileEntityFurnace){		
				furnacePos.add(pos);
			}
		}
	}
	
	private void smeltOre(BlockPos pos){
		ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(worldObj.getBlockState(pos).getBlock()));
		if(itemstack != null && !(worldObj.isRemote)){
			
			worldObj.setBlockToAir(pos);
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX()+0.5, pos.getY()+1.5, pos.getZ()+0.5, itemstack.copy()));
			
		}
		
	}
	/*      TileEntityFurnace fields of interest
	 * 		case 0:
     *          return this.furnaceBurnTime;
     *      case 1:
     *          return this.currentItemBurnTime;
     *      case 2:
     *          return this.cookTime;
     *      case 3:
     *          return this.totalCookTime;
	 */
	
	private void speedFurnace(BlockPos pos){
		//System.out.println("Got a furnace");
		TileEntityFurnace furnace = (TileEntityFurnace) worldObj.getTileEntity(pos);
		if(furnace == null)
			return;
		
		boolean canSmelt = canFurnaceSmelt(furnace);
		
		if(canSmelt){
			if(worldObj.getBlockState(furnace.getPos()).getBlock() == Blocks.FURNACE)
				((BlockFurnace) worldObj.getBlockState(furnace.getPos()).getBlock()).setState(true, worldObj, furnace.getPos());
			furnace.setField(2, Math.min(199, furnace.getField(2) + 9));
			furnace.setField(0, 1600);
		}
		if(!canSmelt){
			furnace.setField(0, 0);
			if(worldObj.getBlockState(furnace.getPos()).getBlock() == Blocks.LIT_FURNACE)
				((BlockFurnace) worldObj.getBlockState(furnace.getPos()).getBlock()).setState(false, worldObj, furnace.getPos());
		}
	}
	
	private boolean canFurnaceSmelt(TileEntityFurnace furnace){
		if(furnace.getStackInSlot(0) == null)
			return false;
		else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(furnace.getStackInSlot(0));

			if(itemstack == null)
				return false;

			if(furnace.getStackInSlot(2) == null)
				return true;

			if(!furnace.getStackInSlot(2).isItemEqual(itemstack))
				return false;

			int result = furnace.getStackInSlot(2).stackSize + itemstack.stackSize;
			return result <= 64 && result <= itemstack.getMaxStackSize();
		}
	}
	
}
