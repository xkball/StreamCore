package com.xkball.stream_core.doc;

import com.google.gson.JsonObject;
import com.xkball.stream_core.StreamCore;
import com.xkball.stream_core.common.block.BlockProperty;
import com.xkball.stream_core.common.block.BlockType;
import com.xkball.stream_core.common.block.CustomBlockBase;
import com.xkball.stream_core.config.loader.CustomBlockBaseLoader;
import com.xkball.stream_core.utils.JsonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Mod.EventBusSubscriber(modid = StreamCore.MODID)
public class SCDocGenerator {
    
    public static File docDir = new File(StreamCore.streamCoreDir.getAbsoluteFile(),"doc");
    
    @Doc(loader = CustomBlockBaseLoader.class,
            path = "autoReg")
    @SuppressWarnings("unused")
    public static CustomBlockBase exampleBlock = new CustomBlockBase(BlockType.Default,new BlockProperty()
                    .setBlockHardness(5)
                    .setBlockResistance(200)
                    .setLightValue(10)
                    .setTranslucent(true)
                    .setCanBurn(false)
                    .setBlockMapColor(MapColor.BROWN)
                    .setBlockSoundType(SoundType.ANVIL)
                    .setBlockParticleGravity(1f)
                    .setCreativeTabs(CreativeTabs.FOOD)
                    .setHarvestLevel(5)
                    .setRequiresTool("axe")
                    .setReplaceable(false),
                    "example");
    
    public static void genDoc(ASMDataTable dataTable) throws IOException {
        for(ASMDataTable.ASMData asmData : dataTable.getAll(Doc.class.getName())){
            DocData data = new DocData(asmData);
            File file = data.getFile();
            if(!file.getParentFile().exists()){
                if(!file.getParentFile().mkdirs()){
                    return;
                }
            }
            if(!file.exists()){
                if(!file.createNewFile()){
                    return;
                }
            }
            try (OutputStream out = Files.newOutputStream(file.toPath())){
                JsonObject json = data.getLoader().write(data.getFieldValue());
                out.write(JsonUtils.jsonToString(json).getBytes());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
    
        }
    }

}
