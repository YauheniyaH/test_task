import es.epam.recordReader.beans.TemporalRecordList;
import es.epam.recordReader.service.RecordService;
import es.epam.recordReader.service.RecordServiceImpl;


public class DataRegressionTask
{
    public static void main(String[] args) throws Exception
    {

   //Read csv file with v2 responses to RecordsV2 object
    TemporalRecordList RecordsV2 = new TemporalRecordList().readCsvFile("/Users/Yauheniya_Hladkaya/Documents/test_task/v2_test.csv");
    RecordsV2.print();

    //Create RecordsV1 object to put data from json v1 responses
        TemporalRecordList RecordsV1 = new TemporalRecordList();

    // Read json file to RecordsV1 object
    RecordService recordService = new RecordServiceImpl();
    RecordsV1  =recordService.readJsonFile("/Users/Yauheniya_Hladkaya/Documents/test_task/v11_test.json");
    RecordsV1.print();

   TemporalRecordList RecordsCompareResult = recordService.RecordCompare(RecordsV1, RecordsV2);
   RecordsCompareResult.print();
   recordService.writeToCSV(RecordsCompareResult);
//while (RecordsV2.size())

    }
}  