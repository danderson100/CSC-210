import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class PA8PublicTestCases {

    @Test
    public void testDummyTest() {
        assertFalse(false);
    }

    @Test
    public void testHashMapGetElement() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        int x = map.get("hello");
        assertEquals(2, x);
    }

    @Test
    public void testHashMapPutThreeGetValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        int x = map.put("checking", 100);
        assertEquals(100, x);
    }

    @Test
    public void testBigInput() {
        MyHashMap<Integer, Integer> bigMap = new MyHashMap<>();
        for (int i =0; i < 100; i++) {
            bigMap.put(i, i+1);
        }
        int x = bigMap.size();
        assertEquals(100, x);
    }

    @Test
    public void testBigInputContainsValue() {
        MyHashMap<Integer, Integer> bigMap = new MyHashMap<>();
        for (int i =0; i < 100; i++) {
            bigMap.put(i, i+1);
        }
        boolean check = bigMap.containsValue(82);
        assertTrue(check);
    }

    @Test
    public void testBigInputContainsKey() {
        MyHashMap<Integer, Integer> bigMap = new MyHashMap<>();
        for (int i =0; i < 100; i++) {
            bigMap.put(i, i+1);
        }
        boolean check = bigMap.containsKey(22);
        assertTrue(check);
    }

    @Test
    public void testBigInputThenRemove() {
        MyHashMap<Integer, Integer> bigMap = new MyHashMap<>();
        for (int i =0; i < 100; i++) {
            bigMap.put(i, i+1);
        }
        for (int j = 0; j < 100; j++) {
            bigMap.remove(j);
        }
        int x = bigMap.size();

        assertEquals(0, x);
    }

    @Test
    public void testHashMapRemoveAll() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        map.remove("bye");
        map.remove("test");
        map.remove("hello");
        int x = map.size();
        assertEquals(0, x);
    }

    @Test
    public void testHashMapRemoveGetValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        int x = map.remove("bye");
        assertEquals(3, x);
    }

    @Test
    public void testHashMapContainsKeyFalse() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        boolean check = map.containsKey("nope");
        assertFalse(check);
    }

    @Test
    public void testHashMapContainsKeytrue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        boolean check = map.containsKey("test");
        assertTrue(check);
    }

    @Test
    public void testHashMapkeySet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        List<String> keys = new ArrayList<>(map.keySet());
        String key1 = keys.get(0);
        assertEquals("test", key1);

    }

    @Test
    public void testHashMapFullKeySet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        List<String> keys = new ArrayList<>(map.keySet());
        List<String> keyCheck = new ArrayList<>();
        keyCheck.add("test");
        keyCheck.add("hello");
        keyCheck.add("three");
        assertNotEquals(keyCheck, keys);

    }
    @Test
    public void testHashMapClear() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        map.clear();
        assertEquals(0, map.size());

    }

    @Test
    public void testHashMapClearThenGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        map.clear();
        map.put("bye", 100);
        int x = map.get("bye");
        assertEquals(100, x);

    }

    @Test
    public void testHashMapIsEmptyTrue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("test", 1);
        map.put("hello", 2);
        map.put("bye", 3);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        map.clear();
        boolean check = map.isEmpty();
        assertTrue(check);

    }

    @Test
    public void testUpdateValues1() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("check", 1);
        map.put("another check", 2);
        map.put("hello", 3);
        map.put("bye", 4);
        map.put("happy", 5);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        map.put("hello", 5);
        map.put("three", 22);
        int x = map.get("three");
        assertEquals(22, x);
    }

    @Test
    public void testUpdateValues2() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("check", "weird");
        map.put("another check", "hello");
        map.put("hello", "goodbye");
        map.put("bye", "i am testing a really long string to see if anything weird happens when it's really long");
        map.put("happy", "CSC210");
        map.put("three", "????");
        map.put("hello", "hmmmmmmm");
        map.put("check", "this should not be weird anymore");
        String s = map.get("check");
        assertEquals("this should not be weird anymore", s);
    }

    @Test
    public void testHashMapIsEmptyFalse() {
        MyHashMap<String, Character> map = new MyHashMap<>();
        map.put("test", 'e');
        map.put("hello", 'g');
        map.put("bye", 'q');
        map.put("three", 'y');
        map.put("four", 'j');
        map.put("should be first", 'b');
        map.remove("three");
        map.remove("test");
        map.remove("four");
        map.remove("bye");
        boolean check = map.isEmpty();
        assertFalse(check);

    }

    @Test
    public void testContainsValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("check", 1);
        map.put("another check", 2);
        map.put("hello", 3);
        map.put("bye", 4);
        map.put("happy", 5);
        map.put("three", 6);
        map.put("four", 7);
        map.put("should be first", 8);
        boolean check = map.containsValue(3);
        assertTrue(check);

    }
    @Test
    public void testContainsValueFalse() {
        MyHashMap<Character, Character> map = new MyHashMap<>();
        map.put('c', 'c');
        map.put('r', 'r');
        map.put('h', 'h');
        boolean check = map.containsValue('g');
        assertFalse(check);

    }

    @Test
    public void testHashMapEmptyKeySet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("test", 1);
        map.remove("test");
        List<String> keys = new ArrayList<>(map.keySet());
        assertEquals(0, keys.size());

    }

    @Test
    public void testExample1ContainsKey() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>(); map.put(1, 2);
        assertTrue(map.containsKey(1));
    }
    @Test
    public void testExample2ContainsKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>(); map.put("1", 2);
        assertFalse(map.containsKey("2"));
    }

}

