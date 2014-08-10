package com.gtt.basicutilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.google.common.base.Defaults;

import com.gtt.setstr.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author tiantiangao
 */
public class DefaultsTest {

    private static boolean getJar = true;

    public static List<String> getClasseNamesInPackage(String jarName, String packageName) {
        if (getJar) {
            System.out.println("Jar \"" + jarName + "\" for \"" + packageName + '\"');
        }

        ArrayList<String> arrayList = new ArrayList<String>();
        // replaceAll 第一个参数是正则字符串，第二个参数是普通的替换字符串，不需要对正则进行转义~
        packageName = packageName.replaceAll("\\.", "/");

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
            JarEntry jarEntry;

            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if ((jarEntry.getName().startsWith(packageName))
                        && (jarEntry.getName().endsWith(".class"))) {
                    arrayList.add(jarEntry.getName().replaceAll("/", "."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void main(String[] args) {


        //String jarPath = "/Users/user/Desktop/JavaDynamicComiple.jar";
        String jarPath = "/home/hidida/workspace/guava-study/out/artifacts/guava_study_defaluttest_jar/guava-study.jar";
        String pkgPath = "com.gtt.basicutilities.DefaultsTest";
        List<String> list = DefaultsTest.getClasseNamesInPackage(jarPath, pkgPath);
        System.out.println("Found: ");
        for (String item : list) {
            System.out.println(item);
        }

    }

	@Test
	public void testGetDefaultValue() {

		assertEquals( false, Defaults.defaultValue(boolean.class).booleanValue() );
		assertEquals('\0', Defaults.defaultValue(char.class).charValue());
		assertEquals(0, Defaults.defaultValue(byte.class).byteValue());
		assertEquals(0, Defaults.defaultValue(short.class).shortValue());
		assertEquals(0, Defaults.defaultValue(int.class).intValue());
		assertEquals(0, Defaults.defaultValue(long.class).longValue());
		assertEquals((Float) 0.0f, (Float) Defaults.defaultValue(float.class).floatValue());
		assertEquals((Double) 0.0d, (Double) Defaults.defaultValue(double.class).doubleValue());
		assertNull(Defaults.defaultValue(void.class));
		assertNull(Defaults.defaultValue(String.class));
	}


}
