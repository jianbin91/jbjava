package com.gtt.setstr;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

public class Hello {

    public static void main(String[] args) {

        Hello obj = new Hello();
//        System.out.println(obj.getFileWithUtil("file/test.txt"));

//        obj.testGetRandomList();

//        long ip_decimial = obj.ipToLong("192.168.1.2");
//        System.out.println(ip_decimial);
//
//        System.out.println(obj.longToIp2(ip_decimial));

        obj.parseJson();

    }


    public void parseJson(){
        String json = "{name:\"mkyong\"}";


        Map<String,String> map = new HashMap<String,String>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {

            map = mapper.readValue(json,
                    new TypeReference<HashMap<String,String>>(){});
            System.out.println(map);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHello() {
        String result = getFileWithUtil("xml/test.xml");
        System.out.println(result);
    }

    private String getFileWithUtil(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Test
    public void testGetRandomList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);


        Hello obj = new Hello();
        for(int i = 0; i < 10; i++){
            System.out.println(obj.getRandomList(list));
        }
    }


    public int getRandomList(List<Integer> list) {

        //0-4
        int index = ThreadLocalRandom.current().nextInt(list.size());
        System.out.println("\nIndex :" + index );
        return list.get(index);

    }


    public long ipToLong(String ipAddress) {

        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;

            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }

        return result;
    }


    public String longToIp2(long ip) {
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 4; i++) {

            // 1. 2
            // 2. 1
            // 3. 168
            // 4. 192
            sb.insert(0, Long.toString(ip & 0xff));

            if (i < 3) {
                sb.insert(0, '.');
            }

            // 1. 192.168.1.2
            // 2. 192.168.1
            // 3. 192.168
            // 4. 192
            ip = ip >> 8;

        }

        return sb.toString();
    }

    @Test
    private void testAppendObjValue() {

        Object[] obj = new Object[] { "a", "b", "c" };

        System.out.println("Before Object [] ");
        for (Object temp : obj) {
            System.out.println(temp);
        }

        System.out.println("\nAfter Object [] ");
        Object[] newObj = appendObjValue(obj, "new Value");
        for (Object temp : newObj) {
            System.out.println(temp);
        }
    }


    private Object[] appendObjValue(Object[] obj, Object newObj) {

        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
        temp.add(newObj);
        return temp.toArray();

    }


    private int[] appendIntValue(int[] obj, int newValue) {

        //convert int[] to Integer[]
        ArrayList<Integer> newObj =
                new ArrayList<Integer>(Arrays.asList(ArrayUtils.toObject(obj)));

        newObj.add(newValue);

        //convert Integer[] to int[]
        return ArrayUtils.toPrimitive(newObj.toArray(new Integer[]{}));

    }


}
