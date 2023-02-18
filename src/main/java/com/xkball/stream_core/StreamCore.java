package com.xkball.stream_core;

import com.xkball.stream_core.config.SCConfigs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = StreamCore.MODID, name = StreamCore.NAME, version = StreamCore.VERSION)
public class StreamCore
{
    public static final String MODID = "stream_core";
    public static final String NAME = "Stream Core";
    public static final String VERSION = "1.0";
    
    public static File configDir = Loader.instance().getConfigDir();
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        //logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        log("Stream Core started loading");
       // ConfigManager.reload();
        log(SCConfigs.autoRegBlocks.toString());
    }
    
    
    public static void log(String s){
        logger.info(s);
    }
}
