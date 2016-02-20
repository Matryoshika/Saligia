package Matryoshika.mods.saligia.blocks.altars;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.altars.TileCultistAltar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CultistAltar extends PaganAltar {
	
	public CultistAltar (Block CultistAltar){
		super(CultistAltar);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":CultistAltar");
		setHardness(10);
		this.setResistance(18000000);
		this.setBlockName("CultistAltar");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCultistAltar();
	}

}
