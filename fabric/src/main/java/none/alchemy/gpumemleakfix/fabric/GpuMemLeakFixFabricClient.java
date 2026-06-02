package none.alchemy.gpumemleakfix.fabric;

import none.alchemy.gpumemleakfix.GpuMemLeakFix;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class GpuMemLeakFixFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GpuMemLeakFix.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> GpuMemLeakFix.drainCleanupQueue());
    }
}
