package negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {
    private static final SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringParaData(String dataStr) throws ParseException{
        return formato.parse(dataStr);
    }

    public static String dataParaString(Date data){
        return formato.format(data);
    }
}
