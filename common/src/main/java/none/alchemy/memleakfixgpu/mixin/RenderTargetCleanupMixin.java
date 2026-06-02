package none.alchemy.memleakfixgpu.mixin;

import none.alchemy.memleakfixgpu.MemLeakFixGPU;
import com.mojang.blaze3d.pipeline.RenderTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = RenderTarget.class, remap = false)
public abstract class RenderTargetCleanupMixin {
    @Shadow(remap = true)
    protected int depthBufferId;

    @Shadow(remap = true)
    protected int colorTextureId;

    @Shadow(remap = true)
    public int frameBufferId;

    @Override
    protected void finalize() throws Throwable {
        try {
            MemLeakFixGPU.enqueueRenderTargetIds(this.depthBufferId, this.colorTextureId, this.frameBufferId);
        } catch (Throwable throwable) {
            MemLeakFixGPU.LOGGER.error("Error during render target finalize", throwable);
        } finally {
            super.finalize();
        }
    }
}
