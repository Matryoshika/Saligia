/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * This class was created by Matryoshika Oct 23, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileGhastSpawner extends CustomTileClass implements ITickable{
	
	private int TIMER = 0;
	public int TIER = 1;
	private int LIMIT = (1*60)*20; //1 minute aka 1200 ticks
	private int RADII = 3;

	@Override
	public void update() {
		
		if(worldObj.isRemote)
			return;
		
		if(TIMER++ >= LIMIT/TIER){
			System.out.println(TIMER);
			for(BlockPos pos : BlockPos.getAllInBox(getPos().add(-2, 0, -2), getPos().add(3, 3, 3))){

				if(!worldObj.isAirBlock(pos) && pos.getX() != getPos().getX() && pos.getY() != getPos().getY() && pos.getZ() != getPos().getZ())
					return;
				
			}
			doSpawn();
			TIMER = 0;
		}
		
		
	}
	
	private void doSpawn(){
		EntityGhast ghast = new EntityGhast(worldObj);
		ghast.setLocationAndAngles(getPos().getX()+0.5, getPos().getY()+3, getPos().getZ()+0.5, worldObj.rand.nextFloat() * 360.0F, 0.0F);
		if(worldObj.getClosestPlayer(getPos().getX()+0.5, getPos().getY()+0.5, getPos().getZ()+0.5, 16, false) != null){
			ghast.setAttackTarget(worldObj.getClosestPlayer(getPos().getX()+0.5, getPos().getY()+0.5, getPos().getZ()+0.5, 16, false));
		}
		worldObj.spawnEntityInWorld(ghast);
	}

}
