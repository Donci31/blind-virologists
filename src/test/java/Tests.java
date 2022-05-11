import controller.Prototype;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Tests {

    @Test
    public void test1() throws IOException {
        Prototype.runTest(1);
        File out = new File(Tests.class.getClassLoader().getResource("test1/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test1/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test2() throws IOException {
        Prototype.runTest(2);
        File out = new File(Tests.class.getClassLoader().getResource("test2/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test2/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test3() throws IOException {
        Prototype.runTest(3);
        File out = new File(Tests.class.getClassLoader().getResource("test3/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test3/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test4() throws IOException {
        Prototype.runTest(4);
        File out = new File(Tests.class.getClassLoader().getResource("test4/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test4/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test5() throws IOException {
        Prototype.runTest(5);
        File out = new File(Tests.class.getClassLoader().getResource("test5/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test5/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test6() throws IOException {
        Prototype.runTest(6);
        File out = new File(Tests.class.getClassLoader().getResource("test6/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test6/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test7() throws IOException {
        Prototype.runTest(7);
        File out = new File(Tests.class.getClassLoader().getResource("test7/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test7/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test8() throws IOException {
        Prototype.runTest(8);
        File out = new File(Tests.class.getClassLoader().getResource("test8/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test8/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test9() throws IOException {
        Prototype.runTest(9);
        File out = new File(Tests.class.getClassLoader().getResource("test9/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test9/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test10() throws IOException {
        Prototype.runTest(10);
        File out = new File(Tests.class.getClassLoader().getResource("test10/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test10/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test11() throws IOException {
        Prototype.runTest(11);
        File out = new File(Tests.class.getClassLoader().getResource("test11/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test11/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test12() throws IOException {
        Prototype.runTest(12);
        File out = new File(Tests.class.getClassLoader().getResource("test12/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test12/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test13() throws IOException {
        Prototype.runTest(13);
        File out = new File(Tests.class.getClassLoader().getResource("test13/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test13/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test14() throws IOException {
        Prototype.runTest(14);
        File out = new File(Tests.class.getClassLoader().getResource("test14/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test14/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test15() throws IOException {
        Prototype.runTest(15);
        File out = new File(Tests.class.getClassLoader().getResource("test15/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test15/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test16() throws IOException {
        Prototype.runTest(16);
        File out = new File(Tests.class.getClassLoader().getResource("test16/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test16/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test17() throws IOException {
        Prototype.runTest(17);
        File out = new File(Tests.class.getClassLoader().getResource("test17/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test17/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test19() throws IOException {
        Prototype.runTest(19);
        File out = new File(Tests.class.getClassLoader().getResource("test19/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test19/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test20() throws IOException {
        Prototype.runTest(20);
        File out = new File(Tests.class.getClassLoader().getResource("test20/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test20/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test21() throws IOException {
        Prototype.runTest(21);
        File out = new File(Tests.class.getClassLoader().getResource("test21/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test21/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test22() throws IOException {
        Prototype.runTest(22);
        File out = new File(Tests.class.getClassLoader().getResource("test22/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test22/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test23() throws IOException {
        Prototype.runTest(23);
        File out = new File(Tests.class.getClassLoader().getResource("test23/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test23/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test24() throws IOException {
        Prototype.runTest(24);
        File out = new File(Tests.class.getClassLoader().getResource("test24/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test24/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test25() throws IOException {
        Prototype.runTest(25);
        File out = new File(Tests.class.getClassLoader().getResource("test25/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test25/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test26() throws IOException {
        Prototype.runTest(26);
        File out = new File(Tests.class.getClassLoader().getResource("test26/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test26/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test27() throws IOException {
        Prototype.runTest(27);
        File out = new File(Tests.class.getClassLoader().getResource("test27/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test27/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test33() throws IOException {
        Prototype.runTest(33);
        File out = new File(Tests.class.getClassLoader().getResource("test33/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test33/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test34() throws IOException {
        Prototype.runTest(34);
        File out = new File(Tests.class.getClassLoader().getResource("test34/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test34/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test35() throws IOException {
        Prototype.runTest(35);
        File out = new File(Tests.class.getClassLoader().getResource("test35/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test35/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test36() throws IOException {
        Prototype.runTest(36);
        File out = new File(Tests.class.getClassLoader().getResource("test36/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test36/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test37() throws IOException {
        Prototype.runTest(37);
        File out = new File(Tests.class.getClassLoader().getResource("test37/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test37/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }

    @Test
    public void test38() throws IOException {
        Prototype.runTest(38);
        File out = new File(Tests.class.getClassLoader().getResource("test38/out.yml").getPath());
        File expected = new File(Tests.class.getClassLoader().getResource("test38/expected.yml").getPath());

        Assert.assertEquals(FileUtils.readLines(out, "UTF-8"), FileUtils.readLines(expected, "UTF-8"));
    }
}
