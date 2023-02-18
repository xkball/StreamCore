package com.xkball.stream_core.config;

import com.xkball.stream_core.common.block.BlockProperty;
import com.xkball.stream_core.common.block.BlockType;
import com.xkball.stream_core.common.block.CustomBlockBase;
import com.xkball.stream_core.config.loader.BasicConfigLoaders;
import com.xkball.stream_core.config.loader.CustomBlockBaseLoader;
import com.xkball.stream_core.config.loader.ListLoader;
import com.xkball.stream_core.config.manager.Config;

import java.util.ArrayList;
import java.util.List;

public class SCConfigs {
    
    @Config(loader = BasicConfigLoaders.BooleanConfigLoader.class)
    public static boolean testMode = false;
    
    @Config(loader = BasicConfigLoaders.BooleanConfigLoader.class)
    public static boolean enableAutoReg = true;
    
    //名称 方块类型
    @Config(loader = ListLoader.class,
            path = "customBlocks")
   // @Config.SubLoaders(loaders = {BasicConfigLoaders.StringConfigLoader.class,BlockTypeLoader.class})
    @Config.SubLoaders(loaders = {CustomBlockBaseLoader.class})
    public static List<CustomBlockBase> autoRegBlocks = new ArrayList<CustomBlockBase>(){
        {
            this.add( new CustomBlockBase(BlockType.Default,new BlockProperty()
                    .setBlockHardness(5)
                    .setBlockResistance(200)
                    .setLightValue(10)
                    .setTranslucent(true),
                    "moss"));
        }
    };
}
