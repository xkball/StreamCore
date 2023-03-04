package com.xkball.stream_core.mixin;

import com.xkball.stream_core.doc.SCDocGenerator;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(ConfigManager.class)
public abstract class SCMixinConfigManager {
    
    @Inject(method = "loadData",at = @At("RETURN"),remap = false)
    private static void injectLoadData(ASMDataTable data, CallbackInfo ci){
        FMLLog.log.info("Stream core config started loading @Config annotation data");
        long t1 = System.nanoTime();
        com.xkball.stream_core.config.manager.ConfigManager.loadConfigs(data);
        long t2 = System.nanoTime();
        FMLLog.log.info(String.format("Stream core config loaded in %d ns",t2-t1));
        FMLLog.log.info("Stream core started gen doc");
        
        try {
            long t3 = System.nanoTime();
            SCDocGenerator.genDoc(data);
            long t4 = System.nanoTime();
            FMLLog.log.info(String.format("Stream core doc generated in %d ns",t4-t3));
        } catch (Exception e) {
            FMLLog.log.info("Stream core doc gen failed");
            FMLLog.log.error("opps",e);
        }
    }
}
