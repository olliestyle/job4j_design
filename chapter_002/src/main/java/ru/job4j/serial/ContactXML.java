package ru.job4j.serial;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "contactXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactXML {
    @XmlAttribute
    private String phone;

    public ContactXML() {

    }

    public ContactXML(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
