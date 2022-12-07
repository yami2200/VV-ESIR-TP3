# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

### 1. Input Space Partitioning

Liste des partitions:

* **invalid date**:
  * `invalid day`: (day < 1 or day > 31 ) and month and year valid
  * `invalid day upper for 30 days month`: day == 31 && month in [4, 6, 9, 11] and year valid
  * **invalid day in februrary** :
    * `invalid day upper for 28 days`: day == 29 && month == 2 && !isLeapYear(year) and year valid
    * `invalid day upper for 29 days`: day == 30 && month == 2 and year valid
  * `invalid month`: (month < 1 or month > 12) and day valid and year valid
  * `invalid year`: year < 0 and day and month valid
  * `invalid day and month`: (day < 1 or day > 31) and (month < 1 or month > 12) and year valid
  * `invalid day and year`: (day < 1 or day > 31) and month valid and year < 0
  * `invalid month and year`: (month < 1 or month > 12) and day valid and year < 0
  * `invalid day, month and year`: (day < 1 or day > 31) and (month < 1 or month > 12) and year < 0
  
* **valid date**:
  * **last day of month**
    * `last day of month with 30 days`: day == 30 and month in [4, 6, 9, 11]
    * **last day of month with 31 days**
      * `last day of month with 31 days (except december)` : : day == 31 and month in [1, 3, 5, 7, 8, 10]
      * `last day of year`: month == 12 and day == 31
    * `last day of februrary leap year`: day == 29 and month == 2 and isLeapYear(year)
    * `last day of februrary non leap year`: day == 28 and month == 2 and !isLeapYear(year)
  * **first day of month**
    * `first day of month with 30 days`: day == 1 and month in [4, 6, 9, 11]
    * **first day of month with 31 days**
      * `first day of month with 31 days (except january, march)` : : day == 1 and month in [5, 7, 8, 10, 12]
      * `first day of year`: month == 1 and day == 1
      * `first day of march leap year`: day == 1 and month == 2 and isLeapYear(year)
      * `first day of march non leap year`: day == 1 and month == 2 and !isLeapYear(year)
  * **other day**: 
    * `leap year` : day != 1 and day != 31(30, 29, 28) and month != 1 and month != 12 and valid date
    * `not leap year` : day != 1 and day != 31(30, 28) and month != 1 and month != 12 and valid date

Chaque intersection de partitions est vide et l'union de toutes les partitions est l'ensemble du domaine d'input.

Pour chaque méthode, on testera un input venant de chaque partition que l'on veut tester par méthode. Parfois, on ne souhaitera pas forcément tester un input venant d'une partition particulière mais plutot n'importe quel input d'un groupement de partitions. Les différents groupements sont listés au-dessus (texte non surligné).


Liste des partitions testées pour chaque méthode :
#### isValidDate()

| Partitions Testées                     |
|----------------------------------------|
| "invalid date"->`all`                  |
| "valid date"->last_day_of_month->`all` |
| "valid date"->other_day->`any`         |

#### constructor

| Partitions Testées    |
|-----------------------|
| "invalid date"->`any` |
| "valid date"->`any`   |

#### isLeapYear()

| Partitions Testées                       |
|------------------------------------------|
| "invalid date"->`invalid year`           |
| "valid date"->other_day->`leap year`     |
| "valid date"->other_day->`not leap year` |

#### nextDate()

| Partitions Testées                      |
|-----------------------------------------|
| "valid date"->last_day_of_month->`all`  |
| "valid date"->other_day->`any`          |

#### previousDate()

| Partitions Testées                      |
|-----------------------------------------|
| "valid date"->first_day_of_month->`all` |
| "valid date"->other_day->`any`          |

#### compareTo()

| Partitions Testées                    |
|---------------------------------------|
| null value                            |
| "valid date"->`any` (date1 == date2)  |
| "valid date"->`any` (year1 < year2)   |
| "valid date"->`any` (year1 > year2)   |
| "valid date"->`any` (month1 < month2) |
| "valid date"->`any` (month1 > month2) |
| "valid date"->`any` (day1 < day2)     |
| "valid date"->`any` (day1 > day2)     |

#### equals()
| Partitions Testées                   |
|--------------------------------------|
| null value                           |
| same object                          |
| different class object               |
| "valid date"->`any` (date1 == date2) |
| "valid date"->`any` (date1 != date2) |

