package hexagonal.api;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationErrorResponse {

    public final String message;
    public final List<FieldError> fieldErrors;

    private ValidationErrorResponse(String message, List<FieldError> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    static Builder builder(){
        return new Builder();
    }

    public static class FieldError {
        public final String field;
        public final String code;
        public final String message;

        private FieldError(String field, String code, String message) {
            this.field = field;
            this.code = code;
            this.message = message;
        }
    }

    static class Builder {
        private String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setViolations(Set<ConstraintViolation<?>> constraintViolations){
            fieldErrors = constraintViolations.stream()
                    .map(Builder::resolveFieldError)
                    .collect(Collectors.toUnmodifiableList());

            return this;
        }

        private static FieldError resolveFieldError(ConstraintViolation<?> constraintViolation){
            String field = StringUtils.substringAfter(
                    constraintViolation.getPropertyPath().toString(), ".");

            return new FieldError(field,
                    constraintViolation.getMessageTemplate(),
                    constraintViolation.getMessage());
        }

        public ValidationErrorResponse build(){
            return new ValidationErrorResponse(message, fieldErrors);
        }
    }
}
