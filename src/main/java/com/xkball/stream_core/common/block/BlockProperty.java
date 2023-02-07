package com.xkball.stream_core.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

@SuppressWarnings("unused")
//记录不包括方块状态的属性
public class BlockProperty {
    
    private CreativeTabs displayOnCreativeTab;
    //private boolean fullBlock;
    //private int lightOpacity;
    private boolean translucent;
    private int lightValue;
    private boolean useNeighborBrightness;
    private float blockHardness;
    private float blockResistance;
    private boolean enableStats;
    private boolean needsRandomTick;
    private boolean hasTileEntity;
    private SoundType blockSoundType;
    private float blockParticleGravity;
    private Material blockMaterial;
    private MapColor blockMapColor;
    
    public BlockProperty(){}
    
    public BlockProperty build(){
        if(displayOnCreativeTab == null) displayOnCreativeTab = CreativeTabs.BUILDING_BLOCKS;
        if(blockHardness == 0) blockHardness = 5f;
        
        
        return this;
    }
    
    public BlockProperty setCreativeTabs(CreativeTabs tabs){
        this.displayOnCreativeTab = tabs;
        return this;
    }
    
    public BlockProperty  setTranslucent(boolean translucent) {
        this.translucent = translucent;
        return this;
    }
    
    public BlockProperty  setLightValue(int lightValue) {
        this.lightValue = lightValue;
        return this;
    }
    
    public BlockProperty  setUseNeighborBrightness(boolean useNeighborBrightness) {
        this.useNeighborBrightness = useNeighborBrightness;
        return this;
    }
    
    public BlockProperty  setBlockHardness(float blockHardness) {
        this.blockHardness = blockHardness;
        return this;
    }
    
    public BlockProperty  setBlockResistance(float blockResistance) {
        this.blockResistance = blockResistance;
        return this;
    }
    
    public BlockProperty  setEnableStats(boolean enableStats) {
        this.enableStats = enableStats;
        return this;
    }
    
    public BlockProperty  setNeedsRandomTick(boolean needsRandomTick) {
        this.needsRandomTick = needsRandomTick;
        return this;
    }
    
    public BlockProperty  setHasTileEntity(boolean hasTileEntity) {
        this.hasTileEntity = hasTileEntity;
        return this;
    }
    
    public BlockProperty  setBlockSoundType(SoundType blockSoundType) {
        this.blockSoundType = blockSoundType;
        return this;
    }
    
    public BlockProperty  setBlockParticleGravity(float blockParticleGravity) {
        this.blockParticleGravity = blockParticleGravity;
        return this;
    }
    
    public BlockProperty  setBlockMaterial(Material blockMaterial) {
        this.blockMaterial = blockMaterial;
        return this;
    }
    
    public BlockProperty  setBlockMapColor(MapColor blockMapColor) {
        this.blockMapColor = blockMapColor;
        return this;
    }
    
    public CreativeTabs getDisplayOnCreativeTab() {
        return displayOnCreativeTab;
    }
    
    public boolean isTranslucent() {
        return translucent;
    }
    
    public int getLightValue() {
        return lightValue;
    }
    
    public boolean isUseNeighborBrightness() {
        return useNeighborBrightness;
    }
    
    public float getBlockHardness() {
        return blockHardness;
    }
    
    public float getBlockResistance() {
        return blockResistance;
    }
    
    public boolean isEnableStats() {
        return enableStats;
    }
    
    public boolean isNeedsRandomTick() {
        return needsRandomTick;
    }
    
    public boolean isHasTileEntity() {
        return hasTileEntity;
    }
    
    public SoundType getBlockSoundType() {
        return blockSoundType;
    }
    
    public float getBlockParticleGravity() {
        return blockParticleGravity;
    }
    
    public Material getBlockMaterial() {
        return blockMaterial;
    }
    
    public MapColor getBlockMapColor() {
        return blockMapColor;
    }
    
}
