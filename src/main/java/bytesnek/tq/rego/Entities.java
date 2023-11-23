package bytesnek.tq.rego;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import bytesnek.tq.Tourniqueted;
import bytesnek.tq.level.entity.boss.Utterfly;
import bytesnek.tq.level.entity.creature.Flutterfly;
import bytesnek.tq.level.entity.creature.Frolicker;
import bytesnek.tq.level.entity.crystal.ComaCrystal;
import bytesnek.tq.level.entity.mob.*;
import bytesnek.tq.level.entity.projectile.CosmicRay;
import bytesnek.tq.level.entity.projectile.ExplosiveHommingArrow;
import bytesnek.tq.level.entity.projectile.HommingArrow;

/**
 * Created by SnakerBone on 15/08/2023
 **/
public class Entities
{
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Tourniqueted.MODID);

    public static final RegistryObject<EntityType<Cosmo>> COSMO = registerMob("cosmo", Cosmo::new, 1F, 2F);
    public static final RegistryObject<EntityType<Snipe>> SNIPE = registerMob("snipe", Snipe::new, 1F, 1.5F);
    public static final RegistryObject<EntityType<Flare>> FLARE = registerMob("flare", Flare::new, 1F, 2F);
    public static final RegistryObject<EntityType<CosmicCreeper>> COSMIC_CREEPER = registerMob("cosmic_creeper", CosmicCreeper::new, 1F, 2F);
    public static final RegistryObject<EntityType<CosmicCreeperite>> COSMIC_CREEPERITE = registerMob("cosmic_creeperite", CosmicCreeperite::new, 1F, 2F);
    public static final RegistryObject<EntityType<Utterfly>> UTTERFLY = registerMob("utterfly", Utterfly::new, 6F, 3F);
    public static final RegistryObject<EntityType<Frolicker>> FROLICKER = registerCreature("frolicker", Frolicker::new, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<Flutterfly>> FLUTTERFLY = registerCreature("flutterfly", Flutterfly::new, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<CrankyFlutterfly>> CRANKY_FLUTTERFLY = registerMob("cranky_flutterfly", CrankyFlutterfly::new, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<HommingArrow>> HOMMING_ARROW = registerMisc("homming_arrow", HommingArrow::new, 0.1F, 0.1F);
    public static final RegistryObject<EntityType<ExplosiveHommingArrow>> EXPLOSIVE_HOMMING_ARROW = registerMisc("explosive_homming_arrow", ExplosiveHommingArrow::new, 0.1F, 0.1F);
    public static final RegistryObject<EntityType<CosmicRay>> COSMIC_RAY = registerMisc("cosmic_ray", CosmicRay::new, 0.1F, 0.1F);
    public static final RegistryObject<EntityType<ComaCrystal>> COMA_CRYSTAL = registerMisc("coma_crystal", ComaCrystal::new, 1, 1);

    static <T extends Animal> RegistryObject<EntityType<T>> registerCreature(String name, EntityType.EntityFactory<T> entity, float width, float height)
    {
        return REGISTER.register(name, () -> EntityType.Builder.of(entity, MobCategory.AMBIENT)
                .sized(width, height)
                .build(name)
        );
    }

    static <T extends Mob> RegistryObject<EntityType<T>> registerMob(String name, EntityType.EntityFactory<T> entity, float width, float height)
    {
        return REGISTER.register(name, () -> EntityType.Builder.of(entity, MobCategory.MONSTER)
                .sized(width, height)
                .build(name)
        );
    }

    static <T extends Entity> RegistryObject<EntityType<T>> registerMisc(String name, EntityType.EntityFactory<T> entity, float width, float height)
    {
        return REGISTER.register(name, () -> EntityType.Builder.of(entity, MobCategory.MISC)
                .sized(width, height)
                .build(name)
        );
    }
}