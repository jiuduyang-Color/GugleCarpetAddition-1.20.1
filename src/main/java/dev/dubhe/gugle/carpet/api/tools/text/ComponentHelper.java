package dev.dubhe.gugle.carpet.api.tools.text;

import dev.dubhe.curtain.utils.TranslationHelper;
import dev.dubhe.gugle.carpet.GcaExtension;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ComponentHelper {

    private static String lang = "";
    private static final Map<String, String> language = new HashMap<>();
    private static final Map<String, String> en_us = new HashMap<>();

    public static MutableComponent tr(String key, Object... args) {
        return tr(key, null, Style.EMPTY, args);
    }

    public static MutableComponent tr(String key, @Nullable TextColor color, Object... args) {
        return tr(key, color, Style.EMPTY, args);
    }

    public static MutableComponent tr(String key, @Nullable TextColor color, Style style, Object... args) {
        if (color != null) style = style.withColor(color);
        String text = language.get(key);
        return Component.translatableWithFallback(key, text, args).setStyle(style);
    }

    @SuppressWarnings("NoTranslation")
    public static MutableComponent fmt(String text, Object... args) {
        return Component.translatableWithFallback("gca.format.empty", text, args);
    }

    public static Component highlight(Object value) {
        MutableComponent component;
        if (value instanceof MutableComponent cpt) component = cpt;
        else if (value instanceof Component cpt) component = Component.literal("").append(cpt);
        else if (value instanceof String str && str.startsWith("msg.gca.")) component = tr(str);
        else component = Component.literal(String.valueOf(value));

        return component.withStyle(ChatFormatting.GOLD);
    }

    public static Component fmtTr(String key, Object... args) {
        Object[] highlights = Arrays.stream(args).map(ComponentHelper::highlight).toArray();
        return tr(key, highlights);
    }

    public static Component prefix(Component content) {
        return Component.literal("")
            .append(Component.literal("[GCA]").withStyle(ChatFormatting.DARK_AQUA))
            .append(" ")
            .append(content);
    }

    public static Component intro(Component content) {
        return fmt("======== %s ========", content).withStyle(ChatFormatting.GRAY);
    }

    private static Map<String, String> load(String lang) {
        String path = String.format("assets/%s/lang/%s.json", GcaExtension.MOD_ID, lang);
        try (InputStream stream = ComponentHelper.class.getClassLoader().getResourceAsStream(path)) {
            if (stream == null) return Map.of();
            return TranslationHelper.getTranslationFromResourcePath(stream);
        } catch (Exception e) {
            return Map.of();
        }
    }

    public static void updateLanguage(String lang) {
        if (en_us.isEmpty()) en_us.putAll(load("en_us"));
        language.clear();
        language.putAll(en_us);
        ComponentHelper.lang = lang;
        if (!"en_us".equals(lang)) language.putAll(load(lang));
    }

    public static Map<String, String> fetchLanguage(String lang) {
        if (!ComponentHelper.lang.equals(lang)) updateLanguage(lang);
        return language;
    }
}