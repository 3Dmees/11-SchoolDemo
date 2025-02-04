package lt.ca.javau11.schooldemov1.controllers;

import lt.ca.javau11.schooldemov1.controllers.DTO.GradeRecordRequest;
import lt.ca.javau11.schooldemov1.entities.GradeRecord;
import lt.ca.javau11.schooldemov1.services.GradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grade-records")
public class GradeRecordController {
    private final GradeRecordService gradeRecordService;
    @Autowired
    public GradeRecordController(GradeRecordService gradeRecordService) {
        this.gradeRecordService = gradeRecordService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeRecord> createGradeRecord(
            @RequestBody GradeRecordRequest gradeRecordRequest
    ){
        GradeRecord createdRecord = gradeRecordService.createGradeRecord(gradeRecordRequest);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeRecord> updateGradeRecord(@PathVariable Long id, @RequestBody GradeRecordRequest gradeRecordRequest) {
        // Ensure the 'id' in the URL matches the ID in the request if it exists
        gradeRecordRequest.setId(id); // Override MathRecordRequest ID with the URL id

        // Get the math record to handle potential nulls
        Optional<GradeRecord> optionalRecord = gradeRecordService.getGradeRecordById(id);
        if (optionalRecord.isPresent()) {
            GradeRecord updatedRecord = gradeRecordService.updateGradeRecord(gradeRecordRequest);
            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeRecord(@PathVariable Long id){
        Optional<GradeRecord> optionalRecord = gradeRecordService.getGradeRecordById(id);
        if(optionalRecord.isPresent()){
            gradeRecordService.deleteGradeRecord(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeRecord> getGradeRecordById(@PathVariable Long id){
        Optional<GradeRecord> gradeRecord = gradeRecordService.getGradeRecordById(id);
        return gradeRecord.map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GradeRecord>> getAllGradeRecords(){
        List<GradeRecord> allRecords = gradeRecordService.getAllGradeRecords();
        return new ResponseEntity<>(allRecords, HttpStatus.OK);
    }
}
