package com.java.springboot.task;

import com.java.springboot.task.entity.Event;
import com.java.springboot.task.entity.Student;
import com.java.springboot.task.repository.EventRepository;
import com.java.springboot.task.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StudentRepository studentRepository;

    private List<Event> events = new ArrayList<>();

    private List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        events.add(eventRepository.save(new Event("Утренняя зарядка")));
        events.add(eventRepository.save(new Event("Завтрак")));
        events.add(eventRepository.save(new Event("Пора на работу")));
        events.add(eventRepository.save(new Event("Пора с работы")));

        students.add(studentRepository.save(new Student("Сидоров Иван Инокентьевич", "sidorovia@mail.ru", 19, "sayyourmagicword", events.get(0))));
        students.add(studentRepository.save(new Student("Петров Себастьян Вагитович", "petrovsv@gmail.com", 18, "onemoretime", events.get(1))));
        students.add(studentRepository.save(new Student("Васильева Майя Оганесовна", "vasilievamo@rambler.ru", 20, "justincaseof", events.get(2))));
        students.add(studentRepository.save(new Student("Рогов Кирилл Николаевич", "rogovkn@wonnacry.net", 21, "easyroll", events.get(3))));
        students.add(studentRepository.save(new Student("Водопьянов Сергей Николаевич", "vodopyanovsn@worldwide.net", 23, "screamy", events.get(1))));
        students.add(studentRepository.save(new Student("Карапетян Мефодий Фарсукович", "karapet@rambler.ru", 17, "iceworld", events.get(2))));
        students.add(studentRepository.save(new Student("Батистута Габриэль Эрнестович", "batistutagabriel@gmail.com", 30, "qwerty", events.get(2))));

    }
}