package at.yedel.meowconfig.features;



import at.yedel.meowconfig.config.MeowConfigConfig;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class MeowInterval {
    private static final MeowInterval INSTANCE = new MeowInterval();

    public static MeowInterval getInstance() {
        return INSTANCE;
    }

    private final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    private MeowInterval() {
        schedule();
    }

    private void schedule() {
        EXECUTOR.schedule(this::run, MeowConfigConfig.getInstance().meowInterval, TimeUnit.MINUTES);
    }

    private void run() {
        if (MeowConfigConfig.getInstance().enabled && MeowConfigConfig.getInstance().meowOnInterval) {
            Meow.getInstance().meow();
        }
        schedule();
    }
}
