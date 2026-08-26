package at.yedel.meowconfig.features;



import at.yedel.meowconfig.config.MeowConfigConfig;



public class MeowTracking {
    private static final MeowTracking INSTANCE = new MeowTracking();

    public static MeowTracking getInstance() {
        return INSTANCE;
    }

    private MeowTracking() {}

    public void incrementMeows() {
        MeowConfigConfig.getInstance().meows ++;
        MeowConfigConfig.getInstance().save();
    }

    public void incrementCharacters(int characters) {
        MeowConfigConfig.getInstance().characters += characters;
        MeowConfigConfig.getInstance().save();
    }
}
