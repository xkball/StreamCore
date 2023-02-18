package com.xkball.stream_core.config.loader;

import com.google.gson.JsonObject;
import com.xkball.stream_core.common.block.BlockProperty;
import com.xkball.stream_core.common.block.BlockType;
import com.xkball.stream_core.common.block.CustomBlockBase;
import com.xkball.stream_core.config.manager.ConfigLoader;
import net.minecraft.block.material.MapColor;

public class CustomBlockBaseLoader implements ConfigLoader<CustomBlockBase> {
    
    @Override
    public JsonObject write(CustomBlockBase data, ConfigLoader<?>... subLoaders) throws IllegalAccessException {
        JsonObject result = new JsonObject();
        BlockProperty blockProperty = data.getBlockProperty();
        ConfigLoader<BlockType> blockTypeConfigLoader = new BlockTypeLoader();
        //ConfigLoader<Float> floatConfigLoader = new BasicConfigLoaders.FloatConfigLoader();
        //ConfigLoader<String> stringConfigLoader = new BasicConfigLoaders.StringConfigLoader();
        result.add("type",blockTypeConfigLoader.write(data.getBlockType()));
        result.addProperty("id",data.getId());
        result.addProperty("hardness",blockProperty.getBlockHardness());
        result.addProperty("resistance",blockProperty.getBlockResistance());
        result.addProperty("lightValue",blockProperty.getLightValue());
        result.addProperty("blockParticleGravity",blockProperty.getBlockParticleGravity());
        result.addProperty("canBurn",blockProperty.isCanBurn());
        result.addProperty("replaceable",blockProperty.isReplaceable());
        result.addProperty("isTranslucent",blockProperty.isTranslucent());
        if(blockProperty.getRequiresTool() != null) {
            result.addProperty("requiresTool", blockProperty.getRequiresTool());
            result.addProperty("harvestLevel", blockProperty.getHarvestLevel());
        }
        if(blockProperty.getBlockMapColor() != null){
            result.addProperty("blockMapColor",blockProperty.getBlockMapColor().colorIndex);
        }
        if(blockProperty.getBlockSoundType() != null){
            result.addProperty("blockSoundType",BlockProperty.soundTypes.indexOf(blockProperty.getBlockSoundType()));
        }
        return result;
    }
    
    @Override
    public CustomBlockBase read(JsonObject json, ConfigLoader<?>... subLoaders) {
        ConfigLoader<BlockType> blockTypeConfigLoader = new BlockTypeLoader();
        String id = json.get("id").getAsString();
        BlockType type = blockTypeConfigLoader.read(json.get("type").getAsJsonObject());
        BlockProperty property = new BlockProperty();
        if(json.has("hardness")) property.setBlockHardness(json.get("hardness").getAsFloat());
        if(json.has("resistance"))property.setBlockResistance(json.get("resistance").getAsFloat());
        if(json.has("lightValue")) property.setLightValue(json.get("lightValue").getAsInt());
        if(json.has("blockParticleGravity")) property.setBlockParticleGravity(json.get("blockParticleGravity").getAsFloat());
        if(json.has("canBurn")) property.setCanBurn(json.get("canBurn").getAsBoolean());
        if(json.has("replaceable")) property.setReplaceable(json.get("replaceable").getAsBoolean());
        if(json.has("isTranslucent"))property.setTranslucent(json.get("isTranslucent").getAsBoolean());
        if(json.has("requiresTool")) {
            property.setRequiresTool(json.get("requiresTool").getAsString());
            property.setHarvestLevel(json.get("harvestLevel").getAsInt());
        }
        if(json.has("blockMapColor")) property.setBlockMapColor(MapColor.COLORS[json.get("blockMapColor").getAsInt()]);
        if(json.has("blockSoundType") && json.get("blockSoundType").getAsInt() != -1)
            property.setBlockSoundType(BlockProperty.soundTypes.get(json.get("blockSoundType").getAsInt()));
        return new CustomBlockBase(type,property,id);
    }
}
