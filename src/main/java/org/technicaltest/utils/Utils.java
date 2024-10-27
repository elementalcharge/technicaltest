package org.technicaltest.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> getListFromJson(String json) {
        File file;
        List<T> list = new ArrayList<>();
        try {
            file = ResourceUtils.getFile("classpath:" + json);
            list = objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files: " + json);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }
        return list;
    }

    public static void saveListInJson(String json, List<?> list) {
        try {
            File file = ResourceUtils.getFile("classpath:" + json);
            objectMapper.writeValue(file, list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your resources files: " + json);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your JSON formatting.");
        }
    }

}