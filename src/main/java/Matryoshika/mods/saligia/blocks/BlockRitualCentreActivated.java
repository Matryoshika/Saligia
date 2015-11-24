package Matryoshika.mods.saligia.blocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRitualCentreActivated extends Block {
	
	public BlockRitualCentreActivated (Block BlockRitualCentreActivated){
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockRitualCentre");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockRitualCentreActivated");
	}

}
