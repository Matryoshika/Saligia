package Matryoshika.mods.saligia.blocks.altars;

import java.util.ArrayList;
import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.utils.AltarRecipes;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class AltarCrafting {
	
	private static AltarRecipes recipeGhastlyBlock;
	private static AltarRecipes recipeRitualActivator;
	private static AltarRecipes recipeMobHarness;
	private static AltarRecipes recipeCrucible;
	
	private static AltarRecipes recipeRuneAcedia;
	private static AltarRecipes recipeRuneAvaritia;
	private static AltarRecipes recipeRuneGula;
	private static AltarRecipes recipeRuneInvidia;
	private static AltarRecipes recipeRuneIra;
	private static AltarRecipes recipeRuneLuxuria;
	private static AltarRecipes recipeRuneSuperbia;
	
	private static AltarRecipes recipeUpgradeRune;
	
	
	static ItemStack netherrack = new ItemStack(Blocks.netherrack);
	static ItemStack ghasttears = new ItemStack(Items.ghast_tear);
	static ItemStack leash = new ItemStack(Items.lead);
	static ItemStack string = new ItemStack(Items.string);
	static ArrayList<ItemStack> souls = new ArrayList<ItemStack>();
	static ItemStack runes = new ItemStack(saligia_Items.Rune);
	static ItemStack clay = new ItemStack(Items.clay_ball);
	static ItemStack star = new ItemStack(Items.nether_star);
	static ItemStack bed = new ItemStack(Items.bed);
	static ItemStack emerald = new ItemStack(Items.emerald);
	static ItemStack cake = new ItemStack(Items.cake);
	static ItemStack painting = new ItemStack(Items.painting);
	static ItemStack playerSkull = new ItemStack(Items.skull, 1, 3);
	static ArrayList<ItemStack> skulls = new ArrayList<ItemStack>();
	static ItemStack saddle = new ItemStack(Items.saddle);
	static ItemStack bowl = new ItemStack(Items.bowl);
	
	public static void init(){
		
		//Tier 1 recipes
		recipeGhastlyBlock = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(new ItemStack(Item.getItemFromBlock(saligia.GhastlyBlock)), 1, 1 ,0, netherrack, ghasttears, ghasttears, ghasttears, ghasttears, ghasttears, ghasttears, ghasttears, ghasttears);

		souls.add(new ItemStack(saligia_Items.AnimalSoul));
		souls.add(new ItemStack(saligia_Items.BuffMobSoul));
		souls.add(new ItemStack(saligia_Items.VillagerSoul));
		souls.add(new ItemStack(saligia_Items.ZombieSoul));
		for(int stack1 = 0; stack1 < souls.size(); stack1++){
			for(int stack2 = 0; stack2 < souls.size(); stack2++){
				for(int stack3 = 0; stack3 < souls.size(); stack3++){
					for(int stack4 = 0; stack4 < souls.size(); stack4++){
						recipeRitualActivator = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(new ItemStack(saligia_Items.RitualActivator), 1, 1, 0, new ItemStack(Items.emerald), souls.get(stack1), souls.get(stack2), souls.get(stack3), souls.get(stack4));
					}
				}
			}
		}
		
		recipeMobHarness = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(new ItemStack(saligia_Items.MobHarness), 5, 1, 0, new ItemStack(Items.ender_eye), leash, leash, leash, leash, string, string, string, string);
		
		for(int stack1 = 0; stack1 < souls.size(); stack1++){
			for(int stack2 = 0; stack2 < souls.size(); stack2++){
				for(int stack3 = 0; stack3 < souls.size(); stack3++){
					for(int stack4 = 0; stack4 < souls.size(); stack4++){
						recipeCrucible = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(new ItemStack(saligia_Items.SoulCrucible), 1, 1, 0, new ItemStack(Items.blaze_powder), bowl, bowl, bowl, bowl, souls.get(stack1), souls.get(stack2), souls.get(stack3), souls.get(stack4));
					}
				}
			}
		}
		
		
		//tier 2 recipes
		ItemStack runeAcedia = new ItemStack(saligia_Items.Rune);
		runeAcedia.stackTagCompound = new NBTTagCompound();
		runeAcedia.stackTagCompound.setString("saligiaElement", "Acedia");
		recipeRuneAcedia = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeAcedia, 1, 2, 0, star, clay, clay, clay, clay, bed, bed, bed, bed);
		
		ItemStack runeAvaritia = new ItemStack(saligia_Items.Rune);
		runeAvaritia.stackTagCompound = new NBTTagCompound();
		runeAvaritia.stackTagCompound.setString("saligiaElement", "Avaritia");
		recipeRuneAvaritia = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeAvaritia, 1, 2, 0, star, clay, clay, clay, clay, emerald, emerald, emerald, emerald);
		
		ItemStack runeGula = new ItemStack(saligia_Items.Rune);
		runeGula.stackTagCompound = new NBTTagCompound();
		runeGula.stackTagCompound.setString("saligiaElement", "Gula");
		recipeRuneAvaritia = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeGula, 1, 2, 0, star, clay, clay, clay, clay, cake, cake, cake, cake);
		
		ItemStack runeInvidia = new ItemStack(saligia_Items.Rune);
		runeInvidia.stackTagCompound = new NBTTagCompound();
		runeInvidia.stackTagCompound.setString("saligiaElement", "Invidia");
		recipeRuneAvaritia = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeInvidia, 1, 2, 0, star, clay, clay, clay, clay, playerSkull, playerSkull, playerSkull, playerSkull);
		
		ItemStack runeIra = new ItemStack(saligia_Items.Rune);
		runeIra.stackTagCompound = new NBTTagCompound();
		runeIra.stackTagCompound.setString("saligiaElement", "Ira");
		skulls.add(new ItemStack(Items.skull, 1, 0));
		skulls.add(new ItemStack(Items.skull, 1, 1));
		skulls.add(new ItemStack(Items.skull, 1, 2));
		skulls.add(new ItemStack(Items.skull, 1, 4));
		for(int stack1 = 0; stack1 < skulls.size(); stack1++){
			for(int stack2 = 0; stack2 < skulls.size(); stack2++){
				for(int stack3 = 0; stack3 < skulls.size(); stack3++){
					for(int stack4 = 0; stack4 < skulls.size(); stack4++){
						recipeRuneIra = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeIra, 1, 2, 0, star, clay, clay, clay, clay, skulls.get(stack1), skulls.get(stack2), skulls.get(stack3), skulls.get(stack4));
					}
				}
			}
		}
		ItemStack runeLuxuria = new ItemStack(saligia_Items.Rune);
		runeLuxuria.stackTagCompound = new NBTTagCompound();
		runeLuxuria.stackTagCompound.setString("saligiaElement", "Luxuria");
		recipeRuneLuxuria = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeLuxuria, 1, 2, 0, star, clay, clay, clay, clay, painting, painting, painting, painting);
		
		ItemStack runeSuperbia = new ItemStack(saligia_Items.Rune);
		runeSuperbia.stackTagCompound = new NBTTagCompound();
		runeSuperbia.stackTagCompound.setString("saligiaElement", "Superbia");
		recipeRuneSuperbia = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runeSuperbia, 1, 2, 0, star, clay, clay, clay, clay, saddle, saddle, saddle, saddle);
		
		
		
		//Tier 3 recipes
		recipeUpgradeRune = Matryoshika.mods.saligia.API.saligiaAPI.registerAltarRecipes(runes, 1, 3, 0, runes, star, star, star, star, star, star, star, star);
	}

}
