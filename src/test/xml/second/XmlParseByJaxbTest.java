package test.xml.second;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import test.xml.JsonUtil;


public class XmlParseByJaxbTest {



	@Test
    public void saxb() {
		RandomAccessFile accessFile = null;
		try {
			accessFile = new RandomAccessFile("D:/test.xml", "r");
			accessFile.seek(0);
			byte[] b = new byte[(int) accessFile.length()];
			accessFile.readFully(b);
			System.out.println(new String(b));
			Root r = unMarshal(b, Root.class);
			System.out.println(JsonUtil.objectToJsonString(r));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * xml 转bean
	 * 
	 * @param str
	 * @param cla
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unMarshal(byte[] buff, Class<T> cla) throws Exception {
		InputStream input = new ByteArrayInputStream(buff);
		JAXBContext context = JAXBContext.newInstance(cla);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(input);
	}


}

@XmlRootElement
class Root {


    private List<Elem> elems;

    @XmlElement(name = "object")
    public List<Elem> getElems() {
        return elems;
    }

    public void setElems(List<Elem> elems) {
        this.elems = elems;
    }

}

class Elem {
    private String name;
    private List<Field> fields;

    private List<JCArray> arrays;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @XmlElement(name = "field", type = Field.class)
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @XmlElement(name = "array", type = JCArray.class)
    public List<JCArray> getArrays() {
        return arrays;
    }

    public void setArrays(List<JCArray> arrays) {
        this.arrays = arrays;
    }



}


class Field {
    private String name;
    private String value;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

class JCArray {
    private String name;
    private String length;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

}
