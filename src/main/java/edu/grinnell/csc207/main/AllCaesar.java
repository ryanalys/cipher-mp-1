package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Prints all possible solutions to encode or decode the string given by the user.
 */
public class AllCaesar {

  /**
   * The number of letters in the alphabet.
   */
  public static final int MAX_LETTERS = 26;
  /**
   * The minimum int index for a lowercase character.
   */
  public static final int MIN_CHAR_INDEX = 97;
  /**
   * The maximum int index for a lowercase character.
   */
  public static final int MAX_CHAR_INDEX = 122;
   /**
   * Main method for the class.
   * Runs based on input from the user.
   * @param args
   */
  public static void main(String[] args) {
    //Encoding or decoding
    String enOrDe = "";
    //String to encode
    String str = "";
    PrintWriter pen = new PrintWriter(System.out, true);
    PrintWriter errors = new PrintWriter(System.err, true);
    if (args.length != 2) {
      errors.println("Error: Incorrect number of parameters");
      return;
    } //Outputs error if given incorrect number of parameters

    //Checks that args
    if ((args[0].equals("encode") || args[0].equals("decode"))) {
      enOrDe = args[0];
    } else {
      errors.printf("Error: Invalid option: \"" + args[0]
          + "\". Valid options are \"encode\" or \"decode\".");
      return;
    } //Outputs error if given an invalid input
    str = args[1];
    if (!isAllLowercase(str)) {
      errors.println("Error: String contains characters other than lowercase letters.");
      return;
    } //If the inputted string is not all lowercase letters, then outputs an error

    String output = "";
    if (enOrDe.equals("encode")) {
      for (int i = 0; i < MAX_LETTERS; i++) {
        char key = (char) (i + (int) 'a');
        pen.printf("n = " + key + " ");
        output = CipherUtils.caesarEncrypt(str, key);
        pen.printf(output + "\n");
      } //Encodes the string using every key in the caesar cipher
    } else if (enOrDe.equals("decode")) {
      for (int i = 0; i < MAX_LETTERS; i++) {
        char key = (char) (i + (int) 'a');
        pen.printf("n = " + key + " ");
        output = CipherUtils.caesarDecrypt(str, key);
        pen.printf(output + "\n");
      } //Decodes the string using every key in the caesar cipher
    } //Outputs all 26 options
  } //main

  /**
   * Checks whether the string str is made of only lowercase letters.
   * @param str
   * @return true if it is all lowercase letters, false otherwise
   */
  public static boolean isAllLowercase(String str) {
    int length = str.length();
    for (int i = 0; i < length; i++) {
      int value = str.charAt(i);
      if (value > MAX_CHAR_INDEX || value < MIN_CHAR_INDEX) {
        return false;
      } //If we found a non-lowercase letter, return false
    } //Goes through the whole string checking for anything not a lowercase letter
    return true;
  } //isAllLowercase
} //AllCaesar
