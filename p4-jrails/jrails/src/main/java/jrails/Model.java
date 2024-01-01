package jrails;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class Model {

    private static int nextId = 1;
    private static Map<Class<?>, Map<Integer, List<Object>>> databases = new HashMap<>();

    private int id = 0;

    public void save() {
        // Read in database if it has not be initialized
        readFromDisk();
        // List of fields of the given instance of Model
        List<Object> fields = new ArrayList<>();
        // The class of the current instance of Model
        Class<?> clazz = this.getClass();
        // Iterate through each field
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class) && (field.getType() == int.class || field.getType() == String.class || field.getType() == boolean.class)) {
                try {
                    Object value = field.get(this);
                    fields.add(value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error: Unable to Get Instance Fields");
                }
            }
        }
        // Assign an ID to the current Model if it is 0
        if (id == 0) {
            id = nextId;
            nextId++;
        } 
        // Create a new database if one does not exist for the class of Model
        if (!databases.containsKey(clazz)) {
            // Create database for the new instance of Model
            Map<Integer, List<Object>> db = new HashMap<>();
            // Insert the new database into the map of all databases
            databases.put(clazz, db);
        }
        // Grab the database corresponding to the current Model class
        Map<Integer, List<Object>> db = databases.get(clazz);
        // Update the database with the current Model
        db.put(id, fields);
        // Update the entire database
        writeToDisk();
    }

    public int id() {
        return id;
    }

    public static <T> T find(Class<T> c, int id) {
        if (!(databases.containsKey(c) && databases.get(c).containsKey(id))) { return null; }
        readFromDisk();
        List<Object> values = databases.get(c).get(id);
        try {
            T instance = c.getDeclaredConstructor().newInstance();
            Field[] fields = c.getDeclaredFields();
            // Iterate over fields and set them to the database values
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Column.class) && (field.getType() == int.class || field.getType() == String.class || field.getType() == boolean.class)) {
                    field.set(instance, values.get(i));
                }
            }
            // Set the id field for the model instance
            Field idField = Model.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(instance, id);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> List<T> all(Class<T> c) {
        if (!databases.containsKey(c)) { return null; }
        readFromDisk();
        Map<Integer, List<Object>> db = databases.get(c);
        List<T> models = new ArrayList<>(); 
        for (Map.Entry<Integer, List<Object>> row : db.entrySet()) {
            int id = row.getKey();
            models.add(find(c, id));
        } 
        return models;
    }

    public void destroy() {
        readFromDisk();
        Class<?> clazz = this.getClass();
        if (!databases.containsKey(clazz)) { throw new RuntimeException("Model does not exist!"); }
        Map<Integer, List<Object>> db = databases.get(clazz);
        db.remove(id);
        writeToDisk();
    }

    public static void reset() {
        try (FileWriter file = new FileWriter("database.txt", false)) {
            file.flush();
            databases.clear();
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to Reset Database!");
        }
    }
    
    private static void writeToDisk() {
        try {
            // Wipe the contents of the file by reopening it
            FileWriter file = new FileWriter("database.txt");
            for (Map.Entry<Class<?>, Map<Integer, List<Object>>> model : databases.entrySet()) {
                Class<?> clazz = model.getKey();
                Map<Integer, List<Object>> db = model.getValue();
                // Iterate over the database
                for (Map.Entry<Integer, List<Object>> row : db.entrySet()) {
                    // Grab the key and fields
                    Integer id = row.getKey();
                    List<Object> fields = row.getValue();
                    // Generate the String of the database row
                    String strRow = "";
                    strRow += id.toString() + ",";
                    strRow += clazz.getName() + ",";
                    for (int i = 0; i < fields.size(); i++) {
                        strRow += "\"" + fields.get(i).toString() + "\",";
                    }
                    strRow += "\n";
                    // Write the String row to the database file
                    file.write(strRow);
                } 
            }
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to Write to Disk!");
        }
    }

    private static void readFromDisk() {
        try { 
            if (!databases.isEmpty()) { return; }

            Map<Class<?>, Map<Integer, List<Object>>> dbs = new HashMap<>();

            File file = new File("database.txt");
            file.createNewFile();

            FileReader filereader = new FileReader("database.txt"); 
            BufferedReader br = new BufferedReader(filereader);

            String row;
            while ((row = br.readLine()) != null) {
                // Read in the ID and Class
                int IdIndex = row.indexOf(',', 0);
                String idStr = row.substring(0, IdIndex);
                int classIndex = row.indexOf(',', IdIndex + 1);
                String clazzStr = row.substring(IdIndex + 1, classIndex);
                // Initialize Class and ID
                Class<?> clazz = Class.forName(clazzStr);
                int id = Integer.parseInt(idStr);
                // Read in the fields
                String cell;
                int openQuoteIndex;
                int closeQuoteIndex;
                List<Object> fields = new ArrayList<>();
                for (int j = classIndex + 1; j < row.length() - 1; j++) {
                    char c = row.charAt(j);
                    openQuoteIndex = row.indexOf('"', j);
                    closeQuoteIndex = row.indexOf('"', openQuoteIndex + 1);
                    while (row.charAt(closeQuoteIndex + 1) != ',') {
                        closeQuoteIndex = row.indexOf('"', closeQuoteIndex + 1);
                    }
                    cell = row.substring(openQuoteIndex + 1, closeQuoteIndex);
                    fields.add(parse(cell));
                    j = closeQuoteIndex;
                }
                if (!dbs.containsKey(clazz)) {
                    Map<Integer, List<Object>> db = new HashMap<>();
                    dbs.put(clazz, db);
                }
                Map<Integer, List<Object>> db = dbs.get(clazz);
                db.put(id, fields);
                // Update the next ID counter
                nextId = Math.max(id, nextId);
                // Update the private database varialbe to the newly populated db
            }
            nextId++;
            databases = dbs;
            
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
            throw new RuntimeException("Unable to Read File");
        }  
    }
    
    private static Object parse(String cell) {
        try {
            int value = Integer.parseInt(cell);
            return value;
        } catch (NumberFormatException e) {
            if (cell.equals("false") || cell.equals("true")) {
                return Boolean.parseBoolean(cell);
            } else {
                return cell;
            }
        }
    }
}
