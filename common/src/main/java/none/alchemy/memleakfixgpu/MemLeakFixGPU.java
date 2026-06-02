package none.alchemy.memleakfixgpu;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.core.Vec3i;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MemLeakFixGPU {
    public static final String MOD_ID = "memleakfixgpu";
    public static final String MOD_NAME = "MemLeakFixGPU";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    private static final ConcurrentLinkedQueue<Vec3i> QUEUE = new ConcurrentLinkedQueue<>();
    private static final int GL_FRAMEBUFFER = 36160;

    private MemLeakFixGPU() {
    }

    public static void init() {
        LOGGER.info("Loaded {}", MOD_NAME);
    }

    public static void enqueueRenderTargetIds(int depthBufferId, int colorTextureId, int frameBufferId) {
        if (depthBufferId > -1 || colorTextureId > -1 || frameBufferId > -1) {
            QUEUE.add(new Vec3i(depthBufferId, colorTextureId, frameBufferId));
        }
    }

    public static void drainCleanupQueue() {
        int counter = 0;
        while (!QUEUE.isEmpty() && counter++ < 20) {
            Vec3i ids = QUEUE.poll();
            if (ids == null) {
                continue;
            }

            GlStateManager._bindTexture(0);
            GlStateManager._glBindFramebuffer(GL_FRAMEBUFFER, 0);
            if (ids.getX() > -1) {
                TextureUtil.releaseTextureId(ids.getX());
            }
            if (ids.getY() > -1) {
                TextureUtil.releaseTextureId(ids.getY());
            }
            if (ids.getZ() > -1) {
                GlStateManager._glBindFramebuffer(GL_FRAMEBUFFER, 0);
                GlStateManager._glDeleteFramebuffers(ids.getZ());
            }
        }
    }
}
