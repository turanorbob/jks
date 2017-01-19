package org.jks.utils;


import com.google.common.collect.Lists;
import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author liaojian
 * @version 12/28/2016
 */
public class DateUtil {

    /**
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static List<String> days(String fromDate, String toDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(fromDate);
        Date endDate = sdf.parse(toDate);
        System.out.println(startDate.equals(endDate));
        List<String> result = Lists.newArrayList();
        while (endDate.after(startDate)){
            result.add(sdf.format(startDate));
            startDate = DateUtils.addDays(startDate, 1);
        }
        result.add(sdf.format(endDate));
        return result;
    }

    public static void batchSave(List<String> items){
        int count = items.size();
        int limit = 10;
        if(count <= limit){
            System.out.println(items);
        }
        else {
            int offset = 0;
            while(count > 0){
                if(count >= limit){
                    List<String> item =
                            items.subList(offset, offset+limit);
                    System.out.println(item);
                }
                else {
                    List<String> item =
                            items.subList(offset, offset+count);
                    System.out.println(item);
                }
                count -= limit;
                offset += limit;
            }
        }
    }


    public static void main(String args[]){
        try{
            List<String> dd = days("2016-12-01", "2017-01-21");
            batchSave(dd);
        }
        catch (Exception e){

        }

    }
}
