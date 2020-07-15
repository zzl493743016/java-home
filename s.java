import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class s {

    public static void main(String[] args) throws Exception {
        PrintWriter writer = new PrintWriter(
                "C:\\Users\\Arthas\\Desktop\\my\\java-note\\_sidebar.md","utf-8");
        File root = new File("C:\\Users\\Arthas\\Desktop\\my\\java-note\\docs");
        String prefix = "";
        String path = "./" + root.getName();

        Arrays.sort(root.listFiles());

        for (File file : root.listFiles()) {
            aaa(file, prefix, path, writer);
        }
    }


    static void aaa(File root, String prefix, String path, PrintWriter writer) throws IOException {
        if (root.isFile()) {
            String fileName = root.getName().substring(0, root.getName().lastIndexOf("."));
            String s = String.format("%s* [%s](%s)", prefix, fileName, path + "/" + root.getName());
            writer.println(s);
            writer.println();
            writer.flush();
            System.out.println(s);
            System.out.println();
        }
        if (root.isDirectory()) {
            String s = prefix + "* " + root.getName();
            writer.println(s);
            writer.println();
            writer.flush();
            System.out.println(s);
            System.out.println();
            File[] files = root.listFiles();
            if (files != null && files.length > 0) {
                Arrays.sort(files, (o1, o2) -> {
                    int n1 = extractNumber(o1.getName());
                    int n2 = extractNumber(o2.getName());
                    return n1 - n2;
                });
                prefix += "    ";
                path += "/" + root.getName();
                for (File file : files) {
                    aaa(file, prefix, path, writer);
                }
            }
        }
    }

    private static int extractNumber(String name) {
        int i;
        try {
            String number = name.replaceAll("[^\\d]", "");
            i = Integer.parseInt(number);
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

}
