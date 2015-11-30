package Matryoshika.mods.saligia.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBoss extends EntityMob implements IBossDisplayData {
	
	private static final float MAX_HP = 500F;

	public EntityBoss(World world) {
		super(world);
        setSize(1,2);
        this.isImmuneToFire = true;
        preventEntitySpawning = true;
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
	}
	
	@Override
	public void onEntityUpdate(){
		super.onEntityUpdate();
	}
	
	@Override
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }
	
	@Override
	public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }
	
	public EntityBoss(World world, double x, double y, double z){
		this(world);
		setPosition(x,y,z);		
	}
	
	public int getMaxSpawnedInChunk()
    {
        return 1;
    }
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MAX_HP);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(100D);
	}
	
	@Override
	public boolean isAIEnabled(){
		return true;
	}
	
	protected void entityInit(){
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
	
	public void setBesideClimbableBlock(boolean p_70839_1_){
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70839_1_){
            b0 = (byte)(b0 | 1);
        }
        else{
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }
	
	protected Entity findPlayerToAttack(){
            double d0 = 25.0D;
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
    }

}
