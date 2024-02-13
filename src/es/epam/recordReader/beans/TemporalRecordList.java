package es.epam.recordReader.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemporalRecordList {
    private List<TemporalRecord> records;

    public TemporalRecordList(){
        records= new ArrayList<TemporalRecord>();
    }

    public TemporalRecord getRecord(int i) {
        return records.get(i);
    }

    public void add(TemporalRecord record){
        records.add(record);
    }

    public int size() {
        return records.size();
    }

    public List<TemporalRecord> getRecords() {
        return records;
    }

    public boolean deleteRecord (TemporalRecord record){
        return this.getRecords().remove(record);
    }


    public void print() {
        if(records.size()!=0) {
            for (int i = 0; i < records.size(); i++) {
                System.out.println("record " + i + " " + this.records.get(i).toString());
            }
            System.out.println("------------------------------------");
            return;
        } else{System.out.println("RecordList is empty");}

    }


    public TemporalRecordList readCsvFile(String fileName) throws IOException {
        TemporalRecordList result = new TemporalRecordList ();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        try {
            String line ;
            // Run through following lines
            while ((line = br.readLine()) != null) {
                // Break line into entries using comma
                String[] items = line.split(",");
                try {
                    // If there are too many entries, throw a dummy exception, if
                    // there are too few, the same exception will be thrown later
                    if (items.length>3) throw new ArrayIndexOutOfBoundsException();
                    // Convert data to person record
                    TemporalRecord record = new TemporalRecord(Float.parseFloat((items[2])),Long.parseLong(items[0]),Long.parseLong(items[1]));
                    result.add(record);
                } catch (ArrayIndexOutOfBoundsException|NumberFormatException|NullPointerException e) {
                    // Caught errors indicate a problem with data format -> Print warning and continue
                  //  System.out.println("Invalid line: "+ line);
                }
            }

        } finally {
            br.close();
        }
        return result;
    }

    public TemporalRecordList readFile1(String fileName) throws IOException {
        TemporalRecordList result = new TemporalRecordList ();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        try {
            String line ;
            // Run through following lines
            while ((line = br.readLine()) != null) {
                // Break line into entries using comma
                String[] sublines = line.split(",");
                for (int i=0; i<=sublines.length;i++){
                    String[] items = sublines[i].split("\":");
                }



                }


        } finally {
            br.close();
        }
        return result;
    }
}
