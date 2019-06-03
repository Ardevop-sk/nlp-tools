package sk.bednarik.nlp.utils;

import java.io.File;

public class FilenameUtils {

  public static String getIDFromFile(File file) {
    return file.getName().substring(0, file.getName().indexOf("."));
  }
}
