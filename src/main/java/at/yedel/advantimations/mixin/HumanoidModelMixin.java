package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin {
    @Unique
    private HumanoidModel.ArmPose advantimations$cancelItemUseAnimations(HumanoidRenderState state, HumanoidArm arm, HumanoidModel.ArmPose original) {
        ItemStackRenderState itemState = arm == HumanoidArm.LEFT ? /*? >= 1.21.11 {*/state.leftHandItemState/*?} else {*//*state.leftHandItem*//*?}*/ : /*? >= 1.21.11 {*/state.rightHandItemState/*?} else {*//*state.rightHandItem*//*?}*/;
        HumanoidModel.ArmPose defaultPose = !itemState.isEmpty() ? HumanoidModel.ArmPose.ITEM : HumanoidModel.ArmPose.EMPTY;
        return switch (original) {
            case BLOCK -> AdvantimationsConfig.getInstance().cancelBlockingAnimation.getThirdPersonResult(state, original, defaultPose);
            case BOW_AND_ARROW -> AdvantimationsConfig.getInstance().cancelBowAnimation.getThirdPersonResult(state, original, defaultPose);
            case /*? spear {*/ THROW_TRIDENT /*?}else {*//*THROW_SPEAR*//*?}*/ -> AdvantimationsConfig.getInstance().cancelTridentSpearAnimation.getThirdPersonResult(state, original, defaultPose);
            case CROSSBOW_CHARGE -> AdvantimationsConfig.getInstance().cancelCrossbowAnimation.getThirdPersonResult(state, original, defaultPose);
            case CROSSBOW_HOLD -> AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation.getThirdPersonResult(state, original, defaultPose);
            case SPYGLASS -> AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getThirdPersonResult(state, original, defaultPose);
            case TOOT_HORN -> AdvantimationsConfig.getInstance().cancelHornTootAnimation.getThirdPersonResult(state, original, defaultPose);
            case BRUSH -> AdvantimationsConfig.getInstance().cancelBrushingAnimation.getThirdPersonResult(state, original, defaultPose);
            /*? if spear {*/
            case SPEAR -> AdvantimationsConfig.getInstance().cancelSpearAnimation.getThirdPersonResult(state, original, defaultPose);
            /*?}*/
            default -> original;
        };
    }

    @ModifyReceiver(method = "poseLeftArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel$ArmPose;ordinal()I"))
    private HumanoidModel.ArmPose advantimations$cancelLeftArmItemUseAnimations(HumanoidModel.ArmPose original, @Local(argsOnly = true) HumanoidRenderState state) {
        return advantimations$cancelItemUseAnimations(state, HumanoidArm.LEFT, original);
    }

    @ModifyReceiver(method = "poseRightArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel$ArmPose;ordinal()I"))
    private HumanoidModel.ArmPose advantimations$cancelRightArmItemUseAnimations(HumanoidModel.ArmPose original, @Local(argsOnly = true) HumanoidRenderState state) {
        return advantimations$cancelItemUseAnimations(state, HumanoidArm.RIGHT, original);
    }
}
