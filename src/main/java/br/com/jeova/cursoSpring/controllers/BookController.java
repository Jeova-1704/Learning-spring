package br.com.jeova.cursoSpring.controllers;

import br.com.jeova.cursoSpring.DTO.BookDTO;
import br.com.jeova.cursoSpring.DTO.PersonDTO;
import br.com.jeova.cursoSpring.model.Book;
import br.com.jeova.cursoSpring.services.BookService;
import br.com.jeova.cursoSpring.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Peoples")
public class BookController {

    @Autowired
    private BookService service;


    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML})
    @Operation(summary = "Finds a book", description = "Finds a book", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    public BookDTO findByid(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_XML})
    @Operation(summary = "Finds all books", description = "Finds all books", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    public List<BookDTO> findAll(){
        return service.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Book", description = "Adds a news Book by passing in a JSON, XML or YML representation of the Book!", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return service.create(bookDTO);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a  Book", description = "Updated", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    public BookDTO update(@RequestBody BookDTO bookDTO) {
        return service.update(bookDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete a  Book", description = "Delete a Book by Id", tags = {"Book"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = {@Content  }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
