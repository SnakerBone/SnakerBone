package xyz.snaker.tq.level.entity.mob;

import xyz.snaker.snakerlib.level.entity.Hostile;
import xyz.snaker.tq.client.renderer.entity.CosmoRenderer;
import xyz.snaker.tq.level.entity.Comatosian;
import xyz.snaker.tq.level.entity.EntityVariants;
import xyz.snaker.tq.level.levelgen.EntitySpawner;
import xyz.snaker.tq.rego.LootTables;
import xyz.snaker.tq.rego.Sounds;

import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.network.NetworkHooks;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by SnakerBone on 2/01/2023
 **/
public class Cosmo extends Hostile implements Comatosian
{
    static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Cosmo.class, EntityDataSerializers.INT);

    public Cosmo(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
    }

    public static AttributeSupplier attributes()
    {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.25).build();
    }

    public static boolean spawnRules(EntityType<Cosmo> type, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random)
    {
        return EntitySpawner.COMATOSE.check(level, pos, random, 75);
    }

    @Override
    @ApiStatus.OverrideOnly
    @SuppressWarnings({"deprecation", "OverrideOnly"})
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor accessor, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData group, @Nullable CompoundTag tag)
    {
        EntityVariants.Cosmo variant = Util.getRandom(EntityVariants.Cosmo.values(), random);
        EntityVariants.Cosmo alpha = EntityVariants.Cosmo.ALPHA;

        boolean isAlpha = variant == alpha && random.nextInt(250) == 0;

        if (isAlpha) {
            setVariant(alpha);
        }

        return super.finalizeSpawn(accessor, difficulty, reason, group, tag);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount)
    {
        Entity entity = source.getEntity();

        if (level().isClientSide || entity == null) {
            return false;
        }

        if (entity instanceof Player player) {
            ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND);

            if (player.isCreative()) {
                return super.hurt(source, amount);
            }

            if (!player.isCreative() && heldItem.is(Tags.Items.STONE)) {
                return super.hurt(source, getMaxHealth() / random.nextInt(5, 10));
            } else if (!player.isCreative() && !heldItem.is(Tags.Items.STONE)) {
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean fireImmune()
    {
        return getVariant().equals(EntityVariants.Cosmo.RED);
    }

    @Override
    public @NotNull Component getName()
    {
        Component name = Component.translatable("entity.tq.alpha_cosmo");

        return getVariant().equals(EntityVariants.Cosmo.ALPHA) ? name : super.getName();
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entity)
    {
        LivingEntity target = getTarget();

        if (target == null) {
            return false;
        }

        if (getVariant().equals(EntityVariants.Cosmo.ALPHA)) {
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 25, 1));
            target.teleportTo(target.getRandomX(15), target.getY(), target.getRandomZ(15));

            level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.FOX_TELEPORT, SoundSource.BLOCKS, 1, 1);
        }

        return super.doHurtTarget(entity);
    }

    @Override
    public @NotNull ResourceLocation getDefaultLootTable()
    {
        return switch (getVariant()) {
            case RED -> LootTables.COSMO_RED;
            case GREEN -> LootTables.COSMO_GREEN;
            case BLUE -> LootTables.COSMO_BLUE;
            case YELLOW -> LootTables.COSMO_YELLOW;
            case PINK -> LootTables.COSMO_PINK;
            case PURPLE -> LootTables.COSMO_PURPLE;
            case ALPHA -> LootTables.COSMO_ALPHA;
        };
    }

    public static RenderType getRenderType(EntityVariants.Cosmo type)
    {
        return CosmoRenderer.TYPE.get(type).get();
    }

    public EntityVariants.Cosmo getVariant()
    {
        return EntityVariants.Cosmo.byId(getTypeVariant() & 255);
    }

    public int getTypeVariant()
    {
        return entityData.get(VARIANT);
    }

    public void setVariant(EntityVariants.Cosmo variant)
    {
        entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public void defineSynchedData()
    {
        super.defineSynchedData();
        entityData.define(VARIANT, 0);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag)
    {
        super.readAdditionalSaveData(tag);
        entityData.set(VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", getTypeVariant());
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound()
    {
        return SoundEvents.SCULK_CLICKING;
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource source)
    {
        return Sounds.COSMO_HURT.get();
    }

    @Override
    public SoundEvent getDeathSound()
    {
        return Sounds.DEATH.get();
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isAdaptive()
    {
        return false;
    }
}
