package Matryoshika.mods.saligia.utils;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.items.saligia_Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class NormalRecipes {
	
	public static void registerNormalRecipes(){
		
		//Items--------------------------------------------------------------------------------------
		ItemStack SinDagger = new ItemStack(saligia_Items.SinDagger );
		SinDagger.addEnchantment(saligia.purify, 1);
		GameRegistry.addShapelessRecipe(SinDagger, new Object[]
				{
					Items.flint, 
					Items.stone_sword
				});
		

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(saligia_Items.RitualActivator, 1, 0), 
				" S ", 
				"SxS", 
				" S ", 
				'S', "materialSaligiaSoul", 'x', Items.emerald));
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.DustCompound), new Object[]{
				"c",
				'c', new ItemStack(saligia_Blocks.SinRose)
		});
		GameRegistry.addRecipe(new ItemStack(saligia_Items.DustIngot, 1, 0), 
				"DDD", 
				"DDD", 
				'D', saligia_Items.DustCompound);
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.SickleBase, 1, 0), 
				"  I", 
				" I ",
				"I  ",
				'I', saligia_Items.SinIngot);
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.SickleBlade, 1, 0), 
				" II", 
				"I I",
				'I', saligia_Items.SinIngot);
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.Deathhand, 1, 0), 
				"l", 
				"a",
				'a', saligia_Items.SickleBase, 'l', saligia_Items.SickleBlade);
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.DaggerBase, 1, 0), 
				"I  ", 
				" I ",
				"I I",
				'I', saligia_Items.SinIngot);
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.DaggerBlade, 1, 0), 
				 " D",
				 "DI",
				 "ID",
				'I', saligia_Items.SinIngot, 'D', saligia_Items.DustCompound);
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.SinDagger, 1, 0), 
				"l", 
				"a",
				'a', saligia_Items.DaggerBase, 'l', saligia_Items.DaggerBlade);
		
		
		//Blocks--------------------------------------------------------------------------------------
		
		GameRegistry.addRecipe(new ItemStack(saligia_Blocks.CharcoalBlock), new Object[]{
				"ccc",
				"ccc",
				"ccc",
				'c', new ItemStack(Items.coal, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(Items.coal, 9, 1), new Object[]{
				"c",
				'c', new ItemStack(saligia_Blocks.CharcoalBlock)
		});
		
		//Smelting------------------------------------------------------------------------------------
		GameRegistry.addSmelting(saligia_Items.DustIngot, new ItemStack(saligia_Items.SinIngot), 0.1f);
	}

}
