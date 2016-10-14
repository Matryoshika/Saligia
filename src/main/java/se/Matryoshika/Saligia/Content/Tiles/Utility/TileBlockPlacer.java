/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;
import se.Matryoshika.Saligia.Utils.Print;

/**
 * This class was created by Matryoshika Oct 10, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileBlockPlacer extends CustomTileClass implements ITickable{
	
	public static int TIMER = 0;
	public static final int LIMIT = 1 * 20;
	private final int RADII = 5;
	private final int HEIGHT = 2;
	
	private HashMap<Item, Block> specials = new HashMap<Item, Block>();
	

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
	
	@Override
	public double[] colourScheme() {
		return new double[]{0, 80, 80};
	}
	
	@SuppressWarnings("deprecation")
	private void activate(){
		IBlockState beneath = worldObj.getBlockState(getPos().down());
		List<BlockPos> possible = new ArrayList<>();
		
		List<EntityItem> inbox = worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().add(-RADII/2, -HEIGHT/2, -RADII/2), getPos().add((RADII+1)/2, (HEIGHT+1)/2, (RADII+1)/2)));
		
		for(EntityItem item : inbox){
			ItemStack stack = item.getEntityItem();
			Item stackItem = stack.getItem();
			
			if(stackItem instanceof ItemBlock || stackItem instanceof ItemRedstone || stackItem instanceof ItemBlockSpecial){
				for(BlockPos pos : BlockPos.getAllInBox(getPos().add(-RADII, -HEIGHT, -RADII), getPos().add(RADII, HEIGHT, RADII))){
					BlockPos above = pos.up();
					IBlockState aboveState = worldObj.getBlockState(above);
					Block aboveBlock = aboveState.getBlock();
					if(beneath == worldObj.getBlockState(pos) && (aboveBlock.isAir(aboveState, worldObj, above) || aboveBlock.isReplaceable(worldObj, above))) 
						possible.add(above);
				}
				
				if(!possible.isEmpty()){
					BlockPos pos = possible.get(worldObj.rand.nextInt(possible.size()));
					IBlockState place = null;
					if(stackItem instanceof ItemBlock)
						place = ((ItemBlock) stackItem).block.getStateFromMeta(stackItem.getMetadata(stack.getItemDamage()));
					if(stackItem instanceof ItemRedstone)
						place = Blocks.REDSTONE_WIRE.getDefaultState();
					if(stackItem instanceof ItemBlockSpecial){
						ItemBlockSpecial special = (ItemBlockSpecial) stackItem;
						if(specials.get(special) == null){
							try {
								Block block = (Block) FieldUtils.readDeclaredField(special, "block", true);
								place = block.getDefaultState();
								specials.put(special, block);
							} catch (IllegalAccessException e) {
								System.out.println("Something went wrong with the Saligia BlockPlacer, trying to place the "+special.getRegistryName().toString() + " located at " + Print.printPos(getPos()));
							}
						}
						else
							place = specials.get(special).getDefaultState();
					}
					if(place != null){
						if(place.getBlock().canPlaceBlockAt(worldObj, pos)){
							worldObj.setBlockState(pos, place, 1|2);
							worldObj.playEvent(2001, pos, Block.getStateId(place));
							possible.remove(pos);
							
							stack.stackSize--;
							if(stack.stackSize <= 0)
								item.setDead();
							
							return;
						}
					}
						
				}
			}
		}
		
	}

}
