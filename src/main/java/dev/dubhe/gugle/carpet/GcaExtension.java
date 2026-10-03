package dev.dubhe.gugle.carpet;

import com.mojang.brigadier.CommandDispatcher;
import dev.dubhe.curtain.Curtain;
import dev.dubhe.curtain.CurtainRules;
import dev.dubhe.curtain.ICurtain;
import dev.dubhe.gugle.carpet.api.tools.text.ComponentHelper;
import dev.dubhe.gugle.carpet.commands.BlistCommand;
import dev.dubhe.gugle.carpet.commands.BotCommand;
import dev.dubhe.gugle.carpet.commands.HereCommand;
import dev.dubhe.gugle.carpet.commands.LocCommand;
import dev.dubhe.gugle.carpet.commands.SopCommand;
import dev.dubhe.gugle.carpet.commands.TodoCommand;
import dev.dubhe.gugle.carpet.commands.WhereisCommand;
import dev.dubhe.gugle.carpet.commands.WlistCommand;
import dev.dubhe.gugle.carpet.config.GcaConfig;
import dev.dubhe.gugle.carpet.entry.PlayerGameProfileCache;
import dev.dubhe.gugle.carpet.tools.WelcomeMessage;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Mod(GcaExtension.MOD_ID)
@Mod.EventBusSubscriber(modid = GcaExtension.MOD_ID)
public class GcaExtension implements ICurtain {
    public static final String MOD_ID = "gca";
    public static final String MOD_NAME = "GugleCarpetAddition";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final List<String> LANGUAGES = List.of("en_us", "zh_cn", "zh_tw");
    public static final HashMap<String, Consumer<ServerPlayer>> ON_PLAYER_LOGGED_IN = new HashMap<>();
    public static final List<Map.Entry<Long, Runnable>> PLAN_FUNCTION = new ArrayList<>();

    public GcaExtension() {
        Curtain.addSubMod(this);
        this.addRules(GcaSetting.class);
        this.loadTranslations();
        WelcomeMessage.registerDefaultReplacer();
    }

    public void loadTranslations() {
        ClassLoader loader = GcaExtension.class.getClassLoader();
        for (String lang : LANGUAGES) {
            InputStream stream = loader.getResourceAsStream("assets/%s/lang/%s.json".formatted(MOD_ID, lang));
            if (stream == null) continue;
            try (InputStream input = stream) {
                this.parseTrans(lang, input);
            } catch (Exception e) {
                LOGGER.error("Failed to load translations for {}", lang, e);
            }
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation parseLocation(String string) {
        return new ResourceLocation(string);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        MinecraftServer server = event.getServer();
        ComponentHelper.updateLanguage(CurtainRules.language);
        GcaConfig.CONFIGS.values().forEach(it -> it.tryInit(server));
        registerCommands(server.getCommands().getDispatcher());
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        BotCommand.register(dispatcher);
        LocCommand.register(dispatcher);
        HereCommand.register(dispatcher);
        WhereisCommand.register(dispatcher);
        TodoCommand.register(dispatcher);
        WlistCommand.register(dispatcher);
        BlistCommand.register(dispatcher);
        SopCommand.register(dispatcher);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ComponentHelper.updateLanguage(CurtainRules.language);
        PlayerGameProfileCache info = PlayerGameProfileCache.of(player);
        Consumer<ServerPlayer> consumer = ON_PLAYER_LOGGED_IN.remove(info.name());
        if (consumer != null) consumer.accept(player);
        if (GcaSetting.welcomePlayer) WelcomeMessage.onPlayerLoggedIn(player);
    }

}