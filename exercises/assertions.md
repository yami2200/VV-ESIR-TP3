# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Le problème pour cette assertion est que le résultat de l'opération `3 * .4` est un réel et non un entier. Etant donné qu'il n'est pas toujours possible de stocker précisemment un réel sur un ordinateur, ce genre d'égalité peut parfois échoué ou réussir comme vu avec les exemples en CM. Pour éviter ce genre d'erreur, il faut donc utiliser `assertEquals` avec un delta de comparaison, autrement dit, on préferera écrire l'assertion de cette manière :
    
```java
assertTrue(Math.abs(3 * .4 - 1.2) < delta);
```

2. La différence entre `assertEquals` et `assertSame` est que la première vérifie que deux objets sont égaux, tandis que la seconde vérifie que deux objets sont identiques autrement il possède la même adresse mémoire (comparaison des références). Par exemple, si on a deux objets `String` de même valeur, `assertEquals` retournera vrai, tandis que `assertSame` retournera faux. En revanche, si on a deux objets `String` de même valeur et de même référence, `assertEquals` et `assertSame` retourneront vrai.

```java
@Test
void sameScenario() {
    String s = "test";
    assertEquals(s, s); // true
    assertSame(s, s); // true
}

@Test
void notTheSame() {
    String s = new String("test");
    String t = new String("test");
    assertEquals(s, t); // true
    assertSame(s, t); // false
}
```

3. L'utilisation de fail peut etre également utile dans des tests asynchrones qui attendent le retour d'une méthode asynchrone. On peut imaginer qu'il y ait un timer pour attendre la réponse de la méthode, si le timer expire, on peut alors utiliser 'fail' pour indiquer que le test a échoué.

```java
final CountDownLatch latch = new CountDownLatch(1);
final TypeResult expected = ...;

service.asyncCall(new ResponseHandler<TypeResult>() {
    @Override
    public void onSuccess(TypeResult result) {
        assertEquals(result, expected);
        latch.countDown();
    }
    
    [...]
});

if (!latch.await(3, TimeUnit.SECONDS)) {
    fail("Timeout");
}
```

4. L'utilisation de la méthode assertThrows apporte son lot d'avantages par rapport à l'ancienne méthode. 
- On peut parfaitement controler à quel endroit se produit l'exception, avant l'exception pouvait se produire dans un bloc de code/méthode inattendu au sein du cas de test. Avec la méthode de JUnit5, on fait un assertThrows sur la méthode où l'on attend l'exception.
- On peut récupérer l'exception et l'utiliser dans le cas de test. Par exemple, on peut récupérer le message de l'exception et l'utiliser dans le cas de test.
```java
@Test
void testException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> { method();});

    String expectedMessage = "a message";
    String actualMessage = exception.getMessage();

    assertEquals(actualMessage, expectedMessage);
}
```
- Il est possible de faire apparaitre un message d'erreur personnalisé en cas d'échec de l'assertion.
```java
@Test
void testException() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> { method();}, "No exception Thrown");
}
```