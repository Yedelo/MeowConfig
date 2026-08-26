package at.yedel.meowconfig.features;



import at.yedel.meowconfig.MeowConfig;
import at.yedel.meowconfig.config.MeowConfigConfig;



public class Meow {
    private static final Meow INSTANCE = new Meow();

    public static Meow getInstance() {
        return INSTANCE;
    }

    public void meow() {
        String message =
            MeowConfigConfig.getInstance().prefix
                + " "
                + MeowConfigConfig.getInstance().immediatePrefix
                + MeowConfigConfig.getInstance().meowMessage
                + MeowConfigConfig.getInstance().immediateSuffix
                + " "
                + MeowConfigConfig.getInstance().suffix;

        for (int i = 0; i < MeowMethod.values().length; i ++) {
            if (MeowConfigConfig.getInstance().meowMethods[i]) {
                MeowMethod meowMethod = MeowMethod.values()[i];
                meowMethod.meow(message);
            }
        }
    }
}
