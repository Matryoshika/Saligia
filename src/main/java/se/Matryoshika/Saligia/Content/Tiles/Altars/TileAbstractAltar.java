/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Altars;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import se.Matryoshika.Saligia.API.Recipes.AltarRecipeInjector;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimunCatcher;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Utils.AltarRecipes;

/**
 * This class was created by Matryoshika Aug 17, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public abstract class TileAbstractAltar extends TileEntity implements ITickable, IItemHandler, IAnimunCatcher{
	
	ItemStack[] inventorySlots = new ItemStack[getSlots()];
	AltarRecipes currentRecipe;
	
	private static final String TAG_ANIMUN = "animun";
	private static final String TAG_REQUIRED_ANIMUN = "required_animun";
	
	protected ItemStack output = null;
	
	int tier;
	int hoverItem = 0;
	int timer = 0;
	int cooldown = 0;
	int requiredAnimun = 0;
	int animun = 0;
	IInventory inv = (IInventory) this;
	
	

	@Override
	public void update(){
		if(worldObj.isRemote)
			return;
		
		hoverItem++;
		if(hoverItem >= 180)
			hoverItem = 0;
		
		if(output == null)
			timer = 0;
		else
			timer++;
	}
	
	
	
	
	public boolean addItem(EntityPlayer player, ItemStack stack){
		
		if(cooldown > 0 || stack.getItem() == ContentRegistry.RITUAL_ACTIVATOR || (stack.getItem() instanceof ItemBlock && this.getStackInSlot(0) != null))
			return false;
		
		if(requiredAnimun != 0)
			return false;
		
		boolean did = false;
		
		for(int i = 0; i < getSlots(); i++){
			
			if(getStackInSlot(i) == null){
				
				did = true;
				ItemStack stackToAdd = stack.copy();
				stackToAdd.stackSize = 1;
				insertItem(i, stackToAdd, false);
				
				if(player == null || !player.capabilities.isCreativeMode){
					stack.stackSize--;
					
					if(stack.stackSize == 0 && player != null)
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					
				}
				break;
			}
			
			if(did)
				dispatchTEToNearbyPlayers(worldObj, pos);
		}
		
		
		return true;
	}
	
	
	
	
	
	public void tryCraft(){
		inv = (IInventory) this;
		
		AltarRecipes recipe = null;
		if(!worldObj.isRemote){
			
			if(hasValidRecipe()){
				for(AltarRecipes recipe_ : AltarRecipeInjector.saligiaAltarRecipes){
					if(recipe_.matches(inv)){
						recipe = recipe_;
						break;
					}
				}
				
				for(int i = 0; i < recipe.getOutputAmount(); i++){
					ItemStack output = recipe.result().copy();
					
					EntityItem entity = new EntityItem(worldObj, this.pos.getX()+0.5, this.pos.getY()+1.2, this.pos.getZ()+0.5, output);
					worldObj.spawnEntityInWorld(entity);
					entity.motionX = 0; entity.motionY = 0; entity.motionZ = 0;
					entity.setPickupDelay(20);
				}
			}
		}
	}
	
	
	
	
	public boolean hasValidRecipe(){
		
		for(AltarRecipes recipe : AltarRecipeInjector.saligiaAltarRecipes){
			if(recipe.matches(inv) && recipe.tier <= getTier())
				return true;
		}
		
		return false;
	}
	
	
	
	public int getTier(){
		return tier;
	}
	
	
	public void setTier(int tier){
		this.tier = tier;
	}
	
	
	
	public static void dispatchTEToNearbyPlayers(World world, BlockPos pos){
		TileEntity tile = world.getTileEntity(pos);
		if(tile != null)
			dispatchTEToNearbyPlayers(tile);
	}
	
	
	
	public void updateRecipe(){
		int requiredAnimun = this.requiredAnimun;
		
		getAnimun : {
			for(AltarRecipes recipe : AltarRecipeInjector.saligiaAltarRecipes){
				if(recipe.matches(inv)){
					this.requiredAnimun = recipe.getAnimunUsage();
					break getAnimun;
				}
				this.requiredAnimun = 0;
			}
		}
	}
	
	
	
	public static void dispatchTEToNearbyPlayers(TileEntity tile){
		World world = tile.getWorld();
		List players = world.playerEntities;
		
		if(!world.isRemote){
			for(Object player : players){
				if(player instanceof EntityPlayerMP){
					EntityPlayerMP mp = (EntityPlayerMP) player;
					if(pointDistancePlane(mp.posX, mp.posZ, tile.getPos().getX()+0.5, tile.getPos().getZ()+0.5) < 64){
						if(tile.getUpdatePacket() != null)
							mp.connection.sendPacket(tile.getUpdatePacket());
					}
				}
			}
		}
	}
	
	
	
	public static float pointDistancePlane(double x1, double z1, double x2, double z2){
		return (float) Math.hypot(x1-x2,  z1-z2);
	}
	
	
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		NBTTagList nbtlist = new NBTTagList();
		
		for(int i = 0; i < this.inventorySlots.length; i++){
			
			if(this.inventorySlots[i] != null){
				
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				this.inventorySlots[i].writeToNBT(compound);
				nbtlist.appendTag(compound);
			}
		}
		nbt.setTag("Items", nbtlist);
		nbt.setInteger(TAG_ANIMUN, animun);
		nbt.setInteger(TAG_REQUIRED_ANIMUN, requiredAnimun);
		
		return nbt;
	}
	
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		NBTTagList nbtlist = nbt.getTagList("Items", 10);
		this.inventorySlots = new ItemStack[getSlots()];
		
		for(int i = 0; i < nbtlist.tagCount(); i++){
			NBTTagCompound compound = nbtlist.getCompoundTagAt(i);
			int j = compound.getByte("Slot") & 255;
			
			if(j >= 0 && j < this.inventorySlots.length){
				this.inventorySlots[j] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		animun = nbt.getInteger(TAG_ANIMUN);
		requiredAnimun = nbt.getInteger(TAG_REQUIRED_ANIMUN);
	}
	
	
	
	public int findAnimunStored(){
		return animun;
	}
	
	
	
	public boolean isFull(){
		return animun >= requiredAnimun;
	}
	
	
	
	public void recieveAnimun(int animun){
		this.animun = Math.min(this.animun + animun, requiredAnimun);
	}
	
	
	
	public int returnTargetedUser(){
		return requiredAnimun;
	}
	
	
	
	public boolean isEmpty(){
		
		for(int i = 0; i < getSlots(); i++){
			
			if(getStackInSlot(i) != null)
				return false;
		}
		
		return true;
	}
	
	
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
	}
	
	
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}

}
