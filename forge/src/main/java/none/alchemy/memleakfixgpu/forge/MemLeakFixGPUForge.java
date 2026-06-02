package none.alchemy.memleakfixgpu.forge;

import none.alchemy.memleakfixgpu.MemLeakFixGPU;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(MemLeakFixGPU.MOD_ID)
public class MemLeakFixGPUForge {
    public MemLeakFixGPUForge() {
        MemLeakFixGPU.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            MemLeakFixGPU.drainCleanupQueue();
        }
    }
}
