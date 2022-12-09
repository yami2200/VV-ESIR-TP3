# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer

1. Tester les fonctions pop et peek est assez semblable, car les deux fonctions sont presque identiques à la différence que le pop supprime l'élément du tas. Afin de tester toutes les fonctions convenablement, il faut effectuer des push, qui ne sont donc testables que par l'appel à d'autres fonctions.

Ainsi, il faut tester que l'on obtient bien une exception lorsque le tas est vide à l'initialisation ou après des opérations. On vérifie également que pop un élément supprime bien le plus petit élément. On teste également le push avec le peek pour vérifier que lorsqu'on ajoute des éléments au tas celui-ci reste trié.

On teste le push avec des entrées dans l'ordre croissant, décroissant et dans un ordre aléatoire afin de vérifier que le tri s'effectue bien et que l'on a toujours le minimum avec un pop ou un peek.

On vérifie également que lorsqu'on ajoute ou supprime des éléments, cela est bien pris en compte pour la taille avec les tests de la fonction count.

Ainsi, le retour de l'élément minimum est commun entre le peek et le pop ainsi que les exceptions.

On a ici fait tous nos tests sur des entiers avec un Comparator d'entiers, mais cela est possible avec n'importe quelle structure de données avec n Comparator défini.

2. En lançant l'outil de couverture de test d'IntelliJ, on se rend compte que le code est couvert à 44% et seulement 5 méthodes sur 7. Cela est dû à la fonction main et à la fonction toString ajoutés à la classe.

Pour résoudre le problème, le main a été supprimé pour plus de propreté et un test pour le toString a été ajouté, la couverture est maintenant de 100% des lignes.

3. La seule décision logique se situe au niveau de la vérification de la liste pour savoir si elle est vide ou non. Ces décisions existent dans le pop et dans le peek. Ce prédicat n'a qu'un argument, pas besoin de vérifier le Base Choice Covergae.

Comme dit précédemment, il y a très peu de décisions logiques, ainsi, il suffit de tester la taille de la liste pour rentrer dans la condition indiquant que le tas est vide ou sinon poursuivre sur le reste du code. En combinant ces vérifications avec des remplissages et vidages de pile, on obtient des tests permettant une bonne vérification de notre code.

4. En utilisant PIT, 7 mutations sont générées et les 7 sont tués, avec 2,14 tests par mutation. Les tests écrits dès la première question sont assez solides pour tester cette classe.
