package Matryoshika.mods.saligia.blocks.altars;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SinfulAltar extends Block {
	
	public SinfulAltar (Block SinfulAltar){
		super(Material.iron);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":SinfulAltar");
		setHardness(10);
		this.setResistance(18000000);
		this.setBlockName("SinfulAltar");
	}

}
