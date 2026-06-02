package none.alchemy.gpumemleakfix.forge;

import none.alchemy.gpumemleakfix.GpuMemLeakFix;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(GpuMemLeakFix.MOD_ID)
public class GpuMemLeakFixForge {
    public GpuMemLeakFixForge() {
        GpuMemLeakFix.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GpuMemLeakFix.drainCleanupQueue();
        }
    }
}
