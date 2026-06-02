package none.alchemy.gpumemleakfix.neoforge;

import none.alchemy.gpumemleakfix.GpuMemLeakFix;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(GpuMemLeakFix.MOD_ID)
public class GpuMemLeakFixNeoForge {
    public GpuMemLeakFixNeoForge(IEventBus eventBus) {
        GpuMemLeakFix.init();
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        GpuMemLeakFix.drainCleanupQueue();
    }
}
