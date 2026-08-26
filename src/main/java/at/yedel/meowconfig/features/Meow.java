package at.yedel.meowconfig.features;



import at.yedel.meowconfig.config.MeowConfigConfig;
import at.yedel.meowconfig.utils.MeowMethod;



public class Meow {
    private static final Meow INSTANCE = new Meow();

    public static Meow getInstance() {
        return INSTANCE;
    }

    public void meow() {
        StringBuilder builder = new StringBuilder();
        if (!MeowConfigConfig.getInstance().prefix.isEmpty()) {
            builder.append(MeowConfigConfig.getInstance().prefix);
            builder.append(" ");
        }
        builder.append(MeowConfigConfig.getInstance().immediatePrefix);
        builder.append(MeowConfigConfig.getInstance().meowMessage);
        builder.append(MeowConfigConfig.getInstance().immediateSuffix);
        if (!MeowConfigConfig.getInstance().suffix.isEmpty()) {
            builder.append(" ");
            builder.append(MeowConfigConfig.getInstance().suffix);
        }
        String message = builder.toString();

        boolean meowed;
        for (int i = 0; i < MeowMethod.values().length; i ++) {
            if (MeowConfigConfig.getInstance().meowMethods[i]) {
                MeowMethod meowMethod = MeowMethod.values()[i];
                meowMethod.meow(message);
                meowed = true;
            }
        }
        if (meowed) {
            MeowTracking.getInstance().incrementMeows();
            MeowTracking.getInstance().incrementCharacters(message.length());
        }
    }
}
