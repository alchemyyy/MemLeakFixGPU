# GPU Memory Leak Fix

Cleans up stale RenderTarget GPU resources.

## What it does

- Queues stale texture and framebuffer ids from finalized render targets.
- Deletes those ids from the client thread on later ticks.
- Limits each cleanup pass so it does not do too much work at once.

## Build

```powershell
.\gradlew.bat build
```

Jars are in `fabric/build/libs`, `forge/build/libs`, and `neoforge/build/libs`.

Discord: https://discord.gg/3TCfgHx7gv

## Credits

- Alchemyyy
