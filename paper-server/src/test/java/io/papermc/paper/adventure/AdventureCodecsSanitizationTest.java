package io.papermc.paper.adventure;

import com.mojang.serialization.DataResult;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.support.environment.Normal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Normal
public class AdventureCodecsSanitizationTest {

    @Test
    public void testTextColorCodecSanitization() {
        final TextColor whiteColor = TextColor.color(0xFFFFFF);
        
        // Test that formatting codes are sanitized to white color
        DataResult<TextColor> result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§m");
        assertTrue(result.result().isPresent());
        assertEquals(whiteColor, result.result().get());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§n");
        assertTrue(result.result().isPresent());
        assertEquals(whiteColor, result.result().get());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§o");
        assertTrue(result.result().isPresent());
        assertEquals(whiteColor, result.result().get());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§k");
        assertTrue(result.result().isPresent());
        assertEquals(whiteColor, result.result().get());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§l");
        assertTrue(result.result().isPresent());
        assertEquals(whiteColor, result.result().get());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§r");
        assertTrue(result.result().isPresent());
        assertEquals(whiteColor, result.result().get());
    }
    
    @Test
    public void testValidColorCodes() {
        // Test that valid color codes still work correctly
        DataResult<TextColor> result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "#FF0000");
        assertTrue(result.result().isPresent());
        assertEquals(TextColor.color(0xFF0000), result.result().get());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "red");
        assertTrue(result.result().isPresent());
        // Note: exact color values may vary, just checking that it succeeds
        assertTrue(result.result().get().value() != 0xFFFFFF); // Should not be white
    }
    
    @Test 
    public void testInvalidColorCodesStillFail() {
        // Test that truly invalid color codes still fail (not sanitized)
        DataResult<TextColor> result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "§z");
        assertFalse(result.result().isPresent());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "invalid");
        assertFalse(result.result().isPresent());
        
        result = AdventureCodecs.TEXT_COLOR_CODEC.parse(null, "#invalid");
        assertFalse(result.result().isPresent());
    }
}
