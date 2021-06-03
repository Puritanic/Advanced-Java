package enums;

enum Level {
    LOW,
    MEDIUM,
    HIGH
}

public class EnumsDemo {
    // enum like
    static String[] _levels = {"Low", "Medium", "High"};

    public static void main(String[] args) {
        Level l = Level.LOW;
        // Enums are values that doesn't change
        System.out.println(Level.HIGH);

        switch (l){
            case LOW:
                System.out.println("Low level");
                break;
            case MEDIUM:
                System.out.println("Medium level");
                break;
            case HIGH:
                System.out.println("High level");
                break;
        }
    }
}
