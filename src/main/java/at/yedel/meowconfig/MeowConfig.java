package at.yedel.meowconfig;



import at.yedel.meowconfig.config.MeowConfigConfig;
import at.yedel.meowconfig.features.MeowChatPatterns;
import at.yedel.meowconfig.features.MeowConfigCommand;
import net.fabricmc.api.ClientModInitializer;
import org.polyfrost.oneconfig.api.commands.v1.CommandManager;



// Meow
public class MeowConfig implements ClientModInitializer {
	private static MeowConfig INSTANCE;

	public static MeowConfig getInstance() {
		return INSTANCE;
	}

	private void initialize() {
		INSTANCE = this;
		MeowConfigConfig.getInstance();
		MeowChatPatterns.getInstance();
		CommandManager.register(MeowConfigCommand.getInstance());
	}

	@Override
	public void onInitializeClient() {
		initialize();
	}
}