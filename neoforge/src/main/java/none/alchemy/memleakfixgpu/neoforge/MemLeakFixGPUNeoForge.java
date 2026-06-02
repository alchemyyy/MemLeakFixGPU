package none.alchemy.memleakfixgpu.neoforge;

import none.alchemy.memleakfixgpu.MemLeakFixGPU;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(MemLeakFixGPU.MOD_ID)
public class MemLeakFixGPUNeoForge {
    public MemLeakFixGPUNeoForge(IEventBus eventBus) {
        MemLeakFixGPU.init();
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        MemLeakFixGPU.drainCleanupQueue();
    }
}
