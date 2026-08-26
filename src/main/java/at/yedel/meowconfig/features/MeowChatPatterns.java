package at.yedel.meowconfig.features;



import at.yedel.meowconfig.MeowConfig;
import at.yedel.meowconfig.config.MeowConfigConfig;
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.polyfrost.oneconfig.api.event.v1.events.ChatEvent;



public class MeowChatPatterns {
    private static final MeowChatPatterns INSTANCE = new MeowChatPatterns();

    public static MeowChatPatterns getInstance() {
        return INSTANCE;
    }

    private MeowChatPatterns() {
        EventManager.register(ChatEvent.Receive.class, (event) -> {
            if (MeowConfigConfig.getInstance().enabled && MeowConfigConfig.getInstance().meowOnChatPatterns) {
                String message = event.getFullyUnformattedMessage();
                for (String chatPattern: MeowConfigConfig.getInstance().chatPatterns) {
                    if (message.matches(chatPattern)) {
                        Meow.getInstance().meow();
                        return;
                    }
                }
            }
        });
    }
}
