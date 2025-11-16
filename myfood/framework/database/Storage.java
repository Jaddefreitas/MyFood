package myfood.framework.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Storage {
    private String storageName;
    private List<String> tables;

    public Storage(String storageName, List<String> tables) {
        this.storageName = storageName;
        this.tables = tables;
    }

    // Deleta todas as tabelas do banco de dados
    public void delete() {
        String rootPath = getStoragePath();
        for (String table : tables) {
            String tablePath = rootPath + table + "/";
            File dir = new File(tablePath);
            if (dir.exists()) {
                deleteDirectory(dir);
            }
        }
    }

    private void deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }

    // Cria todas as tabelas do banco de dados
    public void create() {
        String rootPath = getStoragePath();
        for (String table : tables) {
            String tablePath = rootPath + table + "/";
            File dir = new File(tablePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    // Lê todos os registros de uma tabela específica
    public List<Properties> read(String tableName) {
        String tablePath = getStoragePath() + tableName + "/";
        File dir = new File(tablePath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Tabela não encontrada: " + tableName);
        }
        List<Properties> records = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".properties")) {
                Properties props = new Properties();
                try (FileInputStream fis = new FileInputStream(file)) {
                    props.load(fis);
                    records.add(props);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return records;
    }

    // Escreve um registro em uma tabela específica
    public void write(String tableName, Properties record) {
        String tablePath = getStoragePath() + tableName + "/";
        File dir = new File(tablePath);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Tabela não encontrada: " + tableName);
        }
        String recordId = record.getProperty("id");
        if (recordId == null || recordId.isEmpty()) {
            throw new IllegalArgumentException("O registro deve conter uma propriedade 'id'.");
        }
        File recordFile = new File(dir, recordId + ".properties");
        try (FileOutputStream fos = new FileOutputStream(recordFile)) {
            record.store(fos, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStoragePath() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath() + storageName + "/";
    }
}
