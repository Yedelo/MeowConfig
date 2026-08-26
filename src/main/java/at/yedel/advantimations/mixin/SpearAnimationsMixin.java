/*? if spear {*/
package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.effects.SpearAnimations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(SpearAnimations.class)
public abstract class SpearAnimationsMixin {
    @Inject(method = "thirdPersonAttackItem", at = @At("HEAD"), cancellable = true)
    private static void advantimations$cancelSpearAnimation(ArmedEntityRenderState state, PoseStack poseStack, CallbackInfo ci) {
        if (AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInThirdPerson(state)) ci.cancel();
    }

    @Inject(method = "thirdPersonHandUse", at = @At("HEAD"), cancellable = true)
    private static void advantimations$cancelSpearAnimation(ModelPart modelPart, ModelPart modelPart2, boolean bl, ItemStack itemStack, HumanoidRenderState state, CallbackInfo ci) {
        if (AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInThirdPerson(state)) ci.cancel();
    }

    @Inject(method = "thirdPersonAttackHand", at = @At("HEAD"), cancellable = true)
    private static void advantimations$cancelSpearAnimation(HumanoidModel humanoidModel, HumanoidRenderState state, CallbackInfo ci) {
        if (AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInThirdPerson(state)) ci.cancel();
    }

    @Inject(method = "thirdPersonUseItem", at = @At("HEAD"), cancellable = true)
    private static void advantimations$cancelSpearAnimation(ArmedEntityRenderState state, PoseStack poseStack, float f, HumanoidArm humanoidArm, ItemStack itemStack, CallbackInfo ci) {
        if (AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInThirdPerson(state)) ci.cancel();
    }

    @Inject(method = "firstPersonUse", at = @At("HEAD"), cancellable = true)
    private static void advantimations$cancelSpearAnimation(float f, PoseStack poseStack, float g, HumanoidArm humanoidArm, ItemStack itemStack, CallbackInfo ci) {
        if (AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInFirstPerson()) ci.cancel();
    }

    @Inject(method = "firstPersonAttack", at = @At("HEAD"), cancellable = true)
    private static void advantimations$cancelSpearAnimation(float f, PoseStack poseStack, int i, HumanoidArm humanoidArm, CallbackInfo ci) {
        if (AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInFirstPerson()) ci.cancel();
    }
}
/*?}*/