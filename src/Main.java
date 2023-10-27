import java.io.*;

public class Main {
    public static void main(String[] args) {
        String projectDirectory = "/Users/testinium/IdeaProjects/A101SOSQAMobileTestAutomaiton";  // Replace with your project directory path
        String outputFileName = "concatenatedJavaFiles.txt";

        try {
            FileWriter outputFileWriter = new FileWriter(outputFileName);

            File projectDir = new File(projectDirectory);

            if (projectDir.exists() && projectDir.isDirectory()) {
                processDirectory(projectDir, outputFileWriter);
            } else {
                System.err.println("Project directory not found.");
            }

            outputFileWriter.close();

            System.out.println("Java files concatenated successfully into " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDirectory(File directory, FileWriter outputFileWriter) throws IOException {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file, outputFileWriter);
                } else if (file.isFile() &&
                        (file.getName().endsWith(".java")       ||
                                file.getName().endsWith(".spec")||
                                file.getName().endsWith(".json")||
                                file.getName().endsWith(".csv") ||
                                file.getName().endsWith(".cpt") )) {
                    appendJavaFileContents(file, outputFileWriter);
                }
            }
        }
    }

    private static void appendJavaFileContents(File javaFile, FileWriter outputFileWriter) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(javaFile));
        String line;

        outputFileWriter.write("\n===== " + javaFile.getName() + " =====\n\n");

        while ((line = reader.readLine()) != null) {
            outputFileWriter.write(line);
            outputFileWriter.write("\n");
        }

        outputFileWriter.write("\n===== End Of File =====\n\n");
        reader.close();
    }
}