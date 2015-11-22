package Matryoshika.mods.saligia.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentPurify extends Enchantment {
	
	public EnchantmentPurify(int id, int rarity){
		super(id, rarity, EnumEnchantmentType.weapon);
		this.setName("purify");
		
	}
	
	public int getMinEnchantability(int par1){
		return 31 + (par1 - 1) * 10;
	}
	
	public int getMaxEnchantability(int par1){
		return this.getMinEnchantability(par1) + 1;
	}
	
	public int getMaxLevel(){
		return 1;
	}

}
