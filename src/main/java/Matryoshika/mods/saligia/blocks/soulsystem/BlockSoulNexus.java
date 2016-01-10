package Matryoshika.mods.saligia.blocks.soulsystem;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulObelisk;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockSoulNexus extends BlockSoulObelisk{
	
	public BlockSoulNexus (Block BlockSoulObelisk){
		super(BlockSoulObelisk);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockSoulNexus");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockSoulNexus");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSoulNexus();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float bx, float by, float bz) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(!(tile instanceof TileSoulNexus)) return false;
		TileSoulNexus te = (TileSoulNexus) tile;
		
		if(te.amount < Matryoshika.mods.saligia.utils.math.SoulNexusMax()){
			if(!player.isSneaking() && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof Matryoshika.mods.saligia.items.souls.Soul){
				Item soul = player.inventory.getCurrentItem().getItem();
				
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.AnimalSoul){te.amount += Integer.decode("0x2");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.VillagerSoul){te.amount += Integer.decode("0x66660");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.ZombieSoul){te.amount += Integer.decode("0x1");}
				if(player.inventory.getCurrentItem().getItem() == saligia_Items.BuffMobSoul){te.amount += Integer.decode("0x4");}
				player.inventory.consumeInventoryItem(soul);
				te.markDirty();
			}
		}
		
		if(te.amount >= Matryoshika.mods.saligia.utils.math.SoulNexusMax()){ 
			te.amount = Matryoshika.mods.saligia.utils.math.SoulNexusMax();
			}
		if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == saligia_Items.SoulCrucible){
			return false;
		}
		if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == saligia_Items.TapeMeasure){
			return false;
		}
		
		return true;
	}

}
