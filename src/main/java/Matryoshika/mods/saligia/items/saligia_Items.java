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
import net.minecraft.nbt.NBTTagCompound;
import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.utils.CreativeTabMatryoshika;
import Matryoshika.mods.saligia.items.relics.ItemAvaritiaPick;
import Matryoshika.mods.saligia.items.relics.ItemBloodStone;
import Matryoshika.mods.saligia.items.relics.ItemWitheringShield;
import Matryoshika.mods.saligia.items.runes.ItemRune;
import Matryoshika.mods.saligia.items.souls.*;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
	public static Item RitualActivator;
	public static Item LibroSaligia;
	public static Item Soul;
	public static Item BloodStone;
	public static Item AvaritiaPickaxe;
	public static Item WitheringShield;
	public static Item FelMeat;
	public static Item Rune;
	public static Item DustCompound;
	public static Item DustIngot;
	public static Item SinIngot;
	public static Item SickleBlade;
	public static Item SickleBase;
	public static Item DaggerBlade;
	public static Item DaggerBase;
	public static Item MobHarness;
	
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
		ItemList.add(RitualActivator = new ItemRitualActivator(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(LibroSaligia = new ItemLibroSaligia(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(BloodStone = new ItemBloodStone(SIN).setCreativeTab(saligia.MatryoshikaTab));
		if(saligia.isPickEnabled == true){ItemList.add(AvaritiaPickaxe = new ItemAvaritiaPick(SIN).setCreativeTab(saligia.MatryoshikaTab));}
		ItemList.add(WitheringShield = new ItemWitheringShield(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(FelMeat = new ItemFelMeat(3, 0.2f, false, new PotionEffect(Potion.moveSpeed.id, 600, 0),new PotionEffect(Potion.regeneration.id, 100, 0)).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(Rune = new ItemRune(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(DustCompound = new ItemDustCompound(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(DustIngot = new ItemDustIngot(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(SinIngot = new ItemSinIngot(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(SickleBlade = new ItemDeathHandBlade(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(SickleBase = new ItemDeathHandBase(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(DaggerBlade = new ItemDaggerBlade(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(DaggerBase = new ItemDaggerBase(SIN).setCreativeTab(saligia.MatryoshikaTab));
		ItemList.add(MobHarness = new ItemMobHarness(SOUL).setCreativeTab(saligia.MatryoshikaTab));
		
		
		for(Item Item:ItemList){
			GameRegistry.registerItem(Item,  Item.getUnlocalizedName());
		
		}
		
		
	}

}
