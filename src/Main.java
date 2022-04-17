import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        openZip("C:\\Games\\savegames\\save.zip", "C:\\Games\\savegames");
        System.out.println(openProgress("C:\\Games\\savegames\\save1.dat"));
    }

    private static void openZip(String zipFileDir, String unpackingDir) {
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

    private static GameProgress openProgress(String fileDir) {
        GameProgress gp = null;
        try (FileInputStream fis = new FileInputStream(fileDir);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gp = (GameProgress) ois.readObject();
        }  catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gp;
    }
}