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

1. On test ici deux listes de valeurs qui doivent être toutes vraies ou fausses. Ici, comme notre fonction est assez simple, on se permet de tester toute la liste d'un coup, mais on préfère en règle générale faire des cas de test pour chaque valeur afin d'identifier exactement de quel cas de test vient l'erreur.

Pour les tests vrais, on commence par tester les valeurs de base soit une chaîne de caractères vide ou juste des parenthèses par exemple, puis on teste des structures de plus en plus complexes en vérifiant différentes combinaisons d'imbrication et de suites d'imbrication.

Pour les tests faux, on commence aussi par tester les paramètres les plus simples qui doivent renvoyer faux, comme juste une parenthèse, puis on teste avec des erreurs au début, à différents stades de l'imbrication ou à la fin par exemple.

On peut remarquer que notre code est assez robuste, car il est possible d'insérer n'importe quelle chaîne de caractères n'étant pas une parenthèse, un crochet ou une accolade à n'importe quel endroit.

2. Avec l'outil de couverture de code par le test d'IntelliJ, il est possible de trouver le pourcentage de code couvert par les tests. Ici, on trouve 100%, les tests couvrent toutes les lignes du programme, les premiers cas de tests étaient bien choisis.

3. Dans notre code, on a au maximum deux opérateurs logiques dans nos prédicats, pas besoin de vérifier le Base Choice Coverage.

Ensuite, pour les if de vérification de caractère, une seule des trois conditions du if ne peut être vraie à la fois, ce qui donne vrai. Dans le cas contraire, c'est qu'il n'est pas un des trois caractères.
Il reste les deux prédicats simples du cas où l'on a un caractère fermant. Ceux-ci sont faux à moins d'avoir reconnu le caractère ouvrant correspondant ajouté plus tôt lors du passage dans l'autre if.
On ajoute également la valeur du retour qui correspond à l'état final de la pile.

Pour une bonne couverture logique du code, il faut donc parcourir ces différents scénarios de conditions. C'est le travail qui avait en fait été réalisé par nos soins lors de la création des entrées de tests dans la première partie.

Ainsi, il faut tester une chaîne de caractère vide pour seulement vérifier la condition finale, puis seulement un caractère fermant pour satisfaire la première condition, ce qui validera la première imbriquée, entrainant un résultat faux.

Ensuite, on teste avec un caractère ouvrant seulement, ce qui valide la deuxième condition, puis renvoie faux car la pile n'est pas vide. On teste aussi avec un ouvrant puis un fermant différent pour tester la deuxième condition imbriquée dans la première, ce qui renvoie faux.

On peut ensuite tester pour de nombreux cas qui peuvent renvoyer un résultat vrai soit un caractère ouvrant puis fermant (situation simple), puis avec des exemples plus complexes pour tester la robustesse du programme et s'assurer que les caractères parenthèses, crochets et accolades sont bien traités de la même manière par le programme.

On ajoute un simple test montrant qu'il est possible d'ajouter des caractères autres à la chaîne sans perturber le fonctionnement du programme (la couverture de ce cas est très mauvaise, c'est un simple exemple).

4. En lançant la couverture de mutants de PIT, on peut voir que 19 mutations ont été générées et qu'elles ont toutes été tuées. Aucun nouveau test n'est nécessaire d'après ce rapport.