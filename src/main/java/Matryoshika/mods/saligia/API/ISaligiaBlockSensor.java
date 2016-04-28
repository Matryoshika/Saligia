package Matryoshika.mods.saligia.API;

import net.minecraft.block.Block;

public interface ISaligiaBlockSensor {
	//Used by all rituals. Allows for easier checking

	public boolean isRitualMaster(Block block);
}
