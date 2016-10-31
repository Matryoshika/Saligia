/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Utility;

import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 11, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileMobKiller extends CustomTileClass implements ITickable{
	
	public int TIMER = 0;
	private final int LIMIT = 5 * 20;
	private FakePlayer fakePlayer;

	public TileMobKiller(){
		this.name = "mobkiller";
	}
	
	@Override
	public double[] colourScheme(){
		return new double[]{128, 0, 0};
	}
	
	
	@Override
	public void update() {
		if(worldObj.isRemote)
			return;
		
		TIMER++;
		
		if(TIMER >= LIMIT){
			TIMER = 0;
			activate();
		}
		
	}
	
	private void activate(){
		fakePlayer = Saligia.getFakePlayer();
		
		List<EntityLiving> entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(getPos().add(-2, -1, -2), getPos().add(3, -3, 3)));
		
		for(EntityLiving entity : entities){
			if((entity instanceof EntityCreature) && entity.isNonBoss()){
				if(entity.isEntityAlive()){
					entity.attackEntityFrom(DamageSource.causePlayerDamage(fakePlayer), (float) Math.pow(666, 3));
				}
			}
		}
	}

}
