package com.xkball.stream_core.config;

import com.xkball.stream_core.common.block.BlockType;
import com.xkball.stream_core.config.loader.BasicConfigLoaders;
import com.xkball.stream_core.config.loader.MapConfigLoader;
import com.xkball.stream_core.config.manager.Config;

import java.util.HashMap;
import java.util.Map;

public class SCConfigs {
    
    @Config(loader = BasicConfigLoaders.BooleanConfigLoader.class)
    public static boolean testMode = false;
    
    @Config(loader = BasicConfigLoaders.BooleanConfigLoader.class)
    public static boolean enableAutoReg = true;
    
    //名称 方块类型
    @Config(loader = MapConfigLoader.class)
    @Config.SubLoaders(loaders = {BasicConfigLoaders.StringConfigLoader.class, BasicConfigLoaders.BlockTypeConfigLoader.class})
    public static Map<String, BlockType> autoRegBlocks = new HashMap<>();
}
