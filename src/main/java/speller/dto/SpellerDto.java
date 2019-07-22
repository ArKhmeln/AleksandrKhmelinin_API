package speller.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SpellerDto {

    private String code;
    private String pos;
    private String row;
    private String col;
    private String len;
    private String word;    //initial word
    private List<String> s;     //suggestions
}