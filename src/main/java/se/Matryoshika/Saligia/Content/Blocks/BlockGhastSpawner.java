/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.UtilityTiles.UtilityTileRegistry;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;
import se.Matryoshika.Saligia.Content.Tiles.TileGhastSpawner;

/**
 * This class was created by Matryoshika Oct 23, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockGhastSpawner extends Block{

	/**
	 * @param materialIn
	 */
	public BlockGhastSpawner() {
		super(Material.ROCK);
		this.setRegistryName(Saligia.MODID, "ghastspawner");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state){
        return true;
    }
	
	@Override
    public TileEntity createTileEntity(World world, IBlockState state){
        
		return new TileGhastSpawner();
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
