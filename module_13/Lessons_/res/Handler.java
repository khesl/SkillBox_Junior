package module_13.Lessons_.res;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Handler extends DefaultHandler {

    private SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //super.startElement(uri, localName, qName, attributes);
        if (qName.equals("voter")){
            String name = attributes.getValue("name");
            Date birthDay = null;
            try {
                birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Voter voter = new Voter(name, birthDay);
        }
    }
}
