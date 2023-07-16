package snaker.tq.level.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import snaker.snakerlib.internal.BooleanOp;
import snaker.snakerlib.level.world.Inhabitant;

import java.util.function.Predicate;

/**
 * Created by SnakerBone on 15/07/2023
 **/
public interface ComatoseInhabitant<T extends Entity> extends Inhabitant<T>
{
    Predicate<BooleanOp> extraSpawnConditions(EntityType<T> type, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random);
}
