package io.papermc.paper.adventure;

import net.kyori.adventure.text.format.TextColor;
import net.minecraft.ChatFormatting;
import org.bukkit.support.environment.Normal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Normal
public class PaperAdventureColorSanitizationTest {

    @Test
    public void testFormattingCodeSanitization() {
        // Test that formatting codes are sanitized to white color
        final TextColor whiteColor = TextColor.color(0xFFFFFF);
        
        // Test strikethrough (§m)
        assertEquals(whiteColor, PaperAdventure.asAdventure(ChatFormatting.STRIKETHROUGH));
        
        // Test underline (§n)
        assertEquals(whiteColor, PaperAdventure.asAdventure(ChatFormatting.UNDERLINE));
        
        // Test italic (§o)
        assertEquals(whiteColor, PaperAdventure.asAdventure(ChatFormatting.ITALIC));
        
        // Test obfuscated (§k)
        assertEquals(whiteColor, PaperAdventure.asAdventure(ChatFormatting.OBFUSCATED));
        
        // Test bold (§l)
        assertEquals(whiteColor, PaperAdventure.asAdventure(ChatFormatting.BOLD));
        
        // Test reset (§r)
        assertEquals(whiteColor, PaperAdventure.asAdventure(ChatFormatting.RESET));
    }
    
    @Test
    public void testValidColorCodes() {
        // Test that valid color codes still work correctly
        assertEquals(TextColor.color(0xFF5555), PaperAdventure.asAdventure(ChatFormatting.RED));
        assertEquals(TextColor.color(0x55FF55), PaperAdventure.asAdventure(ChatFormatting.GREEN));
        assertEquals(TextColor.color(0x5555FF), PaperAdventure.asAdventure(ChatFormatting.BLUE));
        assertEquals(TextColor.color(0xFFFF55), PaperAdventure.asAdventure(ChatFormatting.YELLOW));
    }
}
