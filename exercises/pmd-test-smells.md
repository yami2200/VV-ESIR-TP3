# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

| Rules                                | Test smells                                                                                                                                             |
|--------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| DetachedTestCase                     | Avoir une méthode en publique, qui n'est pas annotée, dans une classe de test.                                                                          |
| JUnit4SuitesShouldUseSuiteAnnotation | (JUNIT4) Avoir une suite de test qui n'est pas annotée avec le @RunWith.                                                                                |
| JUnit4TestShouldUseAfterAnnotation   | (JUNIT4) Avoir une méthode tearDown (appelée après les tests) qui n'est pas annotée avec @After.                                                        |
| JUnit4TestShouldUseBeforeAnnotation  | (JUNIT4) Avoir une méthode setUp (appelée avant les tests) qui n'est pas annotée avec @Before.                                                          |
| JUnit4TestShouldUseTestAnnotation    | (JUNIT4) Avoir une méthode en publique commencant par "test" sans avoir l'annotation @Test.                                                             |
| JUnitAssertionsShouldIncludeMessage  | Utiliser la méthode assertEquals() avec deux arguments (sans utiliser la version à 3 arguments, le premier servant de message de test en cas d'erreur)  |
| JUnitSpelling                        | Utiliser une méthode de JUnit3 tel que tearDown ou setUp en l'orthographiant incorrectement. (Notamment avec des majuscules mal placés)                 |
| JUnitStaticSuite                     | Avoir la méthode suite() qui n'est pas publique et/ou statique.                                                                                         |
| JUnitTestContainsTooManyAsserts      | Avoir plus d'une assertion par cas de test.                                                                                                             |
| JUnitTestsShouldIncludeAssert        | Avoir un cas de test sans assertion                                                                                                                     |
| JUnitUseExpected                     | Utiliser le try catch et le fail pour tester une exception au lieu d'utiliser expected dans les paramètres de @Test.                                    |
| UnnecessaryBooleanAssertion          | Utiliser une assertion sur une valeur booléenne literal.                                                                                                |
| UseAssertEqualsInsteadOfAssertTrue   | Utiliser l'assertion assertTrue pour une égalité entre deux objets (au lieu d'utiliser assertEquals)                                                    |
| UseAssertNullInsteadOfAssertTrue     | Utiliser l'assertion assertTrue pour vérifier si un objet est null ou non (au lieu d'utiliser assertNull ou assertNotNull)                              |
| UseAssertSameInsteadOfAssertTrue     | Utiliser l'assertion assertTrue pour vérifier que deux objets sont identiques (même référence) au lieu d'utiliser assertSame                            |
| UseAssertTrueInsteadOfAssertEquals   | Utiliser l'assertion assertEquals pour vérifier la valeur d'une variable / méthode retournant un booléen (au lieu d'utiliser assertTrue ou assertFalse) |

En lançant PMD sur le projet Apache Commons Collections, nous avons détecté plusieurs tests smells JUnitAssertionsShouldIncludeMessage. Cela indique que certaines assertions n'ont pas de message affiché si jamais elles échouent.
Ainsi dans ListOrderedSetTest on a :

```java
@Test
public void testListAddRemove() {
    final ListOrderedSet<E> set = makeObject();
    final List<E> view = set.asList();
    set.add((E) ZERO);
    set.add((E) ONE);
    set.add((E) TWO);

    assertEquals(3, set.size());
    // [...]
}
```

On peut voir que l'assertion assertEquals(3, set.size()) n'a pas de message. On peut donc l'améliorer en :

```java
@Test
public void testListAddRemove() {
    final ListOrderedSet<E> set = makeObject();
    final List<E> view = set.asList();
    set.add((E) ZERO);
    set.add((E) ONE);
    set.add((E) TWO);

    assertEquals("The set size should be 3", 3, set.size());
    // [...]
}
```

On peut voir que l'assertion assertEquals(3, set.size()) a maintenant un message. Cela permet de mieux comprendre ce que l'on teste et de mieux comprendre l'erreur si jamais l'assertion échoue.