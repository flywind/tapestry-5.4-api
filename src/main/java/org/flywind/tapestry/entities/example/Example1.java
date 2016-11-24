package org.flywind.tapestry.entities.example;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings({ "serial", "unused" })
public class Example1 implements Serializable{

	@XmlAttribute(name = "id")
    private Long id;

    private String companyName;
    private String userName;
    private Date createTime;

    private Example1() {
    }

    public Example1(Long id, String companyName, String userName, Date createTime) {
        this.id = id;
        this.companyName = companyName;
        this.userName = userName;
        this.createTime = createTime;
    }
}
