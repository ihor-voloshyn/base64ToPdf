import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.Base64;
import java.io.File;

public class Base64ToPdf {
    public static void main(String[] args) {
        // Имя файла, содержащего строку Base64
        String base64FilePath = "C:\\Projects\\base64_text\\base64.txt"; // Убедитесь, что это полный путь к файлу

        // Имя файла для сохранения PDF
        String pdfFilePath = "C:\\Projects\\base64_text\\output.pdf";

        try {
            File base64File = new File(base64FilePath);
            if (!base64File.exists()) {
                System.err.println("Файл не найден: " + base64FilePath);
                return;
            }
            if (!base64File.canRead()) {
                System.err.println("Нет доступа для чтения файла: " + base64FilePath);
                return;
            }

            // Чтение строки Base64 из текстового файла
            StringBuilder base64StringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(base64FilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    base64StringBuilder.append(line);
                }
            }

            // Конвертация Base64 в PDF и сохранение на диск
            byte[] pdfBytes = Base64.getDecoder().decode(base64StringBuilder.toString());
            try (OutputStream outputStream = new FileOutputStream(pdfFilePath)) {
                outputStream.write(pdfBytes);
                System.out.println("PDF файл успешно сохранен по адресу: " + pdfFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
