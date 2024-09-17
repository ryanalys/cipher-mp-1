package edu.grinnell.csc207.util;

/**
 * Author: Alyssa Ryan
 * Has all of the methods to encode and decode strings.
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
   * Encrypts a given string using the caesar cipher and key letter 'letter'.
   * @param str string to encrypt
   * @param letter char to use as the key
   * @return encrypted string
   */
  public static String caesarEncrypt(String str, char letter) {
    int key = letter2int(letter) - (int) 'a';
    int length = str.length();
    int[] codeArray = stringToIntArray(str);
    int current = 0;
    for (int i = 0; i < length; i++) {
      current = codeArray[i];
      current += key;
      if (current >= MAX_LETTERS) {
        current -= MAX_LETTERS;
      } //Wraps the int around if it is too large
      codeArray[i] = current;
    } //Applies the key to each element
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
    int length = str.length();
    int[] codeArray = stringToIntArray(str);
    int current = 0;
    for (int i = 0; i < length; i++) {
      current = codeArray[i];
      current -= key;
      if (current <= 0) {
        current += MAX_LETTERS;
      } //Wraps the int around if it is too small
      codeArray[i] = current;
    } //Applies the key to each element
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //caesarDecrypt

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
    //Length of str
    int length = str.length();
    //Length of key
    int keylength = key.length();

    int current = 0;
    for (int i = 0; i < length; i++) {
      current = codeArray[i];
      current = current + ikey[i % keylength];
      if (current >= MAX_LETTERS) {
        current -= MAX_LETTERS;
      } //Wraps the int around if it gets too large
      codeArray[i] = current;
    } //applies the correct element of key to each element of the string

    //converts the coded array of ints to a string
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //vigenereEncrypt

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
    //Length of str
    int length = str.length();
    //Length of key
    int keylength = key.length();

    int current = 0;
    for (int i = 0; i < length; i++) {
      current = codeArray[i];
      current = current - ikey[i % keylength];
      if (current < 0) {
        current += MAX_LETTERS;
      } //Wraps the int around if it gets too large
      codeArray[i] = current;
    } //applies the correct element of key to each element of the string
    //converts the coded array of ints to a string
    char[] charArray = intArrayToStringArray(codeArray);
    String output = new String(charArray);
    return output;
  } //vigenereDecrypt
} //CipherUtils class
