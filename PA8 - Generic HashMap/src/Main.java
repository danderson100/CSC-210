import java.util.*;

public class Main {

    public static void main(String[] args) {

//        MyHashMap<String, Integer> map = new MyHashMap<>();
//        map.put("test", 1);
//        map.put("hello", 2);
//        map.put("bye", 3);
//        map.remove("bye");
//        map.remove("test");
//        map.remove("hello");
//        int x = map.size();
//        System.out.println("should be 0, got: " + x);

//        MyHashMap<Integer, Integer> bigMap = new MyHashMap<>();
//        for (int i =0; i < 100; i++) {
//            bigMap.put(i, i+1);
//        }
//        bigMap.printTable();
//        for (int j = 0; j < bigMap.size(); j++) {
//            bigMap.remove(j);
//        }
//        int x = bigMap.size();
//        System.out.println("Expected 0, got: " + x);
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("check", 1);
        map.put("another check", 2);
        map.put("hello", 3);
        map.put("bye", 4);
        map.put("happy", 5);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        map.put("weird", 100);
        map.put("more testing", 22);
        map.put("later", 3);
        map.put("bye", 10);
        map.put("four", 50);
        map.put("I hope remove works this time.....", 101);
        map.put("what about negatives?", -150);
        map.put("even more...", 123123);
        for (int i = 0; i < 20; i++) {
            map.put("input" +i, i);
        }
        System.out.println("before remove...");
        map.printTable();
        System.out.println("--------------------------------");
        map.remove("more testing");
        map.remove("another check");
        map.remove("four");
        map.remove("weird");
        map.remove("input3");
        map.remove("input8");
        map.printTable();

//        map.printTable();
//        System.out.println(map.containsValue(3));
//        System.out.println(map.containsValue(20));
//        map.printTable();
//        map.remove("hello");
//        map.remove("four");
//        map.remove("check");
//        System.out.println("several removed: \n");
//        map.printTable();
//        map.printTable();
//        System.out.println("--------------");
//        System.out.println(map.get("hello"));
//        System.out.println(map.get("three"));
//        int x = map.get("another check");
//        System.out.println("Should be 2, got " + x);
//        boolean containsKeyCheck = map.containsKey("check");
//        System.out.println("Should be true, got " + containsKeyCheck);
//        boolean noKey = map.containsKey("not here");
//        System.out.println("Should be false, got " + noKey);
////        Map<String, Integer> map = new HashMap<>();
////        map.put("hello", 1);
////        map.put("bye", 2);
////        System.out.println(map.toString());
//
////        Map<String, Integer> map = new HashMap<>();
////        map.put("test", 1);
////        map.put("hello", 2);
////        map.put("bye", 3);
////        List<String> keys = new ArrayList<>(map.keySet());
////        //System.out.println(keys.get(0));
////        map.get("test");
////        System.out.println(map);
//
//        MyHashMap map1 = new MyHashMap();
//        map1.put("test", 1);
//        map1.put("hello", 2);
//        //System.out.println(map1.containsValue(5));
//
////        map1.remove("test");
////        System.out.println(map1);
//        map1.put("check", 3);
//        map1.put("fun", 4);
//        map1.put("happy", 5);
//        map1.put("three", 6);
//        map1.put("four", 7);
//        map1.put("should be first", 8);
//        map1.printTable();
//        //System.out.println(map1.containsValue(5));
//        map1.remove("three");
//        System.out.println("removed three, " + map1);
//        System.out.println(map1.toString());
//        System.out.println(map1.get("test"));
//        System.out.println(map1.get("happy"));
//        //map1.put("why same index???", 8);
//        //map1.put("checking it out", 44);
//        System.out.println(map1.containsKey("test"));
//        Set<String> keySet = map1.keySet();
//        System.out.println("Size should be 8, got: " + map1.size());
//        System.out.println(keySet);

    }
}
