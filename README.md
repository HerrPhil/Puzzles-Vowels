# Puzzles-Vowels
Write a program to count the number of vowels occurring in all the substrings of a given String.

Consider a string of lowercase characters that contains 0 or more vowels.
The string is of length n and can be divided into n(n+1)/2 substrings.
The simple solution is to count the number of vowels within each substring and then add them together to get the final result.
Here is the code to count the number of vowels in all the substrings of a given string.

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

   n + (n-1) + (n-2) + (n-3) + ... + 4     + 3     + 2     + 1

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

