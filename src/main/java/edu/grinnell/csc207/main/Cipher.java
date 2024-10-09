package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.CipherUtils;

/**
 * Runs the correct cipher program from CipherUtils based on user input.
 * Author: Alyssa Ryan
 * Course: CSC-207
*/
public class Cipher {
  /**
   * The number of arguements that the user should be passing.
   */
  public static final int ARGS_SIZE = 4;
  /**
   * Max number of actions allowed.
   */
  public static final int MAX_ACTIONS = 2;


  /**
   * Main class.
   * @param args
   */
  public static void main(String[] args) {
    String enOrDe = "empty";
    String cOrV = "empty";
    String str = "";
    String key = "-empty";
    //Stores if we have already gotten the string from the user.

    int actionsFound = 0;
    int strFound = 0;
    PrintWriter errors = new PrintWriter(System.err, true);
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != ARGS_SIZE) {
      errors.println("Error: Expected 4 parameters, recieved " + args.length);
      return;
    } //Correct number of parameters check

    //Adds the users' input to the correct variable
    for (int i = 0; i < ARGS_SIZE; i++) {
      if (args[i].isEmpty()) {
        errors.println("Error: empty parameter");
        return;
      } else if (actionsFound < MAX_ACTIONS && args[i].charAt(0) == '-') {
        if (args[i].equals("-encode") || args[i].equals("-decode")) {
          enOrDe = args[i];
          actionsFound++;
        } else if (args[i].equals("-caesar") || args[i].equals("-vigenere")) {
          cOrV = args[i];
          actionsFound++;
        } //Adds argument to either enOrDe or cOrV. depending on where it belongs
      } else {
        if (strFound == 0) {
          str = args[i];
          strFound = 1;
        } else {
          key = args[i];
        } //Adds args[i] to str if empty, key if not
      } //Checks if args[i] has a '-' character at the beginning or not
    } //Adds args[i] to the correct string

    //pen.printf("enOrDe: %s. cOrV: %s. str: %s. key: %s.\n", enOrDe, cOrV, str, key);
    if (enOrDe.equals("empty")) {
      errors.println("Error: no valid action specified. Legal values are '-encode' and '-decode'");
      return;
    } else if (cOrV.equals("empty")) {
      errors.println("Error: no cipher type specified. Legal values are '-caesar' and '-decode'");
      return;
    } else if (key.isEmpty() || key.equals("-empty")) {
      errors.println("Error: Empty keys are not permitted");
      return;
    } else if (str.isEmpty()) {
      pen.println("\n");
      return;
    } else if (cOrV.equals("-caesar") && key.length() != 1) {
      errors.println("Error: Caesar ciphers require a one-character key");
      return;
    } else if (!AllCaesar.isAllLowercase(str)) {
      errors.println("Error: strings must be only lowercase letters");
      return;
    } //Outputs an error message if an incorrect value was inputted

    String output = "";
    if (enOrDe.equals("-encode")) {
      if (cOrV.equals("-caesar")) {
        char keyc = key.charAt(0);
        output = CipherUtils.caesarEncrypt(str, keyc);
      } else {
        output = CipherUtils.vigenereEncrypt(str, key);
      } //Calls a caesar cipher method
    } else {
      if (cOrV.equals("-caesar")) {
        char keyc = key.charAt(0);
        output = CipherUtils.caesarDecrypt(str, keyc);
      } else {
        output = CipherUtils.vigenereDecrypt(str, key);
      } //Calls a vigenere cipher method
    } //Calls the correct program
    pen.println(output);
  } //main
} //Cipher class
