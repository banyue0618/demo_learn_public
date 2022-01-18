package com.example.demo.common.support;

import net.sf.cglib.core.Converter;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.SimpleDateFormat;

/**
 * the Converter of bean by defined
 * @author banyue
 * date 2020-04-10
 */
public class DtoConverter implements Converter {

    SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object convert(Object value, Class target, Object context) {
        if (value instanceof Integer) {
            return value;
        } else if (value instanceof Timestamp) {
            Timestamp date = (Timestamp) value;
            return dateTimeSdf.format(date);
        } else if (value instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) value;
            return bd.toPlainString();
        }else if(value instanceof java.util.Date){
            return dateSdf.format(value);
        }else if(value instanceof java.lang.String){
            return value;
        }
        return null;
    }

}
