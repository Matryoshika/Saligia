package Matryoshika.mods.saligia.blocks;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class blockSinRose extends BlockFlower implements IGrowable{
	
    @SideOnly(Side.CLIENT)
    private IIcon roseTex;
    String Texture = ":rose_";
    
    int timer;

	protected blockSinRose(Block blockSinRose) {
		super(0);
		this.setBlockName("sinRose");
		setHardness(0F);
		setStepSound(soundTypeGrass);
		setBlockBounds(0.3F, 0.0F, 0.3F, 0.8F, 1, 0.8F);
		setTickRandomly(false);
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean dontKnowDontCareGetLost) {
		return world.isAirBlock(x, y + 1, z);
	}
	
	@Override
	public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
		return func_149851_a(world, x, y, z, false);
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        this.roseTex = iconRegister.registerIcon(saligia.MODID+Texture+"tex");
    }
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return roseTex;
    }
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list){
        
            list.add(new ItemStack(item, 1));
        
    }
}
