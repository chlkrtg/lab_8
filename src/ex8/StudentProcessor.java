package ex8;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private int birthYear;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;

    public Student(int id, String lastName, String firstName, String faculty, int course) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.faculty = faculty;
        this.course = course;
    }

    public Student(int id, String lastName, String firstName, String middleName,
                   int birthYear, String address, String phone, String faculty,
                   int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthYear = birthYear;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format("Студент: %s %s %s, Факультет: %s, Курс: %d, Группа: %s",
                lastName, firstName, middleName, faculty, course, group);
    }
}

public class StudentProcessor {

    // Создание массива студентов
    public static Student[] createStudents() {
        return new Student[]{
                new Student(1, "Иванов", "Иван", "Иванович", 2007, "ул. Пушкина 1", "79609291111", "КБ", 1, "КБ-251"),
                new Student(2, "Петрова", "Мария", "Сергеевна", 2006, "ул. Терешковой 2", "79612291991", "ИТ", 2, "ИТ-241"),
                new Student(3, "Сидоров", "Алексей", "Петрович", 2000, "ул. Гагарина 3", "71209296111", "ПМИ", 3, "ПМИ-231"),
                new Student(4, "Козлова", "Елена", "Владимировна", 2002, "ул. Спортивная 1", "71129296111", "ПИ", 1, "ПИ-251")
        };
    }

    //a. список студентов заданного факультета

    //циклы и операторы условия
    public static List<Student> getStudentsByFacultyLoop(Student[] students, String faculty) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getFaculty().equals(faculty)) {
                result.add(student);
            }
        }

        return result;
    }

    //методы коллекций
    public static List<Student> getStudentsByFacultyCollection(Student[] students, String faculty) {
        List<Student> allStudents = Arrays.asList(students);
        allStudents.removeIf(student -> !student.getFaculty().equals(faculty));

        return allStudents;
    }

    //Stream API
    public static List<Student> getStudentsByFacultyStream(Student[] students, String faculty) {
        return Arrays.stream(students)
                .filter(student -> student.getFaculty().equals(faculty))
                .collect(Collectors.toList());
    }

    //b. списки студентов для каждого факультета и курса

    //циклы
    public static Map<String, Map<Integer, List<Student>>> groupByFacultyAndCourseLoop(Student[] students) {
        Map<String, Map<Integer, List<Student>>> result = new HashMap<>();

        for (Student student : students) {
            String faculty = student.getFaculty();
            int course = student.getCourse();

            if (!result.containsKey(faculty)) {
                result.put(faculty, new HashMap<>());
            }

            Map<Integer, List<Student>> courseMap = result.get(faculty);
            if (!courseMap.containsKey(course)) {
                courseMap.put(course, new ArrayList<>());
            }

            courseMap.get(course).add(student);
        }

        return result;
    }

    //методы коллекций
    public static Map<String, Map<Integer, List<Student>>> groupByFacultyAndCourseCollection(Student[] students) {
        Map<String, Map<Integer, List<Student>>> result = new HashMap<>();
        List<Student> studentList = Arrays.asList(students);

        for (Student student : studentList) {
            result.computeIfAbsent(student.getFaculty(), k -> new HashMap<>())
                    .computeIfAbsent(student.getCourse(), k -> new ArrayList<>())
                    .add(student);
        }

        return result;
    }

    //Stream API
    public static Map<String, Map<Integer, List<Student>>> groupByFacultyAndCourseStream(Student[] students) {
        return Arrays.stream(students)
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.groupingBy(Student::getCourse)
                ));
    }

    //c. список студентов, родившихся после заданного года

    //циклы
    public static List<Student> getStudentsBornAfterLoop(Student[] students, int year) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getBirthYear() > year) {
                result.add(student);
            }
        }

        return result;
    }

    //методы коллекций
    public static List<Student> getStudentsBornAfterCollection(Student[] students, int year) {
        List<Student> result = new ArrayList<>(Arrays.asList(students));
        result.removeIf(student -> student.getBirthYear() <= year);

        return result;
    }

    //Stream API
    public static List<Student> getStudentsBornAfterStream(Student[] students, int year) {
        return Arrays.stream(students)
                .filter(student -> student.getBirthYear() > year)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Student[] students = createStudents();

        //Вывод всех студентов
        System.out.println("Все студенты:");
        Arrays.stream(students).forEach(System.out::println);

        //a. Список студентов ИТ факультета
        System.out.println("\nСтуденты КБ факультета (Stream API):");
        getStudentsByFacultyStream(students, "КБ").forEach(System.out::println);

        //b. Группировка по факультету и курсу
        System.out.println("\nГруппировка по факультету и курсу:");
        Map<String, Map<Integer, List<Student>>> grouped = groupByFacultyAndCourseStream(students);
        grouped.forEach((faculty, courses) -> {
            System.out.println("Факультет: " + faculty);
            courses.forEach((course, studentList) -> {
                System.out.println("  Курс " + course + ": " + studentList.size() + " студентов");
            });
        });

        //c. Студенты, родившиеся после 2000 года
        System.out.println("\nСтуденты родившиеся после 2000 года:");
        getStudentsBornAfterStream(students, 2000).forEach(System.out::println);
    }
}