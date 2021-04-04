package ru.job4j.serial;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * <?xml version="1.1" encoding="UTF-8" ?>
 * <userXML age="25" isWork="true" name="Ivan">
 *     <school number="222" isGraduate="true"/>
 *     <relatives>
 *         <relative>
 *             <relative>brother</relative>
 *         </relative>
 *         <relative>
 *             <relative>sister</relative>
 *         </relative>
 *     </relatives>
 * </userXML>
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXML {

    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean isWork;
    @XmlAttribute
    private String name;
    private School school;
    @XmlElementWrapper(name = "relatives")
    @javax.xml.bind.annotation.XmlElement(name = "relative")
    private Relative[] relatives;

    public UserXML() {

    }

    public UserXML(int age, boolean isWork, String name, School school, Relative[] relatives) {
        this.age = age;
        this.isWork = isWork;
        this.name = name;
        this.school = school;
        this.relatives = relatives;
    }

    @XmlElement(value = "relative")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Relative {

        private String relative;

        public Relative() {

        }

        public Relative(String relative) {
            this.relative = relative;
        }

        @Override
        public String toString() {
            return "Relative{" +
                    "relative='" + relative + '\'' +
                    '}';
        }
    }

    @XmlElement(value = "school")
    public static class School {
        @XmlAttribute
        private int number;
        @XmlAttribute
        private boolean isGraduate;

        public School() {

        }

        public School(int number, boolean isGraduate) {
            this.number = number;
            this.isGraduate = isGraduate;
        }

        @Override
        public String toString() {
            return "School{" +
                    "number=" + number +
                    ", isGraduate=" + isGraduate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserXML{" +
                "age=" + age +
                ", isWork=" + isWork +
                ", name='" + name + '\'' +
                ", school=" + school +
                ", relatives=" + Arrays.toString(relatives) +
                '}';
    }

    public static void main(String[] args) throws Exception{
        UserXML user = new UserXML(25,
                true,
                "Ivan",
                new School(222, true),
                new Relative[]{
                        new Relative("brother"),
                        new Relative("sister")}
                );
        JAXBContext jaxbContext = JAXBContext.newInstance(UserXML.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String userXML = "";
        try(StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            userXML = writer.getBuffer().toString();
            System.out.println(userXML);
        }
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        try(StringReader reader = new StringReader(userXML)) {
            UserXML result = (UserXML) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

/**
 * POJO (Plain Old Java Object) — «старый добрый Java-объект», простой Java-объект.
 * Термин впервые начал употребляться Мартином Фаулером и его коллегами как результат поиска способов упрощения разработки.
 * Нет формального определения, какие объекты являются POJO, обычно руководствуются следующими соглашениями для класса:
 *
 * не наследуется от других классов (возможно, кроме POJO-классов того же пакета)
 * не реализует интерфейсов (иногда делается исключение для маркерных интерфейсов из стандартной библиотеки, или тех, которые нужны для бизнес-модели),
 * не использует аннотаций в определениях
 * не зависит от сторонних библиотек.
 * Иногда под POJO подразумевают JavaBean, но JavaBeans имеют более строгие ограничения.
 *
 * Для использования JAXB нужно в модели добавить дефолтные конструкторы.
 *
 * Для того чтобы сериализовать и десериализовать нужно добавить аннотации JAXB,
 * которые дадут библиотеке информацию о том как парсить объект.
 *
 * 1) xml обязательно должен иметь корневой тег, в котором все и будет располагаться. Для его обозначения служит
 * @XmlRootElement. Эту аннотацию нужно ставить над сущностью, которая будет корневой - UserXML
 *
 * 2) Над вложенными сущностями нам нужно поставить просто @XmlElement
 *
 * 3) Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute, по умолчанию поле парсится как тег
 *
 * 4) Мы можем указать также как мы хотим читать/писать объект.
 * По геттерам/сеттерам или напрямую по полям (используется рефлексия).
 * Мы будем использовать доступ по полям. Для этих целей служит аннотация @XmlAccessType
 *
 * Чтобы массив выводился так как мы хотим мы можем переименовать элемент statuses в status
 * и использовать тег @XmlElementWrapper
 *
 * @XmlElementWrapper(name = "statuses")
 * @XmlElement(name = "status")
 * private String[] statuses;
 */
