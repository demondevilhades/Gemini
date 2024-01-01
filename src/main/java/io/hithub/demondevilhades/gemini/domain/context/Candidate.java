package io.hithub.demondevilhades.gemini.domain.context;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    private TextContent content;
    private String finishReason;
    private int index;
    private List<SafetyRating> safetyRatings;
}
