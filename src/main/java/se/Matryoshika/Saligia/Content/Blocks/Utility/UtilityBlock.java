/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks.Utility;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.UtilityTiles.UtilityTileRegistry;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;
import se.Matryoshika.Saligia.Content.Tiles.Utility.TileUtilityBase;
import se.Matryoshika.Saligia.Events.ClientTicks;

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
		this.needsRandomTick = true;
    }
	
	@Override
    public TileEntity createTileEntity(World world, IBlockState state){
        CustomTileClass tile = null;
        
        tile = (CustomTileClass) UtilityTileRegistry.instantiateUtil(UtilityTileRegistry.getTile(getRegistryName().toString()));
        
        return tile;
    }
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, 
    		EnumFacing side, float hitX, float hitY, float hitZ){
        
		TileUtilityBase tile = (TileUtilityBase) world.getTileEntity(pos);
		if(heldItem != null && !world.isRemote)
			tile.editParticleList(heldItem, player);
		
		return true;
    }
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		String name = getRegistryName().toString();
		if(GuiScreen.isShiftKeyDown())
			tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format(Saligia.MODID+".lore.advanced."+name.replace(Saligia.MODID+":", "")).toString());
		if(!GuiScreen.isShiftKeyDown()){
			tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format(Saligia.MODID+".lore.references."+name.replace(Saligia.MODID+":", "")).toString());
		}
			
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand){

		
		
		
		//System.out.println(ClientTicks.getTicks());
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
	
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

}
