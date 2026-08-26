package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @ModifyExpressionValue(method = /*? >=26.2 {*/"submitHandsWithItems" /*?} else {*//*"renderHandsWithItems"*//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getAttackAnim(F)F"))
    private float advantimations$cancelFirstPersonSwings(float original) {
        return AdvantimationsConfig.getInstance().cancelSwings.getScaledFirstPersonResult(original);
    }

    @ModifyExpressionValue(method = /*? >=26.2 {*/"submitArmWithItem" /*?} else {*//*"renderArmWithItem"*//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;"))
    private ItemUseAnimation advantimations$cancelUseAnimations(ItemUseAnimation original) {
        if (switch (original) {
            case EAT -> AdvantimationsConfig.getInstance().cancelEatingAnimation.shouldApplyInFirstPerson();
            case DRINK -> AdvantimationsConfig.getInstance().cancelDrinkingAnimation.shouldApplyInFirstPerson();
            case BLOCK -> AdvantimationsConfig.getInstance().cancelBlockingAnimation.shouldApplyInFirstPerson();
            case BOW -> AdvantimationsConfig.getInstance().cancelBowAnimation.shouldApplyInFirstPerson();
            case TOOT_HORN -> AdvantimationsConfig.getInstance().cancelHornTootAnimation.shouldApplyInFirstPerson();
            case /*? if spear {*/ TRIDENT /*?} else {*//*SPEAR*//*?}*/ -> AdvantimationsConfig.getInstance().cancelTridentSpearAnimation.shouldApplyInFirstPerson();
            case BRUSH -> AdvantimationsConfig.getInstance().cancelBrushingAnimation.shouldApplyInFirstPerson();
            case BUNDLE -> AdvantimationsConfig.getInstance().cancelBundleAnimation.shouldApplyInFirstPerson();
            /*? if spear {*/
            case SPEAR -> AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInFirstPerson();
            /*?}*/
            default -> false;
        }) {
            return ItemUseAnimation.NONE;
        }
        return original;
    }

    @ModifyExpressionValue(method = /*? >=26.2 {*/"submitArmWithItem" /*?} else {*//*"renderArmWithItem"*//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isUsingItem()Z", ordinal = 0))
    private boolean advantimations$cancelCrossbowAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelCrossbowAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = /*? >=26.2 {*/"submitArmWithItem" /*?} else {*//*"renderArmWithItem"*//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;isCharged(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean advantimations$cancelChargedCrossbowAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = /*? >=26.2 {*/"submitArmWithItem" /*?} else {*//*"renderArmWithItem"*//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isScoping()Z"))
    private boolean advantimations$cancelSpyglassAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = /*? >=26.2 {*/"submitArmWithItem" /*?} else {*//*"renderArmWithItem"*//*?}*/, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isAutoSpinAttack()Z"))
    private boolean advantimations$cancelRiptideAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelRiptideAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;" + /*? >=1.21.11 {*/"getItemSwapScale(F)F"/*?} else {*//*"getAttackStrengthScale(F)F"*//*?}*/))
    private float advantimations$cancelAttackCooldownResets(float original) {
        return AdvantimationsConfig.getInstance().cancelAttackCooldownResets.getFirstPersonResult(original, 1F);
    }

    @Inject(method = "shouldInstantlyReplaceVisibleItem", at = @At("HEAD"), cancellable = true)
    private void advantimations$cancelSlotSwappingResets(ItemStack from, ItemStack to, CallbackInfoReturnable<Boolean> cir) {
        if (AdvantimationsConfig.getInstance().cancelSlotSwappingResets.shouldApplyInFirstPerson()) {
            cir.setReturnValue(true);
        }
    }
}
