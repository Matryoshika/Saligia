package Matryoshika.mods.saligia.blocks.altars;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DemonicAltar extends Block {
	
	public DemonicAltar (Block DemonicAltar){
		super(Material.iron);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":DemonicAltar");
		setHardness(10);
		this.setResistance(18000000);
		this.setBlockName("DemonicAltar");
	}

}
