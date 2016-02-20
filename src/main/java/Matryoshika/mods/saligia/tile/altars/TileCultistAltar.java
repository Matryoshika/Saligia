package Matryoshika.mods.saligia.tile.altars;

import net.minecraft.util.StatCollector;

public class TileCultistAltar extends TilePaganAltar{
	

	
	@Override
	public String getInventoryName() {
		return new String(StatCollector.translateToLocal("saligia.tile.CultistAltar"));
	}
	
	@Override
	public int tier(){
		return 2;
	}
}
