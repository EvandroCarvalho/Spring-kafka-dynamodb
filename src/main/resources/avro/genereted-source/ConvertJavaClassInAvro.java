import com.example.dynamodb.entity.Employee;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class ConvertJavaClassInAvro {

    @SneakyThrows
    public static void main(String[] args) {

        Schema schema = ReflectData.get().getSchema(Employee.class);
        log.info("schema = ".concat(schema.toString(true)));

        Path path = Paths.get("src", "main", "resources", "avro", "employee-avro.avsc");
        Files.deleteIfExists(path);
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(schema.toString(true));
        }
    }
}
