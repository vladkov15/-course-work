package com.tilted.Controllers;

import com.tilted.Models.ProducerDTO;
import com.tilted.Models.ProductDTO;
import com.tilted.Models.ValidationError;
import com.tilted.Services.ProducersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/producers")
public class ProducersController {
    private final ProducersService producersService;


    public ProducersController(ProducersService producersService) {
        this.producersService = producersService;
    }

    @GetMapping()
    public List<ProducerDTO> getAllProducers(){return producersService.GetAll(); }

    @GetMapping(value = "/id")
    public ResponseEntity<?> getProducerById(@PathVariable int id){
        var producerDTO = producersService.GetById(id);
        return producerDTO != null
                ? new ResponseEntity<>(producerDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteProducerById(@PathVariable int id) {
        return producersService.DeleteById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> createProducer(@Valid @RequestBody ProducerDTO producer) {
        var createdProducer = producersService.Create(producer);
        var responseModel = new HashMap<String, Integer>();
        responseModel.put("id", createdProducer.Id);

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    public ValidationError handleValidationErrors(MethodArgumentNotValidException ex) {
        var validationError = new ValidationError();
        validationError.Status = "Bad request";

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            validationError.Errors.put(fieldName, errorMessage);
        });

        return validationError;
    }
}
