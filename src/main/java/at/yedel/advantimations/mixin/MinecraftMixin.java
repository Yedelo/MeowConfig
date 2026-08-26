package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @WrapWithCondition(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;itemUsed(Lnet/minecraft/world/InteractionHand;)V", ordinal = 0))
    private boolean advantimations$cancelBlockInteractResets(ItemInHandRenderer instance, InteractionHand hand) {
        return !AdvantimationsConfig.getInstance().cancelBlockInteractResets.shouldApplyInFirstPerson();
    }

    @WrapWithCondition(method = "startUseItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;itemUsed(Lnet/minecraft/world/InteractionHand;)V", ordinal = 1))
    private boolean advantimations$cancelItemInteractResets(ItemInHandRenderer instance, InteractionHand hand) {
        return !AdvantimationsConfig.getInstance().cancelItemInteractResets.shouldApplyInFirstPerson();
    }
}
