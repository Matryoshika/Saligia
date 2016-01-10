package Matryoshika.mods.saligia;

import Matryoshika.mods.saligia.entities.EntityAcedia;
import Matryoshika.mods.saligia.entities.EntityAvaritia;
import Matryoshika.mods.saligia.entities.EntityGula;
import Matryoshika.mods.saligia.entities.EntityInvidia;
import Matryoshika.mods.saligia.entities.EntityIra;
import Matryoshika.mods.saligia.entities.EntityLuxuria;
import Matryoshika.mods.saligia.entities.EntitySuperbia;
import Matryoshika.mods.saligia.rendering.entities.RenderAcedia;
import Matryoshika.mods.saligia.rendering.entities.RenderAvaritia;
import Matryoshika.mods.saligia.rendering.entities.RenderGula;
import Matryoshika.mods.saligia.rendering.entities.RenderInvidia;
import Matryoshika.mods.saligia.rendering.entities.RenderIra;
import Matryoshika.mods.saligia.rendering.entities.RenderLuxuria;
import Matryoshika.mods.saligia.rendering.entities.RenderSuperbia;
import Matryoshika.mods.saligia.utils.RenderHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	int ModEntityID;

	public void registerRenderers(){
		
		RenderingRegistry.registerEntityRenderingHandler(EntityAcedia.class, new RenderAcedia(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityAvaritia.class, new RenderAvaritia(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityGula.class, new RenderGula(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityInvidia.class, new RenderInvidia(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityIra.class, new RenderIra(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityLuxuria.class, new RenderLuxuria(new ModelBiped(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntitySuperbia.class, new RenderSuperbia(new ModelBiped(), 0));
		
		
		MinecraftForge.EVENT_BUS.register(new RenderHandler());
		
		
	}
	
}
