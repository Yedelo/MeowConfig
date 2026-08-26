package at.yedel.meowconfig.features;



import net.minecraft.client.Minecraft;
import org.polyfrost.oneconfig.api.notifications.v1.Notifications;
import org.polyfrost.oneconfig.api.platform.v1.Platform;



public class MeowPlatform {
    private static final MeowPlatform INSTANCE = new MeowPlatform();

    public static MeowPlatform getInstance() {
        return INSTANCE;
    }

    public void sendChatMessage(String message) {
        Minecraft.getInstance().player.connection.sendChat(message);
    }

    public void receiveChatMessage(String message) {
        Platform.compatibility().displayChatMessage(message);
    }

    public void receiveNotification(String message) {
        Notifications.info("MeowConfig", message);
    }
}
