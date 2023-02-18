package com.xkball.stream_core.config.loader;

import com.google.gson.JsonObject;
import com.xkball.stream_core.common.block.BlockType;
import com.xkball.stream_core.config.manager.ConfigLoader;

public class BlockTypeLoader implements ConfigLoader<BlockType> {
    
    @Override
    public JsonObject write(BlockType data, ConfigLoader<?>... subLoaders) throws IllegalAccessException {
        JsonObject result = new JsonObject();
        result.addProperty("blockType","default");
        return result;
    }
    
    @Override
    public BlockType read(JsonObject json,ConfigLoader<?>... subLoaders) {
        return "default".equals(json.get("blockType").getAsString())? BlockType.Default:null;
    }
}