# Puzzles-Vowels

Write a program to count the number of vowels occurring in all the substrings of a given String.

Consider a string of lowercase characters that contains 0 or more vowels.
The string is of length n and can be divided into n(n+1)/2 substrings.

The simple solution is to count the number of vowels within each substring and then add them together to get the final result.

However, this is inefficient.

## Proof of n(n+1)/2 on why it is the total number of substrings

Note that the value of n(n+1)/2 can be derived as such.

When a given string is "a", then there is 1 substring of 1 (identity case).
S(1) = {"a"}, 1 substring

When a given string is "ab", then there is 1 substring of 2, and 2 subsstrings of 1.
S(2) = {"a", "b", "ab"}, 3 substrings

When a given string is "abc", then there is 1 substring of 3, 2 substrings of 2, and 3 substrings of 1.
S(3) = {"a", "b", "c", "ab", "bc", "abc"}, 6 substrings

When a given string is "abcd", then there is 1 substring of 4, 2 substrings of 3, 3 substrings of 2, and 4 substrings of 1.
S(4) = {"a", "b", "c", "d", "ab", "bc", "cd", "abc", "bcd", "abcd"}, 10 substrings

Now, we can generaliz this pattern
S(n) = 1 + 2 + 3 + 4 + ... + (n-3) + (n-2) + (n-1) + n

However, this is not computationally friendly.

We use algebra to re-arrange the values.

Consider S(n) + S(n)

 = 1 + 2 +      3 +    4     + ... + (n-3) + (n-2) + (n-1) + n

\+ n + (n-1) + (n-2) + (n-3) + ... + 4     + 3     + 2     + 1

 = (1 + n) + (2 + n-1) + (3 + n-2) + (4 + n-3) + ... + (n-3 + 4) + (n-2 + 3) + (n-1 + 2) + (n + 1)

 = (n + 1) + (n +1)    + (n + 1)   + (n + 1) + ...   + (n + 1)   + (n + 4)   + (n + 1)   + (n + 1)

Note that there are n pair-sums of (n + 1)
2S(n) = n(n + 1)
S(n) = n(n + 1)/2

This can also be represented by the combination of m choose k = m!/((m-k)!*k!)
where m = n + 1 and k = 2
(n + 1) choose 2

 = (n + 1)!

   *----------------*

   (n + 1 -2)! * 2!

 = (n - 1)! * (n) * (n + 1)

   *------------------------*

   (n - 1)! * (1 * 2)

= n(n + 1)/2

Note (n - 1)! cancel out

This term counts the number of ways in choosing a subset of size 2 from {0,1,...,𝑛}.
The subsets of the pair-sums from the algebraic proof.

## First efficient algorithm to count all the vowels occurring in all the substrings of a given string

Refer to the following post.

https://www.tutorialspoint.com/count-the-number-of-vowels-occurring-in-all-the-substrings-of-given-string-in-cplusplus

In this approach, we will create an array of integers, which stores counts of occurrences of the ith character in all substrings in arr[i].

The 0th character occurs in n substrings where is the length of the input string.

The ith character occurs:
1. in all the substrings containing it (n-i)
2. plus the number of substrings containing the ith character as well as the previous character (arr[i-1])
3. minus the number of substrings formed by previous characters only (i)

Then sum the counts when the ith character is a vowel.

Here is a sample method.

```
 //calculates total sum of all vowel occurrences
   static int vowel_calc(String str)
   {
       int n = str.length();
       int arr[] = new int[n];

       for (int i = 0; i < n; i++) 
       { 
           if (i == 0)
               // Number of times the 0th character occurs in all substrings
               arr[i] = n; 
           else
               // Number of times the ith character occurs in all substrings
               arr[i] = (n - i) + arr[i - 1] - i;
       }

       int sum = 0;
       for (int i = 0; i < n; i++) {
           char ch = str.charAt(i);
           // Check to see if ith character is a vowel
           if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
           sum += arr[i];
       }

       // Return total sum of all vowel occurrences      
       return sum;
   }
```

## Second efficient algorithm to count all the vowels occurring in all the substrings of a given string

Refer to the following posts.

https://www.geeksforgeeks.org/count-the-number-of-vowels-occurring-in-all-the-substrings-of-given-string-set-2/
https://stackoverflow.com/questions/71448358/given-a-string-s-of-length-n-calculate-the-number-of-occurrences-of-a-charact/71449177#71449177

In a string of length n, there are n(n+1)/2 substrings.

See above how this is established.

The character at index i appears once in every substring,
except for the substrings that end at character i-1 or before,
and except for the substrings that start at character i+1 or after.
Note that here we only count this specific occurrence of the character at index i.

To rephrase: in a string **ScT**, where **S** and **T** are two substrings and **c** is a character,
this occurrence of character **c** appears once in every substring of **ScT**,
except in substrings of **S** and in substrings of **T**.

