package org.example.designProblems.Bowling;


import org.example.designProblems.Bowling.Exception.PlayerCreationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws PlayerCreationException {



        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Player>> constraintViolations = validator
                .validate(new Player(1, null, null));
        for (ConstraintViolation<Player> cv : constraintViolations) {
            System.out.println(String.format(
                    "Error here! property: [%s], value: [%s], message: [%s]",
                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }
        Player player = new Player(1, null, new PlayerScoreBoard());
        System.out.println(player.getName());
    }

}
