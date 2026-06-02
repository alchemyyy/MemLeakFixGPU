package none.alchemy.memleakfixgpu.fabric;

import none.alchemy.memleakfixgpu.MemLeakFixGPU;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class MemLeakFixGPUFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MemLeakFixGPU.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> MemLeakFixGPU.drainCleanupQueue());
    }
}
