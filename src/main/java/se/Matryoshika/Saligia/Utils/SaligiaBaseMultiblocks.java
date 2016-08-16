/**
 * 
 */
package se.Matryoshika.Saligia.Utils;

import net.minecraft.init.Blocks;
import se.Matryoshika.Saligia.API.Rituals.RitualRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Tiles.TileRitualCOTH;

/**
 * This class was created by Matryoshika Aug 13, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class SaligiaBaseMultiblocks {
	
	private static final Object[][] COTH_BLOCKS = new Object[][]{
		{-4, 0, 0, Blocks.STONEBRICK},{-3, 0, -3, Blocks.STONEBRICK},{0, 0, -4, Blocks.STONEBRICK},{3, 0, -3, Blocks.STONEBRICK},
		{4, 0, 0, Blocks.STONEBRICK},{3, 0, 3, Blocks.STONEBRICK},{0, 0, 4, Blocks.STONEBRICK},{-3, 0, 3, Blocks.STONEBRICK},		
		{-4, 1, 0, Blocks.STONEBRICK},{-3, 1, -3, Blocks.STONEBRICK},{0, 1, -4, Blocks.STONEBRICK},{3, 1, -3, Blocks.STONEBRICK},
		{4, 1, 0, Blocks.STONEBRICK},{3, 1, 3, Blocks.STONEBRICK},{0, 1, 4, Blocks.STONEBRICK},{-3, 1, 3, Blocks.STONEBRICK},		
		{-4, 2, 0, ContentRegistry.GHAST_BLOCK},{-3, 2, -3, ContentRegistry.GHAST_BLOCK},{0, 2, -4, ContentRegistry.GHAST_BLOCK},{3, 2, -3, ContentRegistry.GHAST_BLOCK},
		{4, 2, 0, ContentRegistry.GHAST_BLOCK},{3, 2, 3, ContentRegistry.GHAST_BLOCK},{0, 2, 4, ContentRegistry.GHAST_BLOCK},{-3, 2, 3, ContentRegistry.GHAST_BLOCK}
	};
	
	
	public static void register(){
		RitualRegistry.addRitual(ContentRegistry.BLOCK_RITUAL_COTH.getRitualName(), COTH_BLOCKS, TileRitualCOTH.class, ContentRegistry.BLOCK_RITUAL_COTH);
	}

}
