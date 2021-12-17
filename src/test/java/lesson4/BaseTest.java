package lesson4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public abstract class BaseTest {

    public static Properties readProperties() throws Exception {
            Properties properties = new Properties();
            Files.readAllLines(Path.of(
                            Objects.requireNonNull(BaseTest.class.getResource("application.properties")).toURI()))
                    .forEach(str -> {
                        String[] props = str.split("=");
                        properties.setProperty(props[0].trim(), props[1].trim());
                    });
            return properties;
        }

        public File getFileResource(String name) {
            String dir = getClass().getSimpleName();
            return new File(Objects.requireNonNull(getClass().getResource(dir + "/" + name)).getFile());
        }

}
