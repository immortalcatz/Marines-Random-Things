package com.thefuturemarine.ArmorModPlus.block;

import com.thefuturemarine.ArmorModPlus.client.ArmorMod;
import com.thefuturemarine.ArmorModPlus.init.ArmorModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNetherLapisOre extends Block{

        public BlockNetherLapisOre(String name) {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(SoundType.STONE);
        this.setHardness(3.0F);
        this.setResistance(15.0F);
        this.setCreativeTab(ArmorMod.tabBlocks);

        setUnlocalizedName(name);
        setRegistryName(name);
    }
    private void setStepSound(SoundType stone) {

    }
    public int quantityDropped(Random random) {

        return this == ArmorModBlocks.netherrack_lapis_ore ? 4 + random.nextInt(5) : 1;
    }

    public Item getItemDropped(IBlockState state, Random random, int fortune){

        return this == ArmorModBlocks.netherrack_lapis_ore ? Items.DYE: Item.getItemFromBlock(this);
    }

    public int quantityDroppedWithBonus(int fortune, Random random){

        if(fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)){

            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0){
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }else{

            return this.quantityDropped(random);
        }
    }

    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune){

        super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
    }

    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune){

        IBlockState state = world.getBlockState(pos);
        Random random = world instanceof World ? ((World)world).rand : new Random();

        if (this.getItemDropped(state, random, fortune) != Item.getItemFromBlock(this)){

            int i = 0;

            if (this == ArmorModBlocks.netherrack_lapis_ore)
            {
                i = MathHelper.getRandomIntegerInRange(random, 2, 5);
            }

            return i;
        }
        return 0;
    }

    public int getDamageValue(World world, BlockPos pos){

        return 0;
    }
    public int damageDropped(IBlockState state)
    {
        return this == ArmorModBlocks.netherrack_lapis_ore ? EnumDyeColor.BLUE.getDyeDamage() : 0;
    }
}
