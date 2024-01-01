package io.hithub.demondevilhades.gemini.domain.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author awesome
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public abstract class Content {
    
    public static final class Role {
        public static final String USER = "user";
        public static final String MODEL = "model";
    }

    protected String role;
}
