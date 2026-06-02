package none.alchemy.gpumemleakfix.mixin;

import none.alchemy.gpumemleakfix.GpuMemLeakFix;
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
            GpuMemLeakFix.enqueueRenderTargetIds(this.depthBufferId, this.colorTextureId, this.frameBufferId);
        } catch (Throwable throwable) {
            GpuMemLeakFix.LOGGER.error("Error during render target finalize", throwable);
        } finally {
            super.finalize();
        }
    }
}
