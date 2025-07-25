package com.coolerpromc.custombiomes;

import com.coolerpromc.custombiomes.core.BiomeRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(CustomBiomes.MOD_ID)
public class CustomBiomes {
    public static final String MOD_ID = "custombiomes";

    public CustomBiomes(IEventBus modEventBus, ModContainer modContainer) {
        BiomeRegister.init();
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
}
