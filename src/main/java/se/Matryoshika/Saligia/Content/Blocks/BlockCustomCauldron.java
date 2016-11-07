/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Tiles.TileCauldron;

/**
 * This class was created by Matryoshika Nov 2, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockCustomCauldron extends Block{
	
	
	
	public BlockCustomCauldron(){
		super(Material.IRON, MapColor.STONE);
		this.setRegistryName(Saligia.MODID, "cauldron");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, Integer.valueOf(0)));
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state){
        return true;
    }
	
	@Override
    public TileEntity createTileEntity(World world, IBlockState state){
        
		return new TileCauldron().setClientAge(System.currentTimeMillis());
    }
	
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 2);
    protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn){
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return FULL_BLOCK_AABB;
    }

    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    public boolean isFullCube(IBlockState state){
        return false;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
        int i = ((Integer)state.getValue(LEVEL)).intValue();
        float f = (float)pos.getY() + (6.0F + (float)(3 * i)) / 16.0F;

        if (!worldIn.isRemote && entityIn.isBurning() && i > 0 && entityIn.getEntityBoundingBox().minY <= (double)f)
        {
            entityIn.extinguish();
            this.setWaterLevel(worldIn, pos, state, i - 1);
        }
        if(!worldIn.isRemote  && i > 0 ){
        	
        	if(entityIn instanceof EntityLivingBase){
        		EntityLivingBase entity = (EntityLivingBase) entityIn;
        		entity.attackEntityFrom(DamageSource.causePlayerDamage(Saligia.getFakePlayer()), (float) 3);
        	}
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if (heldItem == null){
            return true;
        }
        else {
            int i = ((Integer)state.getValue(LEVEL)).intValue();
            Item item = heldItem.getItem();

            if (item == Items.WATER_BUCKET){
                if (i < 2 && !worldIn.isRemote){
                    if (!playerIn.capabilities.isCreativeMode){
                        playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                    }

                    playerIn.addStat(StatList.CAULDRON_FILLED);
                    this.setWaterLevel(worldIn, pos, state, 2);
                }

                return true;
            }
            else if (item == Items.BUCKET){
                if (i == 2 && !worldIn.isRemote){
                    if (!playerIn.capabilities.isCreativeMode){
                        --heldItem.stackSize;

                        if (heldItem.stackSize == 0){
                            playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
                        }
                        else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))){
                            playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    playerIn.addStat(StatList.CAULDRON_USED);
                    this.setWaterLevel(worldIn, pos, state, 0);
                }

                return true;
            }
            
            TileCauldron cauldron = (TileCauldron) worldIn.getTileEntity(pos);
            
            return cauldron.setStage(heldItem);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){
    	
    	int i = ((Integer)stateIn.getValue(LEVEL)).intValue();
    	if(i != 2 || !((TileCauldron)worldIn.getTileEntity(pos)).gotTables() || worldIn.getBlockState(pos.down()).getMaterial() != Material.FIRE){
    		return;
    	}
    	int amount = 0;
    	for(int times = 0; times <= ((TileCauldron)worldIn.getTileEntity(pos)).STATES *3; times++){
    		double d0 = ((double)pos.getX() + 0.5D)+(worldIn.rand.nextGaussian()*0.18);
	        double d1 = (double)pos.getY() + 0.7D;
	        double d2 = ((double)pos.getZ() + 0.5D)+(worldIn.rand.nextGaussian()*0.18);
	        double d3 = 0.22D;
	        double d4 = 0.27D;

	        worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, times/10d, times/10d, times/10d, new int[0]);
		}
        
    }

    public void setWaterLevel(World worldIn, BlockPos pos, IBlockState state, int level){
        worldIn.setBlockState(pos, state.withProperty(LEVEL, Integer.valueOf(MathHelper.clamp_int(level, 0, 2))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    public void fillWithRain(World worldIn, BlockPos pos){
        if (worldIn.rand.nextInt(20) == 1){
            float f = worldIn.getBiomeGenForCoords(pos).getFloatTemperature(pos);

            if (worldIn.getBiomeProvider().getTemperatureAtHeight(f, pos.getY()) >= 0.15F){
                IBlockState iblockstate = worldIn.getBlockState(pos);

                if (((Integer)iblockstate.getValue(LEVEL)).intValue() < 2){
                    worldIn.setBlockState(pos, iblockstate.cycleProperty(LEVEL), 2);
                }
            }
        }
    }

    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ContentRegistry.CUSTOM_CAULDRON);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state){
        return new ItemStack(ContentRegistry.CUSTOM_CAULDRON);
    }

    public boolean hasComparatorInputOverride(IBlockState state){
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos){
        return ((Integer)blockState.getValue(LEVEL)).intValue();
    }

    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(LEVEL, Integer.valueOf(meta));
    }

    public int getMetaFromState(IBlockState state){
        return ((Integer)state.getValue(LEVEL)).intValue();
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {LEVEL});
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos){
        return true;
    }

}
