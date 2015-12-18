package Matryoshika.mods.saligia.blocks;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockVoidLight extends Block {

	
	public BlockVoidLight (Block BlockVoidLight){
		super(Material.air);
		setBlockTextureName(saligia.MODID+":emptyVoid");
		this.setBlockName("emtpyVoid");
	}
	
	public boolean isOpaqueCube(){
	    return false;
    }
	
	 public void onBlockAdded(World world, int x, int y, int z){
		 BlockVoidLight block = (BlockVoidLight) world.getBlock(x, y, z);
		 block.setLightLevel(15);
	 }
	
	
}
