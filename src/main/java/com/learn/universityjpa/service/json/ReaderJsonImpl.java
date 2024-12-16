package com.learn.universityjpa.service.json;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ReaderJsonImpl
 */
@Component
public class ReaderJsonImpl implements ReaderJson {
    @Override
    public JSONArray readFileJson(String path) throws Exception {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        JSONArray employeeList = null;
        try (FileReader reader = new FileReader(path)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            employeeList = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Optional<JSONArray> employeeListOpt = Optional.ofNullable(employeeList);
        return employeeListOpt.orElseThrow(() -> new Exception("error read"));
    }
}
