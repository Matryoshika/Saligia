package se.Matryoshika.Saligia.Content.Blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Aug 16, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockSinFlower extends BlockBush{
	
	protected static final AxisAlignedBB BOX = new AxisAlignedBB(0.3F, 0.0F, 0.3F, 0.8F, 1, 0.8F);
	
	public BlockSinFlower(){
		this.setCreativeTab(Saligia.saligiaTab);
		this.setRegistryName("BlockSinRose");
		this.setUnlocalizedName(getRegistryName().toString());
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return BOX;
	}
	
	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
		return NULL_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}

}
