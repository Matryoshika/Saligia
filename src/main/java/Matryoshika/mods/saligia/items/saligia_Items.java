package Matryoshika.mods.saligia.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.item.Item.ToolMaterial;
import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.utils.CreativeTabMatryoshika;
import Matryoshika.mods.saligia.items.souls.*;
import net.minecraftforge.common.util.EnumHelper;

public class saligia_Items {
	
	public static ToolMaterial SIN = EnumHelper.addToolMaterial("SIN", 3, -1, 15.0F, 21.0F, 0);
	public static ToolMaterial SACRIFICE = EnumHelper.addToolMaterial("sacrifice", 3, -1, 2.0F, -3.0F, 0);
	public static ToolMaterial BOWL = EnumHelper.addToolMaterial("bowl", 0, 1000, 0, 0, 0);
	public static ToolMaterial SOUL = EnumHelper.addToolMaterial("SOUL", 0, -1, 0, 0, 0);
	
	
	public static Item SinDagger;
	public static Item Deathhand;
	public static Item SoulCrucible;
	public static Item VillagerSoul;
	public static Item ZombieSoul;
	public static Item AnimalSoul;
	public static Item BuffMobSoul;
	public static Item SoulVortex;
	public static Item TapeMeasure;
	
	public static List<Item>ItemList=new ArrayList<Item>();
	
	public static void registerItems() {
		ItemList.add(SinDagger = new ItemSinDagger(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(Deathhand = new ItemDeathHand(SACRIFICE).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(SoulCrucible = new ItemSoulCrucible(BOWL).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(VillagerSoul = new VillagerSoul(SOUL).setCreativeTab(saligia.MatryoshikaTab)); 
		ItemList.add(ZombieSoul = new ZombieSoul(SOUL).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(AnimalSoul = new AnimalSoul(SOUL).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(BuffMobSoul = new BuffMobSoul(SOUL).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(SoulVortex = new ItemSoulVortex(SOUL).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(TapeMeasure = new ItemTapeMeasure(SIN).setCreativeTab(saligia.MatryoshikaTab));
		
		for(Item Item:ItemList){
			GameRegistry.registerItem(Item,  Item.getUnlocalizedName());
			
			
		ItemStack SinDagger = new ItemStack(saligia_Items.SinDagger );
		SinDagger.addEnchantment(saligia.purify, 1);
		GameRegistry.addShapelessRecipe(SinDagger, new Object[]
				{
					Items.flint, 
					Items.stone_sword
				});
		
		GameRegistry.addRecipe(new ItemStack(saligia_Items.SoulCrucible), new Object[]{
				"   ",
				"IBI",
				" I ",
				'I', Items.iron_ingot, 'B', Items.bowl
		});
		}
		
	}

}