Thus the number of substrings in which the character appears at index **i** in a string of length n is:

k_i = n(n+1)/2 - i(i+1)/2 - (n-i-1)(n-i)/2

k_i = (  n(n+1) - i(i+1) - (n-i-1)(n-i)  ) / 2

k_i = (  n<sup>2</sup> - i<sup>2</sup> - i - n<sup>2</sup> + ni + in - i<sup>2</sup> + n - i) / 2

k_i = ( 2n + 2ni - 2i - 2i<sup>2</sup>) / 2

k_i = n + ni - i - i<sup>2</sup>

k_i = n(i + 1) - i(i + 1)

k_i = (i + 1)(n - i)



Here is the proof for that definition of k_i.



Now, we can generalize this pattern

S(n) = 1 + 2 + 3 + 4 + ... + (n-3) + (n-2) + (n-1) + n

However, this is not computationally friendly.

We use algebra to re-arrange the values.

Consider S(n) + S(n)

 = 1 + 2 +      3 +    4 +     ... + (n-3) + (n-2) + (n-1) + n

\+ n + (n-1) + (n-2) + (n-3) + ... + 4     + 3     + 2     + 1

 = (1 + n) + (2 + n-1) + (3 + n-2) + (4 + n-3) + ... + (n-3 + 4) + (n-2 + 3) + (n-1 + 2) + (n + 1)

 = (n + 1) + (n +1)    + (n + 1)   + (n + 1) + ...   + (n + 1)   + (n + 4)   + (n + 1)   + (n + 1)

Note that there are n pair-sums of (n + 1)
2S(n) = n(n + 1)
S(n) = n(n + 1)/2


Now consider substrings of strings S = McN

where character c is at index i

Therefore for M there are S(i) = i(i + 1)/2 substrings

Figure out the algebra for M and N to see what falls out

For substring M, it has substrings that end at character i-1 or before.

First pass, assume index i is 1-based.

M(i-1) = 1 + 2 + 3 + 4 + ... + (i-4) + (i-3) + (i-2) + (i-1)

Consider M(i-1) + M(i-1)

 = 1     + 2     + 3     + 4 +     ... + (i-4) + (i-3) + (i-2) + (i-1)

\+ (i-1) + (i-2) + (i-3) + (i-4) + ... + 4     + 3     + 2     + 1

 = (1 + i-1) + (2 + i-2) + (3 + i-3) + (4 + i-4) + ... + (i-4 + 4) + (i-3 + 3) + (i-2 + 2) + (i-1 + 1)

 = (i) + (i) + (i) + (i) + ... + (i) + (i) + (i) + (i)

Note that there are (i - 1) pair-sums of (i)

2M(i-1) = (i - 1)i

M(i-1) = i(i - 1)/2


For substring N, it has substrings that start at character i+1 or after.

N(n-i) = 1 + 2 + 3 + 4 + ... + (n-i-3) + (n-i-2) + (n-i-1) + (n-i)

Consider N(n-i) + N(n-i)

 = 1 +       2 +       3 +       4 +       ... + (n-i-3) + (n-i-2) + (n-i-1) + (n-i)

\+ (n-i) + (n-i-1) + (n-i-2) + (n-i-3) + ... + 4       + 3       + 2       + 1

 = (1 + n-i) + (2 + n-i-1) + (3 + n-i-2) + (4 + n-i-3) + ... + (n-i-3 + 4) + (n-i-2 + 3) + (n-i-1 + 2) + (n-i + 1)

 = (n-i+1) + (n-i+1) + (n-i+1) + (n-i+1) + ...  + (n-i+1) + (n-i+1) + (n-i+1) + (n-i+1)

Note that there are (n-i) pair-sums of (n-i+1)

2N(n-i) = (n-i)(n-i+1)

N(n-i) = (n-i)(n-i+1)/2

Thus the number of substrings in which the character c appears at index i in a string of length n is:

k_i = n(n+1)/2 - i(i-1)/2 - (n-i)(n-i+1)/2

k_i = (  n(n+1) - i(i-1) - (n-i)(n-i+1)  ) / 2

k_i = ( n<sup>2</sup> + n - i<sup>2</sup> - i - n<sup>2</sup> + ni +in - i<sup>2</sup> - n + i ) / 2

k_i = (2ni -2i<sup>2</sup>) / 2

k_i = (ni - i<sup>2</sup>)

k_i = i(n-i)

However, this does not produce the correct substring counts.




Now consider substrings of strings S = McN where character c is at index i.
Also the character c is at i-th position, where 0<=i<n (index is zero-based).

Note that when the character c is at the i-th index, then the length of M is i.

Therefore we can assert for M there are M(i) = i(i+1)/2 substrings.

Prove this now.

For substring M, it has substrings that end at character i-1 or before.

Second pass, i-th index is zero-based.

