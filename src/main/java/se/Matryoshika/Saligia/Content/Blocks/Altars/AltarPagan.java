package se.Matryoshika.Saligia.Content.Blocks.Altars;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.Tiles.Altars.TileAbstractAltar;

/**
 * This class was created by Matryoshika Aug 16, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class AltarPagan extends Block{

	public AltarPagan(){
		super(Material.ROCK);
		this.setCreativeTab(Saligia.saligiaTab);
		this.setRegistryName("BlockAltarPagan");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setHardness(-1);
		this.setResistance(Float.MAX_VALUE);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state){ return false;}
	
	@Override
	public boolean isFullCube(IBlockState state){return false;}
	
	@Override
	public boolean isVisuallyOpaque(){return false;}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		
		if(world.getTileEntity(pos) instanceof TileAbstractAltar){
			TileAbstractAltar tile = (TileAbstractAltar) world.getTileEntity(pos);
			
			if(player.inventory.getCurrentItem() != null && !player.isSneaking()){
				
				tile.addItem(player, heldItem);
			}
			
			
			else if(player.inventory.getCurrentItem() == null && player.isSneaking()){
				
				if(tile.returnTargetedUser() <= 0)
					for(int i = tile.getSlots() -1; i >= 0; i--){
						ItemStack stackAt = tile.getStackInSlot(i);
						if(stackAt != null){
							ItemStack copy = stackAt.copy();
							player.inventory.addItemStackToInventory(copy);
							if(!player.inventory.addItemStackToInventory(copy)){
								Entity entity = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, copy);
								player.worldObj.spawnEntityInWorld(entity);
							}
							tile.extractItem(i, stackAt.stackSize, false);
								
						}
					}
			}
		}
		
		
		
		
		
		return true;
	}

}
