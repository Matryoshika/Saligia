package se.Matryoshika.Saligia.Utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class CreativeTabSaligia extends CreativeTabs{

	public CreativeTabSaligia(String label) {
		super(label);
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
