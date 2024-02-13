package es.epam.recordReader.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.epam.recordReader.beans.TemporalRecord;
import es.epam.recordReader.beans.TemporalRecordList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class RecordServiceImpl implements RecordService{

    // Read data from json file to map
    @Override
    public Map<String, Object> readJsonFileToMap(String filepath) throws IOException {
        Gson gson = new Gson();
        String json = Files.readString(Paths.get(filepath));
        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
      //  map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));
        return map;
    }

    @Override
    public TemporalRecordList readJsonFile(String filepath) throws IOException {
        TemporalRecordList RecordsV1 = new TemporalRecordList();
        Map<String, Object> map =this.readJsonFileToMap("/Users/Yauheniya_Hladkaya/Documents/test_task/v11_test.json");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
     //   TimeZone tz  = TimeZone.g
        format.setTimeZone(TimeZone.getTimeZone("UTC"));


        map.forEach((x,y)->{
            String AsOfDateParam = x;
            String ObservationDateParam = y.toString().substring(1,21);
            int strLength = y.toString().length();
            String ActualValueParam = y.toString().substring(22,strLength-1);
           // System.out.println(AsOfDateParam);
            try {
                Date dateParam=format.parse(AsOfDateParam);
                long AsOfDateUnixParam = dateParam.getTime()/1000;
                //System.out.println(AsOfDateUnixParam);
                dateParam=format.parse(ObservationDateParam);
                long ObservationUnixParam = dateParam.getTime()/1000;
                RecordsV1.add(new TemporalRecord(Float.parseFloat(ActualValueParam),ObservationUnixParam,AsOfDateUnixParam));

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        });

        return RecordsV1;
    }

    @Override
    public TemporalRecordList RecordCompare(TemporalRecordList v2, TemporalRecordList v1) {

        TemporalRecordList result = new TemporalRecordList();
        List<TemporalRecord> records =v2.getRecords();
        boolean matched ;

        for (int i=0;i<v1.size();i++){
            matched=false;
            for (int j=0;j<v2.size();j++){
                if(v2.getRecord(j).equals(v1.getRecord(i))){
                    matched=true;
            }
            }
            if (matched==false){
                result.add(v1.getRecord(i));
            }
        }
        return result;
    }
    private static final String CSV_SEPARATOR = ",";
    @Override
    public void writeToCSV(TemporalRecordList records) {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("difference.csv"), "UTF-8"));
            for (int i=0;i< records.size();i++)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(records.getRecord(i).getActualValue());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(records.getRecord(i).getObservationDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(records.getRecord(i).getAsOfDate());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
}
