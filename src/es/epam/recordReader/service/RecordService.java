package es.epam.recordReader.service;

import es.epam.recordReader.beans.TemporalRecordList;

import java.io.IOException;
import java.util.Map;

public interface RecordService {
    public  Map<String, Object> readJsonFileToMap(String filepath) throws IOException ;

    public TemporalRecordList readJsonFile (String filepath) throws IOException;

    public TemporalRecordList RecordCompare (TemporalRecordList v2, TemporalRecordList v1);

    public  void writeToCSV(TemporalRecordList records) ;

}
