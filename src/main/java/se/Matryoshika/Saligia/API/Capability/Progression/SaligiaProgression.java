/**
 * 
 */
package se.Matryoshika.Saligia.API.Capability.Progression;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import se.Matryoshika.Saligia.API.Progression;
import se.Matryoshika.Saligia.Content.Progression.ProgressionRegistry;

/**
 * This class was created by Matryoshika Nov 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class SaligiaProgression implements ISaligiaProgression{
	
	private EntityPlayer player = null;
	private HashMap<String,Boolean> map = new HashMap<String,Boolean>();
	
	public SaligiaProgression(EntityPlayer player){
		this.player = player;
		for(Progression prog : ProgressionRegistry.getBlankProgressions())
			map.put(prog.getName(), false);
	}


	@Override
	public boolean getPlayerProgression(EntityPlayer player, String key) {
		
		return map.get(key);
	}


	@Override
	public void setPlayerProgression(EntityPlayer player, String key, boolean done) {

		map.put(key, done);
		
	}
	
	@Override
	public EntityPlayer getPlayer() {
		return player;
	}


	@Override
	public void copyPlayerProgression(EntityPlayer playerOne, EntityPlayer playerTwo) {
		
		for(Progression prog : ProgressionRegistry.getBlankProgressions())
			if(playerOne.getEntityData().hasKey("saligia_progressions_bools") && playerTwo.getEntityData().hasKey("saligia_progressions_bools")){
				if(playerOne.getEntityData().hasKey(prog.getName()) && playerTwo.getEntityData().hasKey(prog.getName()))
					playerTwo.getEntityData().setBoolean(prog.getName(), playerOne.getEntityData().getBoolean(prog.getName()));
			}
		
	}

}
