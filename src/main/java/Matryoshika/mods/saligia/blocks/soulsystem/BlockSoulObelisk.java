package Matryoshika.mods.saligia.blocks.soulsystem;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulBrazier;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulObelisk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockSoulObelisk extends BlockSoulBrazier {

	public BlockSoulObelisk (Block BlockSoulBrazier){
		super(BlockSoulBrazier);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockSoulObelisk");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockSoulObelisk");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSoulObelisk();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float bx, float by, float bz) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(!(tile instanceof TileSoulObelisk)) return false;
		TileSoulObelisk te = (TileSoulObelisk) tile;
		
		if(player.isSneaking() && player.inventory.getCurrentItem() == null){
			if(world.isRemote) player.addChatComponentMessage(new ChatComponentText("Holds: " + Integer.toString(te.amount)));
			if(world.isRemote) player.addChatComponentMessage(new ChatComponentText("Max  : " + Integer.toString(Matryoshika.mods.saligia.utils.math.SoulObeliskMax())));
			return true;	
		}
		if(te.amount < Matryoshika.mods.saligia.utils.math.SoulObeliskMax()){
			if(!player.isSneaking() && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof Matryoshika.mods.saligia.items.souls.Soul){
				Item soul = player.inventory.getCurrentItem().getItem();
				
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.AnimalSoul){te.amount += Integer.decode("0x2");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.VillagerSoul){te.amount += Integer.decode("0x6");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.ZombieSoul){te.amount += Integer.decode("0x1");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.BuffMobSoul){te.amount += Integer.decode("0x4");}
				player.inventory.consumeInventoryItem(soul);
				te.markDirty();
			}
		}
		
		if(te.amount >= Matryoshika.mods.saligia.utils.math.SoulObeliskMax()){ 
			te.amount = Matryoshika.mods.saligia.utils.math.SoulObeliskMax();
			}
		if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == saligia_Items.SoulCrucible){
			return false;
		}
		
		return true;
	}

}
