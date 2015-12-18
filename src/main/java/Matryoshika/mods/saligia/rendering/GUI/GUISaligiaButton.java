package Matryoshika.mods.saligia.rendering.GUI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GUISaligiaButton extends GuiButton{
    public GUISaligiaButton(int id, int x, int y, int i, int j, String string) {
		super(id, x, y, string);
	}

	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
    /** Button width in pixels */
    public int width;
    /** Button height in pixels */
    public int height;
    /** The x position of this control. */
    public int xPosition;
    /** The y position of this control. */
    public int yPosition;
    /** The string displayed on this control. */
    public String displayString;
    public int id;
    /** True if this control is enabled, false to disable. */
    public boolean enabled;
    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean field_146123_n;
    private static final String __OBFID = "CL_00000668";
    public int packedFGColour;


    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int getHoverState(boolean bool){
        byte b0 = 1;

        if (!this.enabled)
        {
            b0 = 0;
        }
        else if (bool)
        {
            b0 = 2;
        }

        return b0;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int x, int y){
        if (this.visible){
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, x, y);
            int l = 14737632;

            if (packedFGColour != 0){
                l = packedFGColour;
            }
            else if (!this.enabled){
                l = 10526880;
            }
            else if (this.field_146123_n){
                l = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int x, int y) {}

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int x, int y) {}

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int x, int y){
        return this.enabled && this.visible && x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
    }

    public boolean func_146115_a(){
        return this.field_146123_n;
    }

    public void func_146111_b(int p_146111_1_, int p_146111_2_) {}

    public void func_146113_a(SoundHandler sound){
        sound.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
    }

    public int getButtonWidth(){
        return this.width;
    }

    public int func_154310_c(){
        return this.height;
    }
}
