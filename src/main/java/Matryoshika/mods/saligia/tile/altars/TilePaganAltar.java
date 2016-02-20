package Matryoshika.mods.saligia.tile.altars;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.API.saligiaAPI;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.blocks.altars.PaganAltar;
import Matryoshika.mods.saligia.entities.misc.saligiaAvaritiaParticle;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.items.runes.ItemRune;
import Matryoshika.mods.saligia.rendering.tilePaganAltarRenderer;
import Matryoshika.mods.saligia.utils.AltarRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class TilePaganAltar extends TileEntity implements ISidedInventory{
	
	ItemStack[] inventorySlots = new ItemStack[getSizeInventory()];
	
	private static final String TAG_ENERGY = "energy";
	private static final String TAG_REQUIRED_ENERGY = "requiredEnergy";
	
	
	AltarRecipes recipeInUse;
	protected ItemStack output = null;
	
	public int requiredEnergy = 0;
	int energy = 0;
	public int cooldown = 0;
	public int time = 0;
	public int hoverItem = 0;
	
	IInventory otherInv = this;
	
	
	@Override
	public void updateEntity() {
		hoverItem++;
		if(hoverItem >= 180){
			hoverItem = 0;
		}
		
		if (worldObj.isRemote)
			return;

		if (output == null)
			time = 0;
		else {
			time++;
		}
	}
	
	public boolean addItem(EntityPlayer player, ItemStack stack) {
		if(cooldown > 0 || stack.getItem() == saligia_Items.LibroSaligia || (stack.getItem() instanceof ItemBlock && this.getStackInSlot(0) != null))
			return false;

		if(requiredEnergy != 0)
			return false;

		boolean did = false;

		for(int i = 0; i < getSizeInventory(); i++)
			if(getStackInSlot(i) == null) {
				did = true;
				ItemStack stackToAdd = stack.copy();
				stackToAdd.stackSize = 1;
				setInventorySlotContents(i, stackToAdd);

				if(player == null || !player.capabilities.isCreativeMode) {
					stack.stackSize--;
					if(stack.stackSize == 0 && player != null)
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}

				break;
			}

		if(did)
			dispatchTEToNearbyPlayers(worldObj, xCoord, yCoord, zCoord);

		return true;
	}
	
	public void tryCraft(){
		otherInv = (IInventory) this;
		
		AltarRecipes recipe = null;
		if(!worldObj.isRemote) {
			if(hasValidRecipe()){
				
				for(AltarRecipes recipe_ : saligiaAPI.saligiaAltarRecipes) {
					if(recipe_.matches(this)) {
						recipe = recipe_;
						break;
					}
				}
				//System.out.println("has valid recipe");
				for(int i = 0; i < recipe.getOutputAmount(); i++){
					ItemStack output = recipe.result().copy();
					if(output.getItem() instanceof ItemRune){
						if(this.getStackInSlot(0).getItem() instanceof ItemRune){
							if(this.getStackInSlot(0).stackTagCompound.getInteger("saligiaTier")<3){
								output.stackTagCompound = new NBTTagCompound();
								output.stackTagCompound.setInteger("saligiaTier", this.getStackInSlot(0).stackTagCompound.getInteger("saligiaTier")+1);
								output.stackTagCompound.setString("saligiaElement", this.getStackInSlot(0).stackTagCompound.getString("saligiaElement"));
							}
							else{
								return;
							}
						}
					}
					EntityItem entity = new EntityItem(worldObj, this.xCoord+0.5, this.yCoord+1.2, this.zCoord+0.5, output);
					worldObj.spawnEntityInWorld(entity);
					entity.motionX =0; entity.motionY =0; entity.motionZ =0;
					entity.delayBeforeCanPickup = 20;
				}
				
			}	
		}
	}
	
	public boolean hasValidRecipe() {
		
		for(AltarRecipes recipe : saligiaAPI.saligiaAltarRecipes){
			if(recipe.matches(this) && recipe.tier <= tier())
				return true;
		}

		return false;
	}
	
	
	public void updateRecipe() {
		int requiredEnergy = this.requiredEnergy;

		getEnergy : {
			for(AltarRecipes recipe : saligiaAPI.saligiaAltarRecipes)
				if(recipe.matches(this)) {
					this.requiredEnergy = recipe.getSoulEUsage();
					break getEnergy;
				}
			this.requiredEnergy = 0;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventorySlots.length; ++i)
        {
            if (this.inventorySlots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventorySlots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);
		

		tag.setInteger(TAG_ENERGY, energy);
		tag.setInteger(TAG_REQUIRED_ENERGY, requiredEnergy);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.inventorySlots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventorySlots.length)
            {
                this.inventorySlots[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
		

		energy = tag.getInteger(TAG_ENERGY);
		requiredEnergy = tag.getInteger(TAG_REQUIRED_ENERGY);
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventorySlots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (inventorySlots[i] != null) {
			ItemStack stackAt;

			if (inventorySlots[i].stackSize <= j) {
				stackAt = inventorySlots[i];
				inventorySlots[i] = null;
				return stackAt;
			} else {
				stackAt = inventorySlots[i].splitStack(j);

				if (inventorySlots[i].stackSize == 0)
					inventorySlots[i] = null;

				return stackAt;
			}
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inventorySlots[i] = itemstack;
	}

	@Override
	public String getInventoryName() {
		return new String(StatCollector.translateToLocal("saligia.tile.PaganAltar"));
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int x, ItemStack stack) {
		if(stack.getItem() instanceof ItemBlock){
			if(x > 0){
				return false;
			}
			else{
				return true;
			}
		}
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		int accessibleSlot = -1;
		for(int i = 0; i < getSizeInventory(); i++)
			if(getStackInSlot(i) != null)
				accessibleSlot = i;

		return accessibleSlot == -1 ? new int[0] : new int[] { accessibleSlot };
	}

	@Override
	public boolean canInsertItem(int x, ItemStack stack, int z) {
		return true;
	}

	@Override
	public boolean canExtractItem(int x, ItemStack stack, int z) {
		return energy == 0;
	}
	
	public int findEnergyStored() {
		return energy;
	}
	
	public boolean isFull() {
		return energy >= requiredEnergy;
	}
	
	public void recieveMana(int energy) {
		this.energy = Math.min(this.energy + energy, requiredEnergy);
	}
	
	public int returnTargetedUser() {
		return requiredEnergy;
	}
	
	public static void dispatchTEToNearbyPlayers(World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null)
			dispatchTEToNearbyPlayers(tile);
	}
	
	public static void dispatchTEToNearbyPlayers(TileEntity tile) {
		World world = tile.getWorldObj();
		List players = world.playerEntities;
		if(!tile.getWorldObj().isRemote){
			for(Object player : players)
				if(player instanceof EntityPlayerMP) {
					EntityPlayerMP mp = (EntityPlayerMP) player;
					if(pointDistancePlane(mp.posX, mp.posZ, tile.xCoord + 0.5, tile.zCoord + 0.5) < 64){
						if(tile.getDescriptionPacket() != null)
							((EntityPlayerMP) player).playerNetServerHandler.sendPacket(tile.getDescriptionPacket());
					}
						
				}
		}
		
	}
	
	public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}
	
	public boolean isEmpty() {
		for(int i = 0; i < getSizeInventory(); i++)
			if(getStackInSlot(i) != null)
				return false;

		return true;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
	
	public int tier(){
		return 1;
	}

}
