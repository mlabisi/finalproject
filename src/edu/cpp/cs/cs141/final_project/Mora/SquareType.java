package edu.cpp.cs.cs141.final_project.Mora;

/**
 * This enum type will represent the specific type of squares that
 * exist in the spy game.
 */
public enum SquareType{
    HALLWAY(" "),
    ROOM("R");

    private String letter;

    /**
     * Sets the character representation for each square type.
     *
     * @param letter The value of letter as a char
     */
    SquareType(String letter){
        this.letter = letter;
    }

    /**
     * @return The {@code char} representation of the square
     */
    public String toString(){
        return letter;
    }

    /**
     * Used to change the {@code char} representation of the
     * square if needed.
     *
     * @param letter The letter to be assigned
     */
    public void setLetter(String letter){
        this.letter = letter;
    }
}




