package com.acquisio.basic.java.question08;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class LambdasTest {
    @Test
    public void convertCarts() throws Exception {
        Lambdas instance = new Lambdas();
        File input = new File(Thread.currentThread().getContextClassLoader().getResource("./carts.csv").toURI());
        File output = new File("./carts_output.csv");
        output.delete();

        instance.convertCarts(input, output);

        assertTrue(output.exists());
        String[] result = FileUtils.readFileToString(output).split("\n");
        assertEquals(3, result.length);
        assertEquals(5, result[0].split(",").length);
    }

}