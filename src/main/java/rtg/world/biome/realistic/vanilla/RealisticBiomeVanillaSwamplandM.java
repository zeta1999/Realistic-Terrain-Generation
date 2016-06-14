package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSwamplandM;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSwamplandM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSwamplandM;

public class RealisticBiomeVanillaSwamplandM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.swampland;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaSwamplandM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaSwamplandM(50f, 25f, 60f),
            new SurfaceVanillaSwamplandM(config, topBlock, fillerBlock)
        );
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoTree decoTrees = new DecoTree(new TreeRTGSalixMyrtilloides());
		decoTrees.logBlock = Blocks.log;
		decoTrees.logMeta = (byte)0;
		decoTrees.leavesBlock = Blocks.leaves;
		decoTrees.leavesMeta = (byte)0;
		decoTrees.strengthNoiseFactorXForLoops = true;
		decoTrees.strengthFactorForLoops = 1f;
		decoTrees.distribution.noiseDivisor = 80f;
		decoTrees.distribution.noiseFactor = 60f;
		decoTrees.distribution.noiseAddend = -15f;
		decoTrees.treeType = TreeType.RTG_TREE;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 12;
		decoTrees.maxY = 100;
		this.addDeco(decoTrees);
		
		TreeRTG ponderosaTree = new TreeRTGPinusPonderosa();
		ponderosaTree.logBlock = Blocks.log;
		ponderosaTree.logMeta = (byte)0;
		ponderosaTree.leavesBlock = Blocks.leaves;
		ponderosaTree.leavesMeta = (byte)0;
		ponderosaTree.minTrunkSize = 3;
		ponderosaTree.maxTrunkSize = 6;
		ponderosaTree.minCrownSize = 6;
		ponderosaTree.maxCrownSize = 14;
		ponderosaTree.noLeaves = true;
		this.addTree(ponderosaTree);
		
		DecoTree deadPineTree = new DecoTree(ponderosaTree);
		deadPineTree.treeType = TreeType.RTG_TREE;
		deadPineTree.treeCondition = TreeCondition.RANDOM_CHANCE;
		deadPineTree.treeConditionChance = 18;
		deadPineTree.maxY = 100;
		this.addDeco(deadPineTree);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
		
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 6;
		decoFallenTree.logBlock = Blocks.log2;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves2;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaSwamplandM.decorationLogsId));
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 50f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
