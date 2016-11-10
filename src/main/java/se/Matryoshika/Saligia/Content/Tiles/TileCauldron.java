/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRedstone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Capability.Progression.CapabilitySaligiaProgression;
import se.Matryoshika.Saligia.API.Capability.Progression.ISaligiaProgression;
import se.Matryoshika.Saligia.API.Rendering.FX.ParticleRedstoneActive;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Blocks.BlockCustomCauldron;
import se.Matryoshika.Saligia.Content.Progression.ProgressionRegistry;

/**
 * This class was created by Matryoshika Nov 2, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileCauldron extends CustomTileClass{
	
	private long creationAge = 0;
	public final List<BlockPos> tables = new ArrayList<BlockPos>();
	
	public List<Boolean> hasJars = new ArrayList<Boolean>();
	public List<Item> jars = Arrays.asList(new Item[]{ContentRegistry.CREEPER_ASH_JAR, 
			ContentRegistry.GHAST_TEAR_JAR, 
			ContentRegistry.MAGMA_CREAM_JAR, 
			ContentRegistry.OCELOT_CLAW_JAR, 
			ContentRegistry.SKELETON_VERTEBRAE_JAR, 
			ContentRegistry.SPIDER_EYE_JAR,
			ContentRegistry.WOLF_BRAIN_JAR, 
			ContentRegistry.ZOMBIE_TEETH_JAR
	});
	
	public int STATES = -1;
	public int MAX_STATES = -1;
	
	public TileCauldron(){
		
		for(Item item : jars)
			hasJars.add(false);
		
		MAX_STATES = jars.size();
		tables.add(new BlockPos(6,0,3));
		tables.add(new BlockPos(6,0,-3));
		tables.add(new BlockPos(1,0,-6));
		tables.add(new BlockPos(1,0,6));
		tables.add(new BlockPos(-4,0,5));
		tables.add(new BlockPos(-4,0,-5));
		tables.add(new BlockPos(-6,0,0));
	}
	
	public TileCauldron setClientAge(long age){
		this.creationAge = age;
		return this;
	}
	
	public long getClientAge(){
		return creationAge;
	}
	
	public int getStage(){
		return STATES;
	}
	
	public boolean setStage(ItemStack item){
		int amount = -1;
		
		if(item == null)
			return false;
		
		BlockCustomCauldron cauldron = (BlockCustomCauldron) worldObj.getBlockState(getPos()).getBlock();
		int level = worldObj.getBlockState(getPos()).getValue(cauldron.LEVEL).intValue();
		if(level != 2)
			return false;
		
		if(!(worldObj.getBlockState(getPos().down()).getBlock() instanceof BlockFire))
			return false;
		
		if(!gotTables())
			return false;
		
		for(int i = 0; i < hasJars.size(); i++){
			if(item.getItem() == jars.get(i) && !hasJars.get(i)){
					
				hasJars.set(i, true);
				
				STATES = i;
				item.stackSize--;
				if(STATES == 7){
					worldObj.createExplosion(null, getPos().getX()+0.5, getPos().getY()+1, getPos().getZ()+0.5, 0, true);
					STATES = 8;
					((BlockCustomCauldron) getWorld().getBlockState(getPos()).getBlock()).setWaterLevel(worldObj, pos, worldObj.getBlockState(getPos()), 0);
					EntityPlayer player = worldObj.getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), 16, false);
					if(player != null){
						player.getFoodStats().setFoodLevel(0);
						player.getFoodStats().setFoodSaturationLevel(0);
						PotionEffect potion = new PotionEffect(MobEffects.WITHER, 10*20, 4);
						potion.getCurativeItems().clear();
						player.addPotionEffect(potion);
						if(player.hasCapability(CapabilitySaligiaProgression.SALIGIA_PROGRESSION_CAPABILITY, CapabilitySaligiaProgression.DEFAULT_FACING)){
							ISaligiaProgression progression = player.getCapability(CapabilitySaligiaProgression.SALIGIA_PROGRESSION_CAPABILITY, CapabilitySaligiaProgression.DEFAULT_FACING);
							progression.setPlayerProgression(player, "STARTER_INFUSION_MAIN", true);
							System.out.println(progression.getPlayerProgression(player, "STARTER_INFUSION_MAIN"));
						}
					}
				}
				return true;
				
			}
		}
		
		
		return false;
	}
	
	public boolean gotTables(){
		for(BlockPos position : tables){
			if(worldObj.getBlockState(getPos().add(position)).getBlock() != Blocks.ENCHANTING_TABLE)
				return false;
		}
		return true;
	}
	
	//64 blocks
	@Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared(){
        return 4096.0D;
    }
    
    @Override
    public AxisAlignedBB getRenderBoundingBox(){
    	
        return new AxisAlignedBB(getPos().add(-16, -16, -16), getPos().add(16, 16, 16));
    }

}
