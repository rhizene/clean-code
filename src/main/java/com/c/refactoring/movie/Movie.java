package com.c.refactoring.movie;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

import com.c.refactoring.StringUtils;

public class Movie {

    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {
        if (isNull(this.getRating())) return false;

        return isValidBRating() || isValidARating();
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private boolean isRatingStartsWith(Character letter) {
        return this.getRating().substring(0, 1).equalsIgnoreCase(letter.toString());
    }

    private boolean isValidARating() {
        final int RATING_LENGTH = 3;
        final char RATING_PREFIX = 'A';
        return isRatingStartsWith(RATING_PREFIX)
                && this.getRating().length() == RATING_LENGTH
                && StringUtils.isNumeric(this.getRating().substring(1, 3));
    }

    private boolean isValidBRating() {
        final int RATING_LENGTH = 2;
        final char RATING_PREFIX = 'B';
        final String RATING_SUFFIX = this.getRating().substring(1, 2);
        final boolean isSuffixNumeric = StringUtils.isNumeric(RATING_SUFFIX);
        final int numericSuffix = isSuffixNumeric ? parseInt(RATING_SUFFIX) : 0;

        return isRatingStartsWith(RATING_PREFIX)
                && this.getRating().length() == RATING_LENGTH
                && isSuffixNumeric
                && (numericSuffix >= 1 && numericSuffix <= 4);
    }
}
