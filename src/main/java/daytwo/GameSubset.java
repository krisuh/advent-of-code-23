package daytwo;

public record GameSubset(int red, int green, int blue) {

    public int getPower() {
        return red * green * blue;
    }

    public static class Parser {
        public static GameSubset parse(String input) {
            // 1 green, 6 red, 4 blue
            var parts = input.split(", ");
            int green = 0;
            int blue = 0;
            int red = 0;
            for (var part : parts) {
                var valueColor = part.split(" ");
                var value = Integer.parseInt(valueColor[0]);
                var color = valueColor[1];
                switch(color) {
                    case "green":
                        green = value;
                        break;
                    case "red":
                        red = value;
                        break;
                    case "blue":
                        blue = value;
                        break;
                }
            }
            return new GameSubset(red, green, blue);
        }
    }
}
