package se.Matryoshika.Saligia.API.Rituals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface IRitualBlock{
	
	//Remember to call Saligia's RenderRegister.reg(BlockRitualMaster, ModelResourceLocation, meta)
	//to render the model.
	//Localization isn't mod-dependent, so do that in your own .lang files.
	
	/**
	 * @param name : The (full) name of the ritual this block belongs to.
	 * example: "Cognizance Of The Hellmouth"
	 */
	public Block setRitualName(String name);
	
	public String getRitualName();
	

}
