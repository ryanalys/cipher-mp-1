package edu.grinnell.csc207.util;

/**
 * Has all of the methods to encode and decode strings.
 * Author: Alyssa Ryan
 * Course: CSC-207
 */
public class CipherUtils {
  /**
   * The number of letters in the alphabet.
   */
  public static final int MAX_LETTERS = 26;

  /**
  * Takes a string and turns it into an array of integers that represent each character.
  * @param str String to be turned into an array of integers.
  * @return int[] where each element corresponds to a character in the array
  */
  private static int[] stringToIntArray(String str) {
    int length = str.length();
    char[] strArray = str.toCharArray();
    int[] iArray = new int[length];
    for (int i = 0; i < length; i++) {
      iArray[i] = letter2int(strArray[i]);
      //Shifts the value so that 'a' is 0 instead of 97
      iArray[i] = iArray[i] - (int) 'a';
    } //Turns each letter from strArray to its integer value in iArray
    return iArray;
  } //stringToIntArray

  /**
   * Takes an array of ints and translates each element to the corresponding characters.
   * @param array
   * @return char[] where each ele,emt
   */
  private static char[] intArrayToStringArray(int[] array) {
    char[] strArray = new char[array.length];
    for (int i = 0; i < array.length; i++) {
      strArray[i] = int2letter(array[i]);
    } //Turns each int in the array to a letter
    return strArray;
  } //intArrayToStringArray

  /**
   * Takes a character and turns it into the corresponding int.
   * @param letter letter to turn into the int
   * @return int that represents the character
   */
  private static int letter2int(char letter) {
    return (int) letter;
  } //letter2int

  /**
   * Takes an int and turns it into the corresponding character.
   * @param i int to turn into the letter
   * @return char that represents the int
   */
  private static char int2letter(int i) {
    return (char) (i + (int) 'a');
  } //int2letter

  /**
   * Applies the caesar code to the inputted array.
   * @param str The string to be coded translated to its int form
   * @param key The key to use when applying the caesar shift
   * @param eOrD Whether we are encoding 'e' or decoding 'd'
   * @return The int form of the coded string
   */
  public static int[] applyCaesar(int[] str, int key, char eOrD) {
    int current = 0;
    for (int i = 0; i < str.length; i++) {
      current = str[i];
      if (eOrD == 'e') {
        current += key;
        if (current >= MAX_LETTERS) {
          current -= MAX_LETTERS;
        } //if
      } else {
        current -= key;
        if (current < 0) {
          current += MAX_LETTERS;
        } //if
      } //if
      str[i] = current;
    } //for
    return str;
  } //applyCaesar

  /**
   * Encrypts a given string using the caesar cipher and key letter 'letter'.
   * @param str string to encrypt
   * @param letter char to use as the key
   * @return encrypted string
   */
  public static String caesarEncrypt(String str, char letter) {
    int key = letter2int(letter) - (int) 'a';
    int[] codeArray = stringToIntArray(str);
    codeArray = applyCaesar(codeArray, key, 'e');
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //caesarEncrypt

  /**
   * Decrypts a given string using the caesar cipher and key letter 'letter'.
   * @param str string to decrypt
   * @param letter char to use as the key
   * @return decrypted string
   */
  public static String caesarDecrypt(String str, char letter) {
    int key = letter2int(letter) - letter2int('a');
    int[] codeArray = stringToIntArray(str);
    codeArray = applyCaesar(codeArray, key, 'd');
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //caesarDecrypt

  /**
   * Applies the vigenere cipher to the given string.
   * @param str The string to be coded, translated into its int form
   * @param key The key to use for the code, translated into its int form
   * @param eOrD Whether we are encoding 'e' or decoding 'd'
   * @return The int form of the coded array
   */
  public static int[] applyVigenere(int[] str, int[] key, char eOrD) {
    int current = 0;
    for (int i = 0; i < str.length; i++) {
      current = str[i];
      if (eOrD == 'e') {
        current = current + key[i % key.length];
        if (current >= MAX_LETTERS) {
          current -= MAX_LETTERS;
        } //if
      } else {
        current = current - key[i % key.length];
        if (current < 0) {
          current += MAX_LETTERS;
        } //if
      } //if
      str[i] = current;
    } //for
    return str;
  } //applyVigenere(int[], int[], char)

   /**
   * Encrypts a given string using the vigenere cipher and the given key 'key'.
   * @param str string to encrypt
   * @param key string to use as the key
   * @return encrypted string
   */
  public static String vigenereEncrypt(String str, String key) {
    //Stores the int values of each ch in key
    int[] ikey = stringToIntArray(key);
    //Stores the int values of each ch in str
    int[] codeArray = stringToIntArray(str);
    codeArray = applyVigenere(codeArray, ikey, 'e');
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //vigenereEncrypt(String, String)

  /**
   * Decrypts a given string using the vigenere cipher and the given key 'key'.
   * @param str string to decrypt
   * @param key string to use as the key
   * @return decrypted string
   */
  public static String vigenereDecrypt(String str, String key) {
    //Stores the int values of each ch in key
    int[] ikey = stringToIntArray(key);
    //Stores the int values of each ch in str
    int[] codeArray = stringToIntArray(str);
    codeArray = applyVigenere(codeArray, ikey, 'd');
    //converts the coded array of ints to a string
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //vigenereDecrypt
} //CipherUtils class
