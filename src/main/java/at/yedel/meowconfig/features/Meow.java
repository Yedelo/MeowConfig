package at.yedel.meowconfig.features;



import at.yedel.meowconfig.config.MeowConfigConfig;
import at.yedel.meowconfig.utils.MeowMethod;
import at.yedel.meowconfig.utils.MeowPlatform;



public class Meow {
    private static final Meow INSTANCE = new Meow();

    public static Meow getInstance() {
        return INSTANCE;
    }

    public void meow() {
        String message = getFullMessage();
        boolean meowed = false;
        for (int i = 0; i < MeowMethod.values().length; i ++) {
            if (MeowConfigConfig.getInstance().meowMethods[i]) {
                MeowMethod meowMethod = MeowMethod.values()[i];
                meowMethod.meow(message);
                meowed = true;
            }
        }
        if (meowed) {
            if (MeowConfigConfig.getInstance().playSoundOnMeow) {
                String[] meowSounds = MeowConfigConfig.getInstance().meowSounds;
                if (meowSounds.length > 0) {
                    String meowSound = meowSounds[(int) (Math.random() * meowSounds.length)];
                    MeowPlatform.getInstance().playSound(meowSound, MeowConfigConfig.getInstance().meowVolume, MeowConfigConfig.getInstance().meowPitch);
                }
            }
            MeowTracking.getInstance().incrementMeows();
            MeowTracking.getInstance().incrementCharacters(message.length());
        }
    }

    private static String getFullMessage() {
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
        return builder.toString();
    }
}
