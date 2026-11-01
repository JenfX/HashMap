//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, String> map = new CustomHashMap<>();

        map.put("apple", "apple_value");
        map.put("orange", "orange_value");
        map.put("banana", "banana_value");
        map.put("tomato", "tomato_value");
        map.put("yellow", "yellow_value");
        map.put("carrot", "carrot_value");
        map.put("pineapple", "pineapple_value");
        map.put("peach", "peach_value");
        map.put("cherry", "cherry_value");
        map.put("strawberry", "strawberry_value");
        map.put("green", "green_value");
        System.out.println(map.capacity());
        map.put("blue", "blue_value");
        map.put("red", "red_value");
        System.out.println(map.capacity());

        System.out.println(map.get("peach"));
        System.out.println(map.remove("banana"));
        System.out.println(map.get("banana"));
    }
}