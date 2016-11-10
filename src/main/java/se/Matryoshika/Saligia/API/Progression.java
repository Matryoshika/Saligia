/**
 * 
 */
package se.Matryoshika.Saligia.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

/**
 * This class was created by Matryoshika Nov 2, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class Progression{
	
	private List<String> parents = new ArrayList<String>();
	//private HashMap<String,Progression> parents = new HashMap<String,Progression>();
	private List<String> childs = new ArrayList<String>();
	private ProgressionType type;
	private boolean unlocked = false;
	private String name;
	private int x, y;
	
	
	public static enum ProgressionType{
		ALTAR, RITUAL, SUMMONING, NECROMANCY, ANIMUN, UTILITY, EQUIPMENT, CROSSMOD, ENVIRONMENT
	}
	
	
	public Progression(ProgressionType type, String name, int x, int y){
		this.type = type;
		this.name = name;
		this.x = x;
		this.y = y;
		
	}
	
	public void setTypeFromString(String name){
		type = ProgressionType.valueOf(name);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isUnlocked(){
		return unlocked;
	}
	
	public void setUnlocked(boolean bool){
		unlocked = bool;
	}
	
	public List<String> getParents(){
		return parents;
	}
	
	public List<String> getChildren(){
		return childs;
	}
	
	public Progression addParent(Progression parent){
		parents.add(parent.getName());
		return this;
	}
	
	public Progression addChild(Progression child){
		childs.add(child.getName());
		return this;
	}
	
	/**
	 * For addons/mods that do cross-mod interaction, and need
	 * to change parents for existing progressions.
	 * DON'T **** PROGRESSION UP or Saligia will issue some -nice- cross-mod
	 * interaction back.
	 */
	public Progression removeParent(String parent){
		parents.remove(parent);
		return this;
	}
	
	/**
	 * For addons/mods that do cross-mod interaction, and need
	 * to change children for existing progressions.
	 * DON'T **** PROGRESSION UP or Saligia will issue some -nice- cross-mod
	 * interaction back.
	 */
	public Progression removeChild(String child){
		childs.remove(child);
		return this;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void tryUnlock(EntityPlayer player){
		
	}

}
