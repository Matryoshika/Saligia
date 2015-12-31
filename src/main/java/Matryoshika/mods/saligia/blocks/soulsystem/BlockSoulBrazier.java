package Matryoshika.mods.saligia.blocks.soulsystem;

import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockSoulBrazier extends BlockContainer implements ITileEntityProvider {
	
	public BlockSoulBrazier (Block BlockSoulBrazier){
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockSoulBrazier");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockSoulBrazier");
	}
	
	@Override
    public boolean hasTileEntity(){
        return true;
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z){
        world.setTileEntity(x, y, z, this.createTileEntity(world, world.getBlockMetadata(x, y, z)));
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
	        super.breakBlock(world, x, y, z, block, meta);
	        world.removeTileEntity(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSoulBrazier();
	}
	
	public Item getItemDropped(int par1, Random rand, int par2){
        return Item.getItemFromBlock(this);
    }
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float bx, float by, float bz) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(!(tile instanceof TileSoulBrazier)) return false;
		TileSoulBrazier te = (TileSoulBrazier) tile;
		
		if(player.isSneaking() && player.inventory.getCurrentItem() == null){
			if(world.isRemote) player.addChatComponentMessage(new ChatComponentText("Holds: " + Integer.toString(te.amount)));
			return true;	
		}
		if(te.amount < Matryoshika.mods.saligia.utils.math.SoulBrazierMax()){
			if(!player.isSneaking() && player.inventory.getCurrentItem() != null){
				Item soul = player.inventory.getCurrentItem().getItem();
				
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.AnimalSoul){te.amount += Integer.decode("0x2");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.VillagerSoul){te.amount += Integer.decode("0x6");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.ZombieSoul){te.amount += Integer.decode("0x1");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.BuffMobSoul){te.amount += Integer.decode("0x4");}
				player.inventory.consumeInventoryItem(soul);
				te.markDirty();
			}
		}
		if(te.amount >= Matryoshika.mods.saligia.utils.math.SoulBrazierMax()){ 
			te.amount = Matryoshika.mods.saligia.utils.math.SoulBrazierMax();
			}
		
		return true;
	}

}

