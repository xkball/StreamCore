package com.xkball.stream_core.mixin;

import com.xkball.stream_core.StreamCore;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EntityRenderer.class)
public abstract class CamRotateTest {
    private static final Random random = new Random(114514);
    
    @Inject(method = "renderWorld",at = @At(value = "HEAD"))
    public void onRenderWorld(float partialTicks, long finishTimeNano, CallbackInfo ci){
        boolean b = random.nextBoolean();
        GlStateManager.rotate(random.nextFloat(),b?1:0,0f,b?0:1);
        StreamCore.log("t");
    }
}
