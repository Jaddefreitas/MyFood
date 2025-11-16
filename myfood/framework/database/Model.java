package myfood.framework.database;

import java.util.Properties;

import myfood.Configuration;

public abstract class Model<T extends Properties> extends Properties {
    private Storage storage;

    public Model() {
        this.storage = new Storage(Configuration.STORAGE_NAME, Configuration.TABLES);
    }

    // Retorna o nome da tabela associada ao modelo
    public abstract String table();

    @SuppressWarnings("unchecked")
    public T fromProperties(Properties props) {
        T instance = null;
        try {
            instance = (T) this.getClass().getDeclaredConstructor().newInstance();
            instance.putAll(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public T find(String id) {
        String tableName = table();
        for (Properties record : storage.read(tableName)) {
            if (record.getProperty("id").equals(id)) {
                return fromProperties(record);
            }
        }
        return null;
    }

    public void save() {
        String tableName = table();
        storage.write(tableName, this);
    }

    public T findBy(String property, String value) {
        String tableName = table();
        for (Properties record : storage.read(tableName)) {
            if (record.getProperty(property).equals(value)) {
                return fromProperties(record);
            }
        }
        return null;
    }
}
