package at.yedel.meowconfig.config;



import at.yedel.meowconfig.features.Meow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.*;
import org.polyfrost.oneconfig.api.config.v1.annotations.Number;



public class MeowConfigConfig extends Config {
    private static final MeowConfigConfig INSTANCE = new MeowConfigConfig();

    public static MeowConfigConfig getInstance() {
        return INSTANCE;
    }

    private MeowConfigConfig() {
        super("meowconfig", "assets/meowconfig/meowconfig.png", "MeowConfig", Category.QOL);
    }

    @Switch(
        title = "Enabled",
        description = "Global toggle for the mod."
    )
    public boolean enabled = true;

    @Button(
        title = "Meow Button",
        description = "Meow",
        category = "Triggers",
        text = "Meow"
    )
    public void meowButton() {
        Meow.getInstance().meow();
    }

    @Switch(
        title = "Meow on Interval",
        description = "Meow on an interval.",
        category = "Triggers"
    )
    public boolean meowOnInterval = false;

    @Number(
        title = "Meow Interval",
        description = "The interval between meows, in minutes.",
        category = "Triggers",
        unit = "m",
        min = 1,
        // year
        max = 525600
    )
    public int meowInterval = 60;

    @Switch(
        title = "Meow on Chat Patterns",
        description = "Meow in response to chat patterns.",
        category = "Triggers"
    )
    public boolean meowOnChatPatterns = false;

    @TextList(
        title = "Chat Patterns",
        description = "The chat patterns to meow to.",
        category = "Triggers"
    )
    public String[] chatPatterns = {"From [MVP+] Yedel: meow"};

    @MultiSelectDropdown(
        title = "Meow Methods",
        description = "How the meow should present itself.",
        category = "Customization",
        options = {"Send Chat Message", "Receive Chat Message", "Receive Notification"}
    )
    public boolean[] meowMethods = new boolean[] {false, true, true};

    @Text(
        title = "Send Prefix",
        description = "For the \"Send Chat Message\" method, this will add the following text to the start of the message.",
        category = "Customization"
    )
    public String sendPrefix = "";

    @Text(
        title = "Prefix",
        description = "A prefix, separated from the meow by a space.",
        category = "Customization"
    )
    public String prefix = "";

    @Text(
        title = "Immediate Prefix",
        description = "An immediate prefix, attached to the meow.",
        category = "Customization"
    )
    public String immediatePrefix = "";

    @Text(
        title = "Meow Message",
        description = "Meow",
        category = "Customization"
    )
    public String meowMessage = "meow";

    @Text(
        title = "Immediate Suffix",
        description = "An immediate suffix, attached to the meow.",
        category = "Customization"
    )
    public String immediateSuffix = "";

    @Text(
        title = "Suffix",
        description = "A suffix, separated from the meow by a space.",
        category = "Customization"
    )
    public String suffix = "";

    @Text(
        title = "Send Suffix",
        description = "For the \"Send Chat Message\" method, this will add the following text to the end of the message.",
        category = "Customization"
    )
    public String sendSuffix = "";
}
