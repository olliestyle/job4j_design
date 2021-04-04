package ru.job4j.serial;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * XML — расширяемый язык разметки.
 * Данный язык очень похож на HTML, только в отличии от него является расширяемым, что значит,
 * мы можем писать свои теги, а не использовать зарезервированные.
 *
 * Применение:
 *
 * - также как и JSON может быть использован для передачи данных между ресурсами в интернете.
 * - используется для декларативной настройки программ, т.е. формально через него мы можем настроить программу. Яркий пример pom.xml
 * - может служить основой для построения целых систем. Например, https://ru.wikipedia.org/wiki/Sedna
 * - в силу своей структуры может быть использован для формализация каких-то правил. Яркий пример checkstyle.xml
 * - и т.д.
 *
 * Структура XML
 *
 * 1. Объявление XML
 * Это первая строка, которая должна идти в файле, под расширением XML. В ней указывается кодировка и версия XML
 * <?xml version="1.1" encoding="UTF-8" ?>
 *
 * 2. Теги
 *
 * Теги - это основные детали из которых строится документ. Тег имеет имя и располагается между <>.
 * Тег бывает открывающим располагается внутри <> и закрывающим располагается внутри < />
 * Между открывающим и закрывающим тегом уже располагаются либо другие теги либо текст той сущности, что мы описываем через тег.
 *
 * Пример:
 *
 * <device>Lenovo Thinkpad</device>
 * Пример:
 *
 * <device>
 *     <producer>Lenovo</producer>
 *     <model>Thinkpad</model>
 * </device>
 * Если между открывающим и закрывающим тегом ничего нет, то можно написать сокращенную запись
 *
 * <model/>
 * вместо
 *
 * <model></model>
 * 3. Атрибуты
 *
 * Атрибуты - это часть синтаксиса, которая позволяет определить свойста элементов.
 *
 * Атрибуты пишутся в открывающем теги, после его имени в формате:
 *
 * имяАтрибут="его значение"
 *
 * Например,
 *
 * <size width="100" height="100"/>
 * Такая запись удобнее и компактнее чем
 *
 * <size>
 *     <width>100</width>
 *     <height>100</height>
 * </size>
 * 4. Комментарии
 *
 * Комментарии как однострочные, так и многострочные пишутся внутри <!-- и -->
 *
 * <!-- комментарии -->
 */

/**
 * Для использования JAXB нужно в модели добавить дефолтные конструкторы.
 */

@XmlRootElement(name = "personXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonXML {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    private ContactXML contact;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public PersonXML() {

    }

    public PersonXML(boolean sex, int age, ContactXML contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        PersonXML person = new PersonXML(false, 30, new ContactXML("11-111"), "Worker", "Married");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(PersonXML.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            PersonXML result = (PersonXML) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

    /**
     * <?xml version="1.1" encoding="UTF-8" ?>
     * <personXML sex="false" age="30">
     *     <contact phone="11-111"/>
     *     <statuses>
     *         <status>Worker</status>
     *         <status>Married</status>
     *     </statuses>
     * </personXML>
     */

}
