import java.util.Map; 
// Importa a interface Map, que será usada para armazenar as cores pré-definidas.

public class HtmlColorParser {
// Declara a classe responsável por fazer o parse da cor HTML.

    private final Map<String, String> presetColors;
    // Mapa que contém cores pré-definidas.
    // A chave é o nome da cor (ex: "limegreen")
    // O valor é o hexadecimal no formato "#RRGGBB"

    public HtmlColorParser(Map<String, String> presetColors) {
        // Construtor que recebe o mapa de cores pré-definidas.
        this.presetColors = presetColors;
        // Atribui o mapa recebido ao atributo da classe.
    }

    public RGB parse(String color) {
        // Método responsável por converter a string recebida em um objeto RGB.

        if (color == null || color.isEmpty()) {
            // Verifica se a cor é nula ou vazia.
            throw new IllegalArgumentException("Invalid color");
            // Lança exceção caso a entrada seja inválida.
        }

        String hexColor;
        // Variável que armazenará a cor no formato final "#RRGGBB".

        if (color.startsWith("#")) {
            // Verifica se a string começa com "#", ou seja, é hexadecimal.

            if (color.length() == 7) {
                // Caso tenha 7 caracteres, já está no formato "#RRGGBB".
                hexColor = color;
                // Apenas atribui diretamente.

            } else if (color.length() == 4) {
                // Caso tenha 4 caracteres, está no formato "#RGB".
                
                char r = color.charAt(1);
                // Pega o caractere da posição 1 (canal vermelho).
                
                char g = color.charAt(2);
                // Pega o caractere da posição 2 (canal verde).
                
                char b = color.charAt(3);
                // Pega o caractere da posição 3 (canal azul).

                hexColor = "#" + r + r + g + g + b + b;
                // Duplica cada caractere para transformar em "#RRGGBB".
                // Exemplo: "#3B7" vira "#33BB77".

            } else {
                // Se não tiver tamanho válido.
                throw new IllegalArgumentException("Invalid hex format");
            }

        } else {
            // Se não começa com "#", então é nome de cor.

            String preset = presetColors.get(color.toLowerCase());
            // Converte o nome para minúsculo e busca no mapa.

            if (preset == null) {
                // Se não existir no mapa.
                throw new IllegalArgumentException("Unknown color name");
            }

            hexColor = preset;
            // Atribui o hexadecimal correspondente.
        }

        // Neste ponto, hexColor está garantidamente no formato "#RRGGBB".

        int r = Integer.parseInt(hexColor.substring(1, 3), 16);
        // Pega os dois primeiros dígitos após "#"
        // Converte de hexadecimal (base 16) para inteiro.

        int g = Integer.parseInt(hexColor.substring(3, 5), 16);
        // Pega os dois dígitos do meio (verde) e converte.

        int b = Integer.parseInt(hexColor.substring(5, 7), 16);
        // Pega os dois últimos dígitos (azul) e converte.

        return new RGB(r, g, b);
        // Retorna um novo objeto RGB com os valores convertidos.
    }
}
