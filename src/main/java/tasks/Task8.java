package tasks;

import common.Person;
import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private long count;

  // Изменила проверку и добавила фильтрацию на null, перенесла пропуск элемента в stream, изменила название
  public List<String> getPersonsFirstNames(List<Person> persons) {
    if (persons == null) {
      return List.of();
    }
    return persons.stream()
            .skip(1)
            .filter(Objects::nonNull)
            .map(Person::getFirstName)
            .filter(Objects::nonNull)
            .toList();
  }

  // Переписала с использованием предыдущей функции
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getPersonsFirstNames(persons));
  }

  // Переписала как stream, добавила middle name, изменила название
  public String getPersonFullName(Person person) {
    if (person == null) {
      return "";
    }
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // Переделала как stream, изменила название
  public Map<Integer, String> getMapPersonIdToFullName(Collection<Person> persons) {
    if (persons == null) {
      return Map.of();
    }
    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::getPersonFullName,
                    (existedName, newName) -> existedName)
            );
  }

  // Переделала как stream, подправила название
  public boolean haveSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    if (persons1 == null || persons2 == null) {
      return false;
    }
    return persons1.stream()
            .anyMatch(new HashSet<>(persons2)::contains);
  }

  // Убрала счётчик, дополнила название
  public long countEvenNumbers(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
