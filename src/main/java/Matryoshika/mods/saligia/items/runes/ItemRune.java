package Matryoshika.mods.saligia.items.runes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRune extends Item {
	
	public String runeProc;
	public int saligiaTier;
	public boolean compound = false;
	
	IIcon[] iconArray = new IIcon[8];
	String Texture = ":runeBack";
	
	public String[] elements = {"Acedia","Avaritia","Gula","Invidia","Ira","Luxuria","Superbia"};
	
    @SideOnly(Side.CLIENT)
    private IIcon Acedia;
    @SideOnly(Side.CLIENT)
    private IIcon Avaritia;
    @SideOnly(Side.CLIENT)
    private IIcon Gula;
    @SideOnly(Side.CLIENT)
    private IIcon Invidia;
    @SideOnly(Side.CLIENT)
    private IIcon Ira;
    @SideOnly(Side.CLIENT)
    private IIcon Luxuria;
    @SideOnly(Side.CLIENT)
    private IIcon Superbia;
	
	public ItemRune(ToolMaterial sin) {
		super();
		this.setUnlocalizedName("ItemRune");	
		//this.setIcon(":runeBack");
		//this.setTextureName(saligia.MODID+":runeBack");
		}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
		//Makes sure the rune can have NBT-data
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			}
		//Sets the Tier value. Default is 1. Higher is gained through altar-crafting
		//Change the second value in setInteger here to test Tier values
		if (!stack.getTagCompound().hasKey("saligiaTier")) {
			stack.getTagCompound().setInteger("saligiaTier", 1);
			this.saligiaTier = stack.getTagCompound().getInteger("saligiaTier");
		}
		//Sets the affinity of the rune
		if (!stack.getTagCompound().hasKey("saligiaElement")) {
			String randomElement = (elements[new Random().nextInt(elements.length)]);
			stack.getTagCompound().setString("saligiaElement", randomElement);
			
			List list = new ArrayList();
			EntityPlayer player = (EntityPlayer) entity;
			
			addInformation(stack, player, list, par5);
			
			setIcon(Texture+stack.getTagCompound().getString("saligiaElement"));
			this.setTextureName(Texture+stack.getTagCompound().getString("saligiaElement"));
			}
		//Forces an update to the Tier if it's less than default
		if(this.saligiaTier < 1){
			this.saligiaTier = stack.getTagCompound().getInteger("saligiaTier");
		}
		//If value is too high, resets it to T1
		if(this.saligiaTier > 3 && stack.getTagCompound().getInteger("saligiaTier") > 3){
			this.saligiaTier = 1;
			stack.getTagCompound().setInteger("saligiaTier", 1);
		}
		//System.out.println(saligia.MODID+Texture+"Ira");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par){
		//Sets the lore of the rune. Changes based on affinity
		if (stack.hasTagCompound() && compound == false){
			String main = StatCollector.translateToLocal("saligia.rune."+stack.getTagCompound().getString("saligiaElement").toLowerCase()+".main");
			String items = StatCollector.translateToLocal("saligia.rune."+stack.getTagCompound().getString("saligiaElement").toLowerCase()+".items");
			String tools = StatCollector.translateToLocal("saligia.rune."+stack.getTagCompound().getString("saligiaElement").toLowerCase()+".tools");
			String armour = StatCollector.translateToLocal("saligia.rune."+stack.getTagCompound().getString("saligiaElement").toLowerCase()+".armour");

			switch (stack.getTagCompound().getString("saligiaElement")){
			
			//Changes specific lore depending on tier & affinity
			case "Ira" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?+damage", Integer.toString(2*stack.getTagCompound().getInteger("saligiaTier"))));
				list.add(tools.replace("?(5*Tier)", Integer.toString(5*stack.getTagCompound().getInteger("saligiaTier"))));
				list.add(armour.replace("?(3*Tier)", Integer.toString(3*stack.getTagCompound().getInteger("saligiaTier"))));
				break;	
			}
			case "Superbia" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?(2*Tier)", Integer.toString(2*stack.getTagCompound().getInteger("tier"))));
				list.add(tools.replace("?(5*Tier)", Integer.toString(5*stack.getTagCompound().getInteger("tier"))));
				list.add(armour.replace("?(5*Tier)", Integer.toString(5*stack.getTagCompound().getInteger("tier"))));
				break;
			}
			case "Gula" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?(60/Tier)", Integer.toString(60/stack.getTagCompound().getInteger("saligiaTier"))));
				list.add(tools.replace("?(1*Tier)", Integer.toString(1*stack.getTagCompound().getInteger("saligiaTier"))));
				list.add(armour.replace("?(4shanks^Tier)", Integer.toString((int)Math.pow(4, stack.getTagCompound().getInteger("saligiaTier")))));
				break;
			}
			case "Invidia" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?(4^Tier)", Integer.toString((int)Math.pow(4, stack.getTagCompound().getInteger("saligiaTier")))));
				list.add(tools.replace("?(10*Tier)", Integer.toString(10*stack.getTagCompound().getInteger("saligiaTier"))));
				switch (stack.getTagCompound().getInteger("saligiaTier")){
				case 1:{list.add(armour.replace("?(tier)", "basic")); break;}
				case 2:{list.add(armour.replace("?(tier)", "")); break;}
				case 3:{list.add(armour.replace("?(tier)", "advanced")); break;}
				}
				break;	
			}
			case "Acedia" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?(48-(Tier^3))", Integer.toString(48-((int)Math.pow(stack.getTagCompound().getInteger("saligiaTier"), 3)))));
				String toolsAcediaFirstRep = tools.replace("?(4^Tier)", Integer.toString((int)Math.pow(4, stack.getTagCompound().getInteger("saligiaTier"))));
				list.add(toolsAcediaFirstRep.replace("?(Tier)", Integer.toString(stack.getTagCompound().getInteger("saligiaTier"))));
				switch (stack.getTagCompound().getInteger("saligiaTier")){
				case 1:{list.add(armour.replace("?(percentage)", "33")); break;}
				case 2:{list.add(armour.replace("?(percentage)", "67")); break;}
				case 3:{list.add(armour.replace("?(percentage)", "100")); break;}
				}
				break;
			}
			case "Avaritia" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?((4^Tier)/2)", Integer.toString(((int)Math.pow(4, stack.getTagCompound().getInteger("saligiaTier")))/2)));
				String toolsAvaritiaFirstRep = tools.replace("?(Tier*15)", Integer.toString(stack.getTagCompound().getInteger("saligiaTier")*15));
				list.add(toolsAvaritiaFirstRep.replace("?(Tier*2)", Integer.toString(stack.getTagCompound().getInteger("saligiaTier")*2)));
				list.add(armour.replace("?((8*Tier)/2)", Integer.toString((8*stack.getTagCompound().getInteger("saligiaTier"))/2)));
				break;
			}
			case "Luxuria" :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items.replace("?(Tier)", Integer.toString(stack.getTagCompound().getInteger("saligiaTier"))));
				String toolsLuxuriaFirstRep = tools.replace("?(50*Tier)", Integer.toString(50*stack.getTagCompound().getInteger("saligiaTier")));
				list.add(toolsLuxuriaFirstRep.replace("?((8*Tier)/2)", Integer.toString((8*stack.getTagCompound().getInteger("saligiaTier"))/2)));
				list.add(armour.replace("?(35*Tier)", Integer.toString(35*stack.getTagCompound().getInteger("saligiaTier"))));
				break;
			}
			
			
			//Default lore, fallback if dark voodoo appears
			default :{
				list.add(StatCollector.translateToLocal("saligia.rune.tier")+" "+stack.getTagCompound().getInteger("saligiaTier"));
				list.add(main);
				list.add(items);
				list.add(tools);
				list.add(armour);
				break;
			}
			
			}
		}
		
	}
	
	
	@Override
    public String getItemStackDisplayName(ItemStack stack){
		//Sets the runes' name
			return runeName(stack);
	}
	
	public String runeName(ItemStack stack){
		//Checks NBT-value and returns possible names
		if (!stack.hasTagCompound()){
			return "§kSource of Sin";
			}
		return StatCollector.translateToLocal("saligia.rune.default")+" "+stack.getTagCompound().getString("saligiaElement");
	}
	
	public void setIcon(String Texturename){
		Texturename = Texture;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		ItemStack stack = new ItemStack(this);
		this.itemIcon = register.registerIcon(saligia.MODID+Texture);
		
		this.Acedia =register.registerIcon(saligia.MODID+Texture+"Acedia");
		this.Avaritia =register.registerIcon(saligia.MODID+Texture+"Avaritia");
		this.Gula =register.registerIcon(saligia.MODID+Texture+"Gula");
		this.Invidia =register.registerIcon(saligia.MODID+Texture+"Invidia");
		this.Ira =register.registerIcon(saligia.MODID+Texture+"Ira");
		this.Luxuria =register.registerIcon(saligia.MODID+Texture+"Luxuria");
		this.Superbia =register.registerIcon(saligia.MODID+Texture+"Superbia");

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
		if(stack.hasTagCompound()){
			switch (stack.stackTagCompound.getString("saligiaElement")){
			case "Acedia" : {return this.Acedia;}
			case "Avaritia" : {return this.Avaritia;}
			case "Gula" : {return this.Gula;}
			case "Invidia" : {return this.Invidia;}
			case "Ira" : {return this.Ira;}
			case "Luxuria" : {return this.Luxuria;}
			case "Superbia" : {return this.Superbia;}
			}
		}
		return this.itemIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack stack){
		if(stack.hasTagCompound()){
			switch (stack.stackTagCompound.getString("saligiaElement")){
			case "Acedia" : {return this.Acedia;}
			case "Avaritia" : {return this.Avaritia;}
			case "Gula" : {return this.Gula;}
			case "Invidia" : {return this.Invidia;}
			case "Ira" : {return this.Ira;}
			case "Luxuria" : {return this.Luxuria;}
			case "Superbia" : {return this.Superbia;}
			}
		}
		return this.itemIcon;
	}
	
}
