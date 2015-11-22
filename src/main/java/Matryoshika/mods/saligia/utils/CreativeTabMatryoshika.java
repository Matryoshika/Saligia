package Matryoshika.mods.saligia.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMatryoshika extends CreativeTabs{

	public CreativeTabMatryoshika(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return null;
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return this.getTabLabel();
	}

}
