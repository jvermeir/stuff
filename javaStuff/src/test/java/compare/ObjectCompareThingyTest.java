package compare;

import org.junit.Assert;
import org.junit.Test;

public class ObjectCompareThingyTest {
    @Test
    public void testObjectIsEqualToItSelf() {
        TestClass t1 = new TestClass(1, "s", 1.0d);
        Assert.assertTrue(ObjectCompareThingy.compare(t1, t1));
    }

    @Test
    public void testObjectIsEqualToAnotherInstanceWithTheSameAttributes() {
        TestClass t1 = new TestClass(1, "s", 1.0d);
        TestClass t2 = new TestClass(1, "s", 1.0d);
        Assert.assertTrue(ObjectCompareThingy.compare(t1, t2));
    }

    @Test
    public void testObjectIsNotEqualToObjectFromDifferentClass() {
        TestClass t1 = new TestClass(1, "s", 1.0d);
        TestClass2 t2 = new TestClass2(1, "s", 1.0d);
        Assert.assertFalse(ObjectCompareThingy.compare(t1, t2));
    }

    @Test
    public void testObjectIsNotEqualToObjectWithDifferentValues() {
        TestClass t1 = new TestClass(1, "s", 1.0d);
        TestClass t2 = new TestClass(2, "s", 1.0d);
        Assert.assertFalse(ObjectCompareThingy.compare(t1, t2));
    }

    @Test
    public void testObjectIsNotEqualToObjectWithNullValue() {
        TestClass t1 = new TestClass(1, "s", 1.0d);
        TestClass t2 = new TestClass(2, null, 1.0d);
        Assert.assertFalse(ObjectCompareThingy.compare(t1, t2));
    }

    @Test
    public void testObjectWithNullValueIsNotEqualToObjectWithoutNullValue() {
        TestClass t1 = new TestClass(1, null, 1.0d);
        TestClass t2 = new TestClass(2, "s", 1.0d);
        Assert.assertFalse(ObjectCompareThingy.compare(t1, t2));
    }

    @Test
    public void testClassWithStaticField() {
        TestClassWithStaticField t1 = new TestClassWithStaticField("s", 1.0d);
        TestClassWithStaticField t2 = new TestClassWithStaticField("s", 1.0d);
        Assert.assertTrue(ObjectCompareThingy.compare(t1, t2));
    }

    @Test
    public void testResultIsNotEqualIfFirstObjectIsNull() {
        TestClass t = new TestClass(2, "s", 1.0d);
        Assert.assertFalse(ObjectCompareThingy.compare(null, t));
    }

    @Test
    public void testResultIsNotEqualIfSecondObjectIsNull() {
        TestClass t = new TestClass(2, "s", 1.0d);
        Assert.assertFalse(ObjectCompareThingy.compare(t, null));
    }

    @Test
    public void testTwoObjectsAreNotEqual() {
        TestClass t1 = new TestClass(1, "s", 1.0d);
        TestClass t2 = new TestClass(1, "s", 1.0d);
        Assert.assertFalse(t1.equals(t2));
    }
}

class TestClass {
    private int i;
    private String s;
    private double d;
    TestClass(int i, String s, double d) {
        this.i = i;
        this.s = s;
        this.d = d;
    }
}

class TestClass2 {
    private int i;
    private String s;
    private double d;
    TestClass2(int i, String s, double d) {
        this.i = i;
        this.s = s;
        this.d = d;
    }
}

class TestClassWithStaticField {
    private static int i=10;
    private String s;
    private double d;
    TestClassWithStaticField(String s, double d) {
        this.s = s;
        this.d = d;
    }
}

