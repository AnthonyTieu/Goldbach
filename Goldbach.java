import java.util.Scanner;
import java.io.*;

public class Goldbach
{
  public static void main(String []args) throws Exception
  {
    Scanner file = new Scanner(new File("Goldbach.txt"));
    int line = 5;
    int aggregate1;
    int aggregate2;
    int primecount;
    int primecount2;
    int prime_aggregate_pairs;
    
    for(line = 5; line > 0; line--)
      //take integer from the five lines (one by one)
    {
      int sum = file.nextInt();
      
      prime_aggregate_pairs = 0;
      //reset counter of prime_aggregate_pairs for next goldbach sum
      
      for(aggregate1 = 3; aggregate1 <= sum/2; aggregate1++) 
        /*FINDING ALL POSSIBLE FIRST NUMBERS THAT CAN ADD WITH SOMETHING ELSE TO EQUAL THE SUM 
         *(WITHOUT REPEATING like ---> 3 = 1+2 and 2+1) 
         *start from 3 and increase first aggregate, must be three since we want positive prime numbers
         *(that are odd) i.e. no negatives, no 1 or 2*/
      { 
        primecount = 0;
        for(int all_numbers_before = (int) Math.round(Math.sqrt(aggregate1)); all_numbers_before >= 2; all_numbers_before--)
          /*DETERMING WHETHER AGGREGATE IS PRIME, METHOD: take square root of aggregate (rounded to nearest integer), 
           *determine if aggregate is divisible by any other integer below its square root (PURPOSE:this is so that we do 
           *not test if ex.12 is a multiple of 11, 10, 9, 8, 7 as we clearly know it is not, this SAVES COMPUTER TIME).
           *P.S. all_numbers_before >= 2 was used since all numbers modulously divide into 1, thus, not needed*/ 
        {
          if(aggregate1 % all_numbers_before == 0)
            /*if aggregate can divide by an integer modulously without remainder, it is not prime, the counter 
             *"primecount" records this, primecount = 0 signifies aggregate did not divide by any integer, thus, it is 
             *prime. Any primecount greater than 0 is not prime*/
          {
            primecount++;
          }
        }
        if(primecount == 0)
          /*if primecount is equal to 0, proceed to find the corresponding aggregate (named aggregate2) that produces 
           *the appropriate sum*/
        {
          aggregate2 = sum - aggregate1;
          
          //SECTION 2: Determing if the corresponding aggregate2 is prime (repetition of prime checking code
          
          primecount2 = 0;
          for(int all_numbers_before2 = (int) Math.round(Math.sqrt(aggregate2)); all_numbers_before2 >= 2; all_numbers_before2--)
          { 
            if(aggregate2 % all_numbers_before2 == 0)
            {
              primecount2++;
            }
          }
          if(primecount2 == 0)
            /*if the corresponding aggregate2 (to aggregate1) is also prime, than the number of prime_aggregate_pairs 
             *(for the particular sum) increases*/ 
          {
            prime_aggregate_pairs++;
          }
        }
      }
      System.out.print("goldbach(" + sum + ") = " + prime_aggregate_pairs + "\n");
    }
  }
}      