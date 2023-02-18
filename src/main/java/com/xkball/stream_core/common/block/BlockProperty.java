package com.xkball.stream_core.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

@SuppressWarnings("unused")
//记录不包括方块状态的属性
public class BlockProperty {
    
    private static final Class<Material> material = Material.class;
    private static final Class<SoundType> soundType = SoundType.class;
    private static final Method setTranslucent;
    private static final Method setRequiresTool;
    private static final Method setBurning;
    
    public static final ArrayList<SoundType> soundTypes = new ArrayList<>();
    
    static {
        try {
            setTranslucent = material.getDeclaredMethod("setTranslucent");
            setRequiresTool = material.getDeclaredMethod("setRequiresTool");
            setBurning = material.getDeclaredMethod("setBurning");
            setTranslucent.setAccessible(true);
            setRequiresTool.setAccessible(true);
            setBurning.setAccessible(true);
            
            for(Field o : soundType.getFields()){
                if(Modifier.isStatic(o.getModifiers()) && o.get(null) instanceof SoundType){
                    soundTypes.add((SoundType) o.get(null));
                }
            }
           
            
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    private CreativeTabs displayOnCreativeTab;
    //private boolean fullBlock;
    //private int lightOpacity;
    //private boolean translucent;
    private int lightValue;
    //private boolean useNeighborBrightness;
    private float blockHardness;
    private float blockResistance;
    //private boolean enableStats;
    //private boolean needsRandomTick;
    //private boolean hasTileEntity;
    private SoundType blockSoundType;
    private float blockParticleGravity;
    private Material blockMaterial;
    
    //特殊
    private MapColor blockMapColor;
    private boolean canBurn;
    
    private boolean replaceable;
    
    private boolean isTranslucent;
    private String requiresTool;
    private int harvestLevel = 0;
    
    public BlockProperty(){}
    
    public void build() throws InvocationTargetException, IllegalAccessException {
        if(blockMapColor == null) blockMapColor = MapColor.STONE;
        if(blockMaterial == null) blockMaterial = new Material(blockMapColor);
        if(replaceable) blockMaterial.setReplaceable();
        if(canBurn) setBurning.invoke(blockMaterial);
        if(requiresTool != null) {
            setRequiresTool.invoke(blockMaterial);
        }
        if(isTranslucent) setTranslucent.invoke(blockMaterial);
        
        if(blockSoundType == null) blockSoundType = SoundType.STONE;
        if(displayOnCreativeTab == null) displayOnCreativeTab = CreativeTabs.BUILDING_BLOCKS;
        if(blockHardness == 0) blockHardness = 5f;
    
    
    }
    
    public BlockProperty setCreativeTabs(CreativeTabs tabs){
        this.displayOnCreativeTab = tabs;
        return this;
    }
    
    public BlockProperty  setLightValue(int lightValue) {
        this.lightValue = lightValue;
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
    
    public int getLightValue() {
        return lightValue;
    }
    
    public float getBlockHardness() {
        return blockHardness;
    }
    
    public float getBlockResistance() {
        return blockResistance;
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
    
    public void setDisplayOnCreativeTab(CreativeTabs displayOnCreativeTab) {
        this.displayOnCreativeTab = displayOnCreativeTab;
    }
    
    public boolean isCanBurn() {
        return canBurn;
    }
    
    public void setCanBurn(boolean canBurn) {
        this.canBurn = canBurn;
    }
    
    public boolean isReplaceable() {
        return replaceable;
    }
    
    public void setReplaceable(boolean replaceable) {
        this.replaceable = replaceable;
    }
    
    public String getRequiresTool() {
        return requiresTool;
    }
    
    public BlockProperty setRequiresTool(String requiresTool) {
        this.requiresTool = requiresTool;
        return this;
    }
    
    public BlockProperty setBlockSoundType(SoundType blockSoundType) {
        this.blockSoundType = blockSoundType;
        return this;
    }
    
    public boolean isTranslucent() {
        return isTranslucent;
    }
    
    public BlockProperty setTranslucent(boolean translucent) {
        isTranslucent = translucent;
        return this;
    }
    
    public int getHarvestLevel() {
        return harvestLevel;
    }
    
    public BlockProperty setHarvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;
        return this;
    }
    
}
