import java.util.Arrays;
import java.util.Comparator;

/**
 * Створення масиву об'єктів класу EducationalInstitution,
 * сортування за двома полями:
 *  - за назвою навчального закладу (name) за зростанням,
 *  - при однакових значеннях name - за кількістю студентів (studentCount) за спаданням, та пошук об'єкта, ідентичного заданому.
 */
public class Lab3Classes {

    public static void main(String[] args) {

        EducationalInstitution[] institutions = {
                new EducationalInstitution("КПІ ім. Ігоря Сікорського", "Київ", "Університет", 1898, 25000),
                new EducationalInstitution("КНУ ім. Тараса Шевченка", "Київ", "Університет", 1834, 22000),
                new EducationalInstitution("Львівська Політехніка", "Львів", "Університет", 1844, 18000),
                new EducationalInstitution("ХНУ ім. В.Н. Каразіна", "Харків", "Університет", 1804, 20000),
                new EducationalInstitution("Одеська Медична Академія", "Одеса", "Академія", 1900, 12000)
        };

        System.out.println("Початковий масив навчальних закладів:");
        printArray(institutions);

        Arrays.sort(
                institutions,
                Comparator
                        .comparing(EducationalInstitution::getName)
                        .thenComparing(
                                EducationalInstitution::getStudentCount,
                                Comparator.reverseOrder()
                        )
        );

        System.out.println("\nВідсортований масив:");
        printArray(institutions);

        EducationalInstitution target = new EducationalInstitution(
                "КПІ ім. Ігоря Сікорського",
                "Київ",
                "Університет",
                1898,
                25000
        );

        int foundIndex = -1;

        for (int i = 0; i < institutions.length; i++) {
            if (institutions[i].equals(target)) {
                foundIndex = i;
                break;
            }
        }

        System.out.println("\nРезультат пошуку ідентичного об'єкта:");
        if (foundIndex != -1) {
            System.out.println("Об'єкт знайдено на позиції " + foundIndex + ":");
            System.out.println(institutions[foundIndex]);
        } else {
            System.out.println("Ідентичний об'єкт не знайдено.");
        }
    }

    /**
     * Виводить масив навчальних закладів у консоль
     */
    private static void printArray(EducationalInstitution[] array) {
        for (EducationalInstitution institution : array) {
            System.out.println(institution);
        }
    }
}

/**
 * Клас, що описує навчальний заклад
 */
class EducationalInstitution {

    private String name;
    private String city;
    private String type;
    private int foundationYear;
    private int studentCount;

    /**
     * Конструктор для ініціалізації полів навчального закладу
     */
    public EducationalInstitution(String name,
                                  String city,
                                  String type,
                                  int foundationYear,
                                  int studentCount) {

        this.name = name;
        this.city = city;
        this.type = type;
        this.foundationYear = foundationYear;
        this.studentCount = studentCount;
    }

    public String getName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    @Override
    public String toString() {
        return "EducationalInstitution{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                ", foundationYear=" + foundationYear +
                ", studentCount=" + studentCount +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EducationalInstitution)) {
            return false;
        }

        EducationalInstitution other = (EducationalInstitution) obj;

        return foundationYear == other.foundationYear
                && studentCount == other.studentCount
                && name.equals(other.name)
                && city.equals(other.city)
                && type.equals(other.type);
    }

    @Override
    public int hashCode() {

        int result = name.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + foundationYear;
        result = 31 * result + studentCount;

        return result;
    }
}
