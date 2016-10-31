/**
 * 
 */
package se.Matryoshika.Saligia.Utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Aug 16, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class NormalRecipeRegistry {
	
	public static void registerRecipes(){
		
		//Items================================================
		GameRegistry.addShapelessRecipe(
				new ItemStack(ContentRegistry.SIN_INGOT, 9), 
				new ItemStack(ContentRegistry.SIN_BLOCK));
		GameRegistry.addShapelessRecipe(
				new ItemStack(ContentRegistry.DUST_COMPOUND), 
				new ItemStack(ContentRegistry.SIN_ROSE_BLOCK));
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.DUST_INGOT),
				"CCC",
				"CIC",
				'C', ContentRegistry.DUST_COMPOUND, 'I', Items.IRON_INGOT
		);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.POISONCLAY, 8), 
				"CCC",
				"CPC",
				"CCC",
				'C', Items.CLAY_BALL, 'P', ContentRegistry.DUST_COMPOUND
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.JAR, 4), 
				" P ",
				"P P",
				"PPP",
				'P', ContentRegistry.POISONCLAY
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.SPIDER_EYE_JAR), 
				"SSS",
				"SJS",
				"SSS",
				'S', Items.SPIDER_EYE, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.ZOMBIE_TEETH_JAR), 
				"TTT",
				"TJT",
				"TTT",
				'T', ContentRegistry.ZOMBIETOOTH, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.SKELETON_VERTEBRAE_JAR), 
				"SSS",
				"SJS",
				"SSS",
				'S', ContentRegistry.SKELETONVERTEBRAE, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.CREEPER_ASH_JAR), 
				"CCC",
				"CJC",
				"CCC",
				'C', ContentRegistry.CREEPERASH, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.OCELOT_CLAW_JAR), 
				"OOO",
				"OJO",
				"OOO",
				'O', ContentRegistry.OCELOTCLAW, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.MAGMA_CREAM_JAR), 
				"MMM",
				"MJM",
				"MMM",
				'M', Items.MAGMA_CREAM, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.GHAST_TEAR_JAR), 
				"TTT",
				"TJT",
				"TTT",
				'T', Items.GHAST_TEAR, 'J', ContentRegistry.JAR
				);
		
		GameRegistry.addRecipe(new ItemStack(ContentRegistry.WOLF_BRAIN_JAR), 
				"BBB",
				"BJB",
				"BBB",
				'B', ContentRegistry.WOLFBRAIN, 'J', ContentRegistry.JAR
				);
		
		//Blocks===============================================
		ItemStack SIN_BLOCK = new ItemStack(ContentRegistry.SIN_BLOCK);
		GameRegistry.addRecipe(SIN_BLOCK, new Object[]{
				"III",
				"III",
				"III",
				'I', ContentRegistry.SIN_INGOT
		});
		
		
		//Furnace==============================================
		GameRegistry.addSmelting(new ItemStack(ContentRegistry.DUST_INGOT), new ItemStack(ContentRegistry.SIN_INGOT), 8);
		GameRegistry.addSmelting(new ItemStack(ContentRegistry.SOFTJAR), new ItemStack(ContentRegistry.JAR), 10);
	}

}