M(i) = 1 + 2 + 3 + 4 + ... + (i-3) + (i-2) + (i-1) + i

Consider M(i) + M(i)

 = 1 + 2     + 3     + 4 + ... + (i-3) + (i-2) + (i-1) + i

\+ i + (i-1) + (i-2) + (i-3) ... + 4   + 3     + 2     + 1

 = (1 + i) + (2 + i-1) + (3 + i-2) + (4 + i-3) + ... + (i-3 + 4) + (i-2 + 3) + (i-1 + 2) + (i + 1)

 = (i + 1) + (i + 1) + (i + 1) + (i + 1) + ... + (i + 1) + (i + 1) + (i + 1) + (i + 1)

Note that there are i pair-sums of (i + 1)

2M(i) = i(i + 1)

M(i) = i(i + 1)/2

Double-check this with the binomial coefficient.

b choose k  = b!/((b-k)!k!)

where b = i + 1 and k = 2 for pair sums

 = (i + 1)!/((i + 1 - 2)!2!)

 = (i - 1)!*(i)*(i + 1)/((i - 1)!*2)

 = i(i + 1) / 2

Note that when the character c is at the nth index, then the length of N is n-(i+1) = n-i-1.

Therefore we can assert for N there are N(n-i-1) = (n-i)(n-i-1)/2

Prove this now.

For substring N, it has substrings that end at character i+1 or after.

Second pass, i-th index is zero-based.

Check a few strings for the length of N to confirm the generalization is correct.


012

abc

n=3, for "a" i-th index is 0, len(N) = 3-0-1 = 2, length of N which is "bc"

n=3, for "b" i-th index is 1, len(N) = 3-1-1 = 1, length of N which is "c"

n=3, for "c" i-th index is 2, len(N) = 3-2-1 = 0, length of N which is after "c", nothing


0123

abcd

n=4, for character c "a" i-th index is 0, len(N) = 4-0-1 = 3, length of N which is "bcd"

n=4, for character c "b" i-th index is 1, len(N) = 4-1-1 = 2, length of N which is "cd"

n=4, for character c "c" i-th index is 2, len(N) = 4-2-1 = 1, length of N which is "d"

n=4, for character c "d" i-th index is 3, len(N) = 4-3-1 = 0, length of N which is after "d", nothing

N(n-i-1) = 1 + 2 + 3 + 4 + ... + (n-i-4) + (n-i-3) + (n-i-2) + (n-i-1)

Consider N(n-i-1) + N(n-i-1)

 = 1       + 2       + 3       + 4 + ...       + (n-i-4) + (n-i-3) + (n-i-2) + (n-i-1)

\+ (n-i-1) + (n-i-2) + (n-i-3) + (n-i-4) + ... + 4       + 3       + 2       + 1

 = (1 + n-i-1) + (2 + n-i-2) + (3 + n-i-3) + (4 + n-i-4) + ... + (n-i-4 + 4) + (n-i-3 + 3) + (n-i-2 + 2) + (n-i-1 + 1)

 = (n - i) + (n - i) + (n - i) + (n - i) + ...  + (n - i) + (n - i) + (n - i) + (n - i)

Note that there are n-i-1 pair-sums of (n - i)

2N(n - i - 1) = (n - i - 1)(n - i)

N(n - i - 1)  = (n - i - 1)(n - i)/2

Double-check this with the binomial coefficient.

b choose k  = b!/((b-k)!k!)

where b = n - i and k = 2 for pair sums

 = (n - i)!/((n - i - 2)!2!)

 = (n - i - 2)!*(n - i - 1)*(n - i)/((n - i - 2)!*2)

 = (n - i - 1)(n - i) / 2


Thus the number of substrings in which the character c appears at index i in a string of length n is:

k_i = n(n + 1)/2 - i(i + 1)/2 - (n - i - 1)(n - i)/2

k_i = ( n(n + 1) - i(i + 1) - (n - i - 1)(n - i) ) / 2

k_i = ( n<sup>2</sup> + n - i<sup>2</sup> - i - n<sup>2</sup> + ni + in - i<sup>2</sup> + n - i ) / 2

k_i = ( 2n - 2i<sup>2</sup> - 2i + 2ni ) / 2

k_i = n - i<sup>2</sup> - i + ni

k_i = (ni + n) - (i<sup>2</sup> + i)

k_i = n(i + 1) - i(i + 1)

k_i = (i + 1)(n - i)

Here is a sample method.


```
 //calculates total sum of all vowel occurrences
   static int vowel_calc(String str)
   {
       int n = str.length();
       int sum = 0;
       for (int i = 0; i < n; i++) {
           char ch = str.charAt(i);
           // Check to see if ith character is a vowel
           if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
               sum += (i + 1) * (n - i);
           }
       }
       // Return total sum of all vowel occurrences
       return sum;
   }
```
