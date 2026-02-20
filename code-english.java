import java.util.Map;
// Imports the Map interface, which will store predefined colors.

public class HtmlColorParser {
// Declares the class responsible for parsing HTML colors.

    private final Map<String, String> presetColors;
    // Map containing predefined colors.
    // Key = color name (e.g., "limegreen")
    // Value = hexadecimal string in "#RRGGBB" format.

    public HtmlColorParser(Map<String, String> presetColors) {
        // Constructor that receives the predefined colors map.
        this.presetColors = presetColors;
        // Assigns the received map to the class field.
    }

    public RGB parse(String color) {
        // Method responsible for converting a string into an RGB object.

        if (color == null || color.isEmpty()) {
            // Checks if the input is null or empty.
            throw new IllegalArgumentException("Invalid color");
            // Throws an exception if the input is invalid.
        }

        String hexColor;
        // Variable that will store the normalized "#RRGGBB" value.

        if (color.startsWith("#")) {
            // Checks if the string starts with "#", meaning it's hexadecimal.

            if (color.length() == 7) {
                // If length is 7, it is already in "#RRGGBB" format.
                hexColor = color;
                // Just assign directly.

            } else if (color.length() == 4) {
                // If length is 4, it is in "#RGB" short format.
                
                char r = color.charAt(1);
                // Gets the red channel character.
                
                char g = color.charAt(2);
                // Gets the green channel character.
                
                char b = color.charAt(3);
                // Gets the blue channel character.

                hexColor = "#" + r + r + g + g + b + b;
                // Duplicates each character to expand to "#RRGGBB".
                // Example: "#3B7" becomes "#33BB77".

            } else {
                // If length is invalid.
                throw new IllegalArgumentException("Invalid hex format");
            }

        } else {
            // If it doesn't start with "#", it must be a color name.

            String preset = presetColors.get(color.toLowerCase());
            // Converts the name to lowercase and searches in the map.

            if (preset == null) {
                // If the color name is not found.
                throw new IllegalArgumentException("Unknown color name");
            }

            hexColor = preset;
            // Assigns the corresponding hexadecimal value.
        }

        // At this point, hexColor is guaranteed to be in "#RRGGBB" format.

        int r = Integer.parseInt(hexColor.substring(1, 3), 16);
        // Extracts the first two digits after "#"
        // Converts from hexadecimal (base 16) to decimal.

        int g = Integer.parseInt(hexColor.substring(3, 5), 16);
        // Extracts the middle two digits (green channel) and converts.

        int b = Integer.parseInt(hexColor.substring(5, 7), 16);
        // Extracts the last two digits (blue channel) and converts.

        return new RGB(r, g, b);
        // Returns a new RGB object with parsed values.
    }
}
