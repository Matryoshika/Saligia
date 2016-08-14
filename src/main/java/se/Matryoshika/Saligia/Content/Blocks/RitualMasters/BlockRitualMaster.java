/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks.RitualMasters;

import com.sun.istack.internal.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Rituals.IRitualBlock;
import se.Matryoshika.Saligia.API.Rituals.RitualRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Tiles.TileRitualCOTH;

/**
 * This class was created by Matryoshika Aug 10, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockRitualMaster extends Block implements IRitualBlock{
	
	public String name;
	
	
	protected static final AxisAlignedBB BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

	public BlockRitualMaster() {
		super(Material.ROCK);
		this.setHardness(1);
		this.setResistance(0);
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state){
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
		return RitualRegistry.getTile(getRitualName());
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return BOX;
	}
	
	@Nullable
	@Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return BOX;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}

	@Override
	public Block setRitualName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String getRitualName(){
		return this.name;
	}
	
	public String getUnlocName(){
		String unloc = name.replaceAll("\\s", "");
		return unloc;
	}
	

}
