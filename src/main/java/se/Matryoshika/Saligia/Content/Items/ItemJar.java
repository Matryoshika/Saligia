/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockNetherrack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Oct 27, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemJar extends Item{
	
	private List<BlockPos> blocks = new ArrayList<BlockPos>();
	
	public ItemJar(){
		this.setRegistryName(Saligia.MODID, "jar");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, 
			BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(world.isRemote)
        	return EnumActionResult.PASS;

        
		if(world.getBlockState(pos).getBlock() instanceof BlockNetherrack && !(blocks.contains(pos))){
			if(blocks.size() >= 100){
				blocks.remove(0);
			}
			blocks.add(pos);
			Random rand = new Random();
			
			if(rand.nextInt(256) == 1){
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}
		}
		return super.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
    }

}
