package dev.dubhe.gugle.carpet.config.updater.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.Optional;

public class ChatFormattingSerializer implements JsonSerializer<ChatFormatting>, JsonDeserializer<ChatFormatting> {
    public static final Codec<ChatFormatting> CODEC =
        ChatFormatting.CODEC
        ;

    @Override
    public ChatFormatting deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Optional.ofNullable(byName(json.getAsString())).orElse(ChatFormatting.WHITE);
    }

    @Override
    public JsonElement serialize(ChatFormatting src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(
            src.getName()
        );
    }

    @Nullable
    public static ChatFormatting byName(String name) {
        return ChatFormatting.getByName(name);
    }
}
