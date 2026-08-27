package at.yedel.meowconfig.utils;



import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import org.polyfrost.oneconfig.api.notifications.v1.Notifications;
import org.polyfrost.oneconfig.api.platform.v1.Platform;



public class MeowPlatform {
    private static final MeowPlatform INSTANCE = new MeowPlatform();

    public static MeowPlatform getInstance() {
        return INSTANCE;
    }

    public static final Component LOGO = MiniMessage.miniMessage().deserialize("<gradient:#f05be1:#ffb3f7>[MeowConfig]");


    public void sendChatMessage(String message) {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.connection.sendChat(message);
        }
    }

    public void receiveChatMessage(String message) {
        Platform.compatibility().displayChatMessage(message);
    }

    public void receiveChatMessageWithLogo(String message) {
        Platform.compatibility().displayChatMessage(LOGO.append(Component.space()).append(Component.text(message)));
    }

    public void receiveNotification(String message) {
        Notifications.info("MeowConfig", message);
    }

    public void playSound(String sound, float volume, float pitch) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            SoundEvent event = BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse(sound));
            if (event == null) return;
            level.playSound(player, player.xo, player.yo, player.zo, event, SoundSource.UI, volume, pitch);
        }
    }

    public void schedule(Runnable runnable) {
        Minecraft.getInstance().schedule(runnable);
    }
}
