package sk.bednarik.nlp.utils;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;

import java.io.*;
import java.util.List;

public class AnnotationFilesUtils {

  public static void saveResults(String id, Annotation annotation, String folder)
      throws FileNotFoundException, UnsupportedEncodingException {
    List<CoreLabel> labels = annotation.get(CoreAnnotations.TokensAnnotation.class);
    String originalText = annotation.get(CoreAnnotations.OriginalTextAnnotation.class);
    try (PrintWriter writer = new PrintWriter(folder + id + ".ann", "UTF-8")) {
      int i = 1;
      CoreLabel lastLabel = null;
      int start = 0;
      for (CoreLabel label : labels) {
        if (lastLabel != null && label.ner().equals(lastLabel.ner())) {
        } else if (lastLabel != null) {
          if (!lastLabel.ner().equals("O")) {
            writer.println(
                "T" + i + "\t" + lastLabel.ner() + " " + start + " " + lastLabel.endPosition() + "\t" + originalText
                    .substring(start, lastLabel.endPosition()));
            start = label.beginPosition();
            i++;
          } else {
            start = label.beginPosition();
          }
        } else {
          start = label.beginPosition();
        }
        lastLabel = label;
      }


    }
  }

  public static void saveEvaluations(String id, Annotation annotation, String fromFolder, String toFolder)
      throws IOException {
    List<CoreLabel> labels = annotation.get(CoreAnnotations.TokensAnnotation.class);
    try (PrintWriter writer = new PrintWriter(toFolder + id + ".tsv", "UTF-8")) {
      try (BufferedReader br = new BufferedReader(new InputStreamReader(
          new FileInputStream(new File(fromFolder + id + ".tsv")), "UTF-8"))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
          String[] parts = line.split("\t");
          writer.println(parts[0] + "\t" + parts[4] + "\t" + labels.get(i).ner());
          i++;
        }
      }
    }
  }

  private static String convertLabel(String label) {
    switch (label) {
      case "ULICA":
        return "Adresa";
      case "OBEC":
        return "Adresa";
      case "MENO,OBEC":
        return "Adresa";
      case "PRIEZVISKO,OBEC":
        return "Adresa";
      case "PRIEZVISKO,ULICA":
        return "Adresa";
      case "MENO,ULICA":
        return "Adresa";
           /* case "MENO":
                return "Osoba";*//*
            case "TITUL":
                return "Osoba";
            *//*case "PRIEZVISKO":
                if(label.length()>3) {
                    return "Osoba";
                }else{
                    return "O";
                }*/
      case "NUMBER":
      case "DATE":
        return "O";
      default:
        return label;
    }
  }
}
