package sk.bednarik.nlp.tika;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Pavol Berta
 * @since 26. 2. 2019
 */
public class FileExtractor {

  public static String extractContentUsingParser(String fileString)
      throws IOException, TikaException, SAXException {
    InputStream stream = FileExtractor.class.getClassLoader().getResourceAsStream(fileString);

    PDFParser parser = new PDFParser();
    StringWriter any = new StringWriter();
    ContentHandler handler = new BodyContentHandler(any);
    Metadata metadata = new Metadata();
    PDFParserConfig pdfParserConfig = new PDFParserConfig();
    pdfParserConfig.setSpacingTolerance(0.14f);
    pdfParserConfig.setEnableAutoSpace(true);
//    pdfParserConfig.setAverageCharTolerance(0.74f);
    ParseContext context = new ParseContext();
    context.set(PDFParserConfig.class, pdfParserConfig);

    parser.parse(stream, handler, metadata, context);
    return handler.toString();
  }

  public static void writeToFile(String fileName, String content) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    writer.write(content);

    writer.close();
  }

  public static void main(String[] args) throws TikaException, IOException, SAXException {
    String content = extractContentUsingParser("ZZ_2005_301_20190201_TLAC.pdf");
    writeToFile("ouput_tlac_2005_301.txt", content);
  }

} 
