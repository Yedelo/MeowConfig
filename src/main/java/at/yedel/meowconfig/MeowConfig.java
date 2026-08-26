package at.yedel.meowconfig;



/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
/*?} else if neoforge {*/
/*import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
    *//*?}*/



// Maud
/*? if neoforge */ //@Mod("meowconfig")
public class MeowConfig /*? if fabric {*/implements ClientModInitializer/*?}*/ {
	private void initialize() {

	}

	/*? if fabric {*/
	@Override
	public void onInitializeClient() {
		initialize();
	}
	/*?} elif neoforge {*/
	/*public MeowConfig(ModContainer container) {
		initialize();
	}
	*//*?}*/
}