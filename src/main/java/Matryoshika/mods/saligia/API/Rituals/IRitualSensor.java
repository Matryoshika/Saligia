package Matryoshika.mods.saligia.API.Rituals;

import net.minecraft.block.Block;

public interface IRitualSensor {
	//Used by all rituals. Allows for easier checking

	public boolean isRitualMaster(Block block);
}
