package Matryoshika.mods.saligia.blocks;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRitualCentre extends Block {
	
	@SideOnly(Side.CLIENT)
    private IIcon BlockRitualCentreTop;
    @SideOnly(Side.CLIENT)
    private IIcon BlockRitualCentreSides;
	
	public BlockRitualCentre (Block BlockRitualCentre){
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockRitualCentre");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockRitualCentre");
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return side == 1 ? this.BlockRitualCentreTop : (side == 0 ? this.blockIcon : (side != meta ? this.blockIcon : this.BlockRitualCentreTop));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        this.BlockRitualCentreTop = iconRegister.registerIcon(saligia.MODID+":BlockRitualCentreTop");
        this.blockIcon = iconRegister.registerIcon(saligia.MODID+":BlockRitualCentreSides");
    }
	public boolean isOpaqueCube(){
    	return false;
    }
}