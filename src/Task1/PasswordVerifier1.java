package Task1;

import java.util.Scanner;

 class PasswordVerifier1 {
     public static void main(String[] args) {
         char ch;
         Scanner scan = new Scanner(System.in);

         System.out.print("Введите пароль: ");
         String password = scan.nextLine();

         try {
             if (password.length() != 8) {
                 throw new InvalidPasswordException("Пароль должен быть не менее 8 символов!");

             }
         } catch (InvalidPasswordException e) {
             System.out.println("Ошибка: " + e.getMessage());
         }
         try {

             for (int l = 0; l < password.length(); l++) {


                 if ((!(password.matches(".*[A-Z]*"))) && ((!(password.matches(".*[А-Я]*"))))){
                     throw new InvalidPasswordException("Пароль должен содержать хотя бы одну заглавную букву!");

                 }
             }
         } catch (InvalidPasswordException e) {
             System.out.println("Ошибка: " + e.getMessage());
         }
         try {


             for (int k = 0; k < password.length(); k++) {

                 if (!(password.matches(".*[0-9]+"))) {
                     throw new InvalidPasswordException("Пароль должен содержать хотя бы одну цифру!");
                 }
             }
         } catch (InvalidPasswordException e) {
             System.out.println("Ошибка: " + e.getMessage());
         }

     }
 }
