# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. On test ici deux listes de valeurs qui doivent être toutes vraies ou fausses. Ici comme notre fonction est assez simple on se permet de tester toute la lsite d'un coup mais on préfère en règle générale faire des cas de test pour chaque valeur afin d'identifier exactement de quel cas de test vient l'erreur.

Pour les tests vrais, on commence par tester les valeurs de base soit une chaîne de caractères vide ou juste des parenthèses par exemple, puis on teste des structures de plus en plus complexes en vérifiant différentes combinaisons d'imbrication et de suites d'imbrication.

Pour les tests faux, on commence aussi par tester les paramètres les plus simples qui doivent renvoyer faux, comme juste une parenthèse, puis on teste avec des erreurs au début, à différents stades de l'imbrication ou à la fin par exemple. 

2. On