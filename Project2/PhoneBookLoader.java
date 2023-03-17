import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PhoneBookLoader implements IPhoneBookLoader{
    public List<IPhoneBook> loadNames(String filepathToXML) throws IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<IPhoneBook> contacts = new ArrayList<>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filepathToXML));
            document.getDocumentElement().normalize();
            NodeList contact_list = document.getElementsByTagName("contact");

            for (int i = 0; i != contact_list.getLength(); i++) {
                Node contact_l = contact_list.item(i);

                if (contact_l.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList contact_detail = contact_l.getChildNodes();
                    String name = null;
                    String phone_num = null;
                    for (int j = 0; j != contact_detail.getLength(); j++) {
                        Node detail = contact_detail.item(j);
                        if (detail.getNodeType() == Node.ELEMENT_NODE) {
                            if (j == 1) {
                                name = detail.getTextContent();
                            } else if (j == 3) {
                                phone_num = detail.getTextContent();
                            }
                        }
                    }
                    PhoneBook phoneBook = new PhoneBook(name,phone_num);
                    contacts.add(phoneBook);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
