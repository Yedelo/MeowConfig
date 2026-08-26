package at.yedel.meowconfig;



import at.yedel.meowconfig.config.MeowConfigConfig;
import at.yedel.meowconfig.triggers.MeowChatPatterns;
import at.yedel.meowconfig.command.MeowConfigCommand;
import at.yedel.meowconfig.triggers.MeowInterval;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.network.chat.Component;
import org.polyfrost.oneconfig.api.commands.v1.CommandManager;
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.polyfrost.oneconfig.api.event.v1.events.WorldEvent;



// Meow
public class MeowConfig implements ClientModInitializer {
	private static MeowConfig INSTANCE;

	public static MeowConfig getInstance() {
		return INSTANCE;
	}

	//@TODO make this fancy later
	public static final Component LOGO = Component.literal("[MeowConfig]");

	private void initialize() {
		INSTANCE = this;
		MeowConfigConfig.getInstance();
		MeowChatPatterns.getInstance();
		CommandManager.register(MeowConfigCommand.getInstance());
		EventManager.register(WorldEvent.Load.class, (event) -> {
			MeowInterval.getInstance();
		});
	}

	@Override
	public void onInitializeClient() {
		initialize();
	}
}