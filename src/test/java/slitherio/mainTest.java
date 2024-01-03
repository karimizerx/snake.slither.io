package slitherio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import slitherio.Utils.*;
import slitherio.gameobjects.*;

public class mainTest {

    final static String red = "\u001B[31m";
    final static String green = "\u001B[32m";
    final static String yellow = "\u001B[34m";
    final static String white = "\u001B[37m";

    public static String result(String color, String gp, String fct, String msg) {
        return color + gp + ": " + fct + yellow + msg + white;
    }

    /* ******************** Utils ******************** */

    @Test
    public void testUtils() {
        // Test [ getValidAngle(double) ]
        for (int i = -365; i < 365; ++i) {
            double n = Utils.getValidAngle(i);
            assertTrue(n >= 0 && n <= 360, result(red, "Utils", "getValidAngle", ": " + i));
        }
    }

    /* ******************** GameObject ******************** */

    @Test
    public void testGameObject() {
        double maxX = 463, maxY = 542;

        Segment seg = new Segment(24, 46);
        Segment seg2 = new Segment(73, 95); // Top-Left corner collides the bottom-right corner of seg
        Segment seg3 = new Segment(maxX / 2, -25); // At the limite
        Segment seg4 = new Segment(-50, maxY + 50); // Out of Bounds
        double segW = seg.getWidth(), segH = seg.getHeight();

        // Test [ getLeft, getRight, getUp, getDown ]
        assertEquals(seg.getLeft(), seg.getCenterX() - segW / 2, result(red, "GameObject", "getLeft", ""));
        assertEquals(seg.getRight(), seg.getCenterX() + segW / 2, result(red, "GameObject", "getRight", ""));
        assertEquals(seg.getRight() - seg.getLeft(), segW, result(red, "GameObject", "getRight/getLeft", ""));

        assertEquals(seg.getUp(), seg.getCenterY() - segH / 2, result(red, "GameObject", "getUp", ""));
        assertEquals(seg.getDown(), seg.getCenterY() + segH / 2, result(red, "GameObject", "getDown", ""));
        assertEquals(seg.getDown() - seg.getUp(), segH, result(red, "GameObject", "getDown/getUp", ""));

        // Test [ collides(GameObject), collides(double, double), isOut ]
        assertTrue(seg.collides(seg2), result(red, "GameObject", "collides(GameObject)", ""));
        assertFalse(seg.collides(seg3), result(red, "GameObject", "collides(GameObject)", ""));

        assertTrue(seg.collides(maxX, maxY), result(red, "GameObject", "collides(double, double)", ""));
        assertTrue(seg3.collides(maxX, maxY), result(red, "GameObject", "collides(double, double)", ""));

        assertFalse(seg3.isOut(maxX, maxY), result(red, "GameObject", "isOut", ""));
        assertTrue(seg4.isOut(maxX, maxY), result(red, "GameObject", "isOut", ""));

    }

    @Test
    public void testFood() {
        // Test [ FoodRandom ]
        double maxX = 463, maxY = 542;
        int cnt = 0;

        Food food = Food.FoodRandom(maxX, maxY);
        double x = food.getCenterX(), y = food.getCenterY();
        assertFalse(food.isOut(maxX, maxY), result(red, "Food", "FoodRandom:", " food is out of Bounds"));

        for (int i = 0; i < 100; ++i) {
            Food f = Food.FoodRandom(maxX, maxY);
            assertFalse(food.isOut(maxX, maxY), result(red, "Food", "FoodRandom:", " food is out of Bounds"));

            if (f.getCenterX() == x && f.getCenterY() == y)
                ++cnt;

            x = f.getCenterX();
            y = f.getCenterY();
        }

        assertTrue(cnt < 2, result(red, "Food", "FoodRandom:", " repetition"));

    }
}
