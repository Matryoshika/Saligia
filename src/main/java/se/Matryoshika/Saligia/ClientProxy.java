package se.Matryoshika.Saligia;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.API.Rendering.RenderMultiBlockEvent;
import se.Matryoshika.Saligia.API.Rendering.RenderMultiblock;
import se.Matryoshika.Saligia.API.Rendering.RenderingRegistryInjector;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Blocks.Utility.FileCreatorUtility;
import se.Matryoshika.Saligia.Content.Tiles.TileRitual;
import se.Matryoshika.Saligia.Rendering.RenderRegister;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		OBJLoader.INSTANCE.addDomain(Saligia.MODID);
		RenderRegister.registerRenderers();
		RenderingRegistryInjector.reg(ContentRegistry.BLOCK_RITUAL_COTH, new ModelResourceLocation(Saligia.MODID+":"+ContentRegistry.BLOCK_RITUAL_COTH.getUnlocName(), "inventory"), 0);
		
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(RenderMultiBlockEvent.class);
		
		FileCreatorUtility.createFiles();
	}
	
	@Override
	public void renderMultiblock(TileRitual te, double x, double y, double z){
		RenderMultiblock.renderAt(te, x, y, z);
	}

}
