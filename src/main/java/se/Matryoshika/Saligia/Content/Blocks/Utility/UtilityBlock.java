/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks.Utility;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.UtilityTiles.UtilityTileRegistry;

/**
 * This class was created by Matryoshika Oct 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class UtilityBlock extends Block{
	
	private final AxisAlignedBB BOX = new AxisAlignedBB(0.33D, 0.20D, 0.33D, 0.67D, 0.80D, 0.67D);

	public UtilityBlock() {
		super(Material.DRAGON_EGG);
		this.setCreativeTab(Saligia.saligiaTab);
	}

	public TileEntity createTileEntity(World world, IBlockState state){
		Class clazz = UtilityTileRegistry.getTile(this.getRegistryName().toString());
		
		if(clazz == null)
			return null;
		
		try {
			return (TileEntity) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	
	@Override
	public boolean hasTileEntity(IBlockState state){
        return true;
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BOX;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.TRANSLUCENT;
    }

}
