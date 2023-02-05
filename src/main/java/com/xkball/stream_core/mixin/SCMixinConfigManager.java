package com.xkball.stream_core.mixin;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfigManager.class)
public abstract class SCMixinConfigManager {
    
    @Inject(method = "loadData",at = @At("RETURN"),remap = false)
    private static void injectLoadData(ASMDataTable data, CallbackInfo ci){
        FMLLog.log.info("Stream core config started loading @Config anotation data");
        com.xkball.stream_core.config.manager.ConfigManager.loadConfigs(data);
    }
}
