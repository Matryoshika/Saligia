/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 20, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileUtilityBase extends CustomTileClass implements ITickable{
	
	protected int AGE = 0;
	
	protected boolean ShouldRenderParticles = true;
	List<PARTICLES> particlesToRender = new ArrayList<PARTICLES>();
	
	public static enum PARTICLES{WOOL, REDSTONE, 
		SNOWBALL, SNOWBLOCK, 
		ICEBLOCK, PACKEDICEBLOCK
	}
	
	public void editParticleList(ItemStack stack, EntityPlayer player){
		Item item = stack.getItem();
		Block block = Block.getBlockFromItem(item);
		if(block != null){
			PARTICLES part = Block.getBlockFromItem(stack.getItem()) == Blocks.WOOL ? PARTICLES.WOOL :
				Block.getBlockFromItem(stack.getItem()) == Blocks.SNOW ? PARTICLES.SNOWBLOCK :
				Block.getBlockFromItem(stack.getItem()) == Blocks.ICE  ? PARTICLES.ICEBLOCK  :
				Block.getBlockFromItem(stack.getItem()) == Blocks.PACKED_ICE ? PARTICLES.PACKEDICEBLOCK :
				null;
			if(part != null){
				if(particlesToRender.contains(part))
					return;
				particlesToRender.add(part);
				stack.stackSize--;
			}
		}

		else{
			PARTICLES part = stack.getItem() == Items.REDSTONE ? PARTICLES.REDSTONE :
				stack.getItem() == Items.SNOWBALL ? PARTICLES.SNOWBALL :
				null;
			if(part != null){
				if(particlesToRender.contains(part))
					return;
				particlesToRender.add(part);
				stack.stackSize--;
			}
		}
		if(stack.stackSize >= 0)
			stack = null;
		
	}
	
	@Override
	public void update() {
		if(!worldObj.isRemote)
			return;
		
		AGE++;
		if(AGE >= Integer.MAX_VALUE -99) //not very likely to happen, but always good to have a fail-safe, if anyone does something very stupid. 
			AGE = 0;
		

			if(getAge() % (360) == 1)
				Saligia.proxy.particle(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 1, 1, 1);
			
		
	}
	
	public int getAge(){
		return AGE;
	}
}
