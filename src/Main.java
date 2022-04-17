import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        openZip("C:\\Games\\savegames\\save.zip", "C:\\Games\\savegames");

    }

    static void openZip(String zipFileDir, String unpackingDir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileDir))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = unpackingDir + "\\" + entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1 ; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
